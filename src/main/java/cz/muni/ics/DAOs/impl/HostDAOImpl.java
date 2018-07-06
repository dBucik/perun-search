package cz.muni.ics.DAOs.impl;

import cz.muni.ics.DAOs.DAOUtils;
import cz.muni.ics.DAOs.HostDAO;
import cz.muni.ics.exceptions.DatabaseIntegrityException;
import cz.muni.ics.mappers.entities.HostMapper;
import cz.muni.ics.mappers.richEntities.RichHostMapper;
import cz.muni.ics.models.attributes.PerunAttribute;
import cz.muni.ics.models.entities.Host;
import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.richEntities.RichHost;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.IncorrectResultSetColumnCountException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class HostDAOImpl implements HostDAO {

    private static final HostMapper MAPPER = new HostMapper();
    private static final RichHostMapper RICH_MAPPER = new RichHostMapper();

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    /* HOST */

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Host getHost(Long id) throws DatabaseIntegrityException {
        String where = "WHERE t.id = ?";
        String query = queryBuilder(where, false);

        try {
            return jdbcTemplate.queryForObject(query, new Object[]{id}, MAPPER);
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (IncorrectResultSetColumnCountException e) {
            throw new DatabaseIntegrityException("More hosts with same ID found [id: " + id + ']');
        }
    }

    @Override
    public List<Host> getHostsByHostname(String hostname) {
        hostname = '%' + hostname + '%';
        String where = "WHERE upper(t.hostname) LIKE upper(?)";
        String query = queryBuilder(where, false);

        return jdbcTemplate.query(query, new Object[] {hostname}, MAPPER);
    }

    @Override
    public List<Host> getHosts() {
        String query = queryBuilder(null, false);

        return jdbcTemplate.query(query, MAPPER);
    }

    @Override
    public List<Host> getHostsHavingAttrs(List<InputAttribute> attrs) {
        return new ArrayList<>(getRichHostsHavingAttrs(attrs));
    }

    /* RICH_HOST */

    @Override
    public RichHost getRichHost(Long id) throws DatabaseIntegrityException {
        String where = "WHERE t.id = ?";
        String query = queryBuilder(where, true);

        try {
            return jdbcTemplate.queryForObject(query, new Object[]{id}, RICH_MAPPER);
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (IncorrectResultSetColumnCountException e) {
            throw new DatabaseIntegrityException("More hosts with same ID found [id: " + id + ']');
        }
    }

    @Override
    public List<RichHost> getRichHostsByHostname(String hostname) {
        hostname = '%' + hostname + '%';
        String where = "WHERE upper(t.hostname) LIKE upper(?)";
        String query = queryBuilder(where, true);

        return jdbcTemplate.query(query, new Object[] {hostname}, RICH_MAPPER);
    }

    @Override
    public List<RichHost> getRichHosts() {
        String query = queryBuilder(null, true);

        return jdbcTemplate.query(query, RICH_MAPPER);
    }

    @Override
    public List<RichHost> getRichHostsHavingAttrs(List<InputAttribute> attrs) {
        //TODO: improve
        List<RichHost> all = getRichHosts();
        List<RichHost> correct = new ArrayList<>();
        for (RichHost host: all) {
            if (DAOUtils.hasAttributes(host, attrs)) {
                correct.add(host);
            }
        }

        return correct;
    }

    /* ATTRIBUTES */

    @Override
    public List<PerunAttribute> getHostAttrs(Long id, List<String> attrs) throws DatabaseIntegrityException {
        //TODO: improve
        RichHost host = getRichHost(id);
        return host.getAttributesByKeys(attrs);
    }

    private String queryBuilder(String where, boolean withAttrs) {
        //TODO: check table names
        StringBuilder query = new StringBuilder();
        query.append("SELECT to_jsonb(t)");
        if (withAttrs) {
            query.append(" ||");
            query.append(" jsonb_build_object('attributes', json_agg(jsonb_build_object('key', friendly_name," +
                    " 'val', attr_value, 'val_text', attr_value_text, 'type', type)))");
        }
        query.append(" AS host");
        query.append(" FROM hosts t");
        if (withAttrs) {
            query.append(" JOIN host_attr_values av ON av.host_id = t.id");
            query.append(" JOIN attr_names an ON an.id = av.attr_id");
        }
        if (where != null) {
            query.append(' ').append(where.trim());
        }
        query.append(" GROUP BY t.id");
        return query.toString();
    }
}
