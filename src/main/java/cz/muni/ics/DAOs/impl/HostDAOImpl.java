package cz.muni.ics.DAOs.impl;

import cz.muni.ics.DAOs.HostDAO;
import cz.muni.ics.Utils;
import cz.muni.ics.exceptions.DatabaseIntegrityException;
import cz.muni.ics.mappers.entities.HostMapper;
import cz.muni.ics.models.Attribute;
import cz.muni.ics.models.entities.Host;
import cz.muni.ics.models.InputAttribute;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.IncorrectResultSetColumnCountException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class HostDAOImpl implements HostDAO {

    private static final HostMapper MAPPER = new HostMapper();

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Host getHost(Long id, boolean withAttrs) throws DatabaseIntegrityException {
        String where = "WHERE t.id = ?";
        String query = queryBuilder(where, withAttrs);

        try {
            return jdbcTemplate.queryForObject(query, new Object[]{id}, MAPPER);
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (IncorrectResultSetColumnCountException e) {
            throw new DatabaseIntegrityException("More hosts with same ID found [id: " + id + ']');
        }
    }

    @Override
    public List<Host> getHostsByHostname(String hostname, boolean withAttrs) {
        hostname = '%' + hostname + '%';
        String where = "WHERE upper(t.hostname) LIKE upper(?)";
        String query = queryBuilder(where, withAttrs);

        return jdbcTemplate.query(query, new Object[] {hostname}, MAPPER);
    }

    @Override
    public List<Host> getHosts(boolean withAttrs) {
        String query = queryBuilder(null, withAttrs);

        return jdbcTemplate.query(query, MAPPER);
    }

    @Override
    public List<Attribute> getHostAttrs(Long id, List<String> attrs) throws DatabaseIntegrityException {
        //TODO: improve
        Host host = getHost(id, true);
        List<Attribute> result = new ArrayList<>();

        if (attrs == null) {
            result.add(new Attribute("id", host.getId().toString()));
            result.add(new Attribute("hostname", host.getHostname()));
            result.add(new Attribute("description", host.getDescription()));
            result.add(new Attribute("facilityId", host.getFacilityId().toString()));
            result.addAll(host.getAttributes());
        } else {
            result.addAll(host.getAttributesByKeys(attrs));
        }

        return result;
    }

    @Override
    public List<Host> getHostsWithAttrs(List<InputAttribute> attrs, boolean withAttrs) {
        //TODO: improve
        List<Host> hosts = getHosts(withAttrs);
        List<Attribute> filter = Utils.convertAttrsFromInput(attrs);

        hosts.removeIf(host -> {
            assert filter != null;
            return ! host.getAttributes().containsAll(filter);
        });

        return hosts;
    }

    private String queryBuilder(String where, boolean withAttrs) {
        //TODO: check table names
        StringBuilder query = new StringBuilder();
        query.append("SELECT to_jsonb(t)");
        if (withAttrs) {
            query.append(" || ");
            query.append("jsonb_build_object('attributes', json_object_agg(friendly_name, attr_value)) AS host ");
        }
        query.append("FROM hosts t ");
        if (withAttrs) {
            query.append("JOIN host_attr_values av ON av.host_id = t.id ");
            query.append("JOIN attr_names an ON an.id = av.attr_id ");
        }
        if (where != null) {
            query.append(where).append(' ');
        }
        query.append("GROUP BY t.id");
        return query.toString();
    }
}
