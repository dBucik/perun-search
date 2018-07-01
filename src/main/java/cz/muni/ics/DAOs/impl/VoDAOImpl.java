package cz.muni.ics.DAOs.impl;

import cz.muni.ics.DAOs.VoDAO;
import cz.muni.ics.exceptions.DatabaseIntegrityException;
import cz.muni.ics.Utils;
import cz.muni.ics.mappers.VoMapper;
import cz.muni.ics.models.Attribute;
import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.Vo;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.IncorrectResultSetColumnCountException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class VoDAOImpl implements VoDAO {

    private static final VoMapper MAPPER = new VoMapper();

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Vo getVo(Long id, boolean withAttrs) throws DatabaseIntegrityException {
        //TODO: change query
        String query = "SELECT to_jsonb(t) || " +
                "jsonb_build_object('attributes', json_object_agg(friendly_name, attr_value)) AS vo " +
                "FROM vos t " +
                "JOIN vo_attr_values av ON av.vo_id = t.id " +
                "JOIN attr_names an ON an.id = av.attr_id " +
                "WHERE t.id=? " +
                "GROUP BY t.id";
        try {
            return jdbcTemplate.queryForObject(query, new Object[]{id}, MAPPER);
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (IncorrectResultSetColumnCountException e) {
            throw new DatabaseIntegrityException("More VOs with same ID found [id: " + id + ']');
        }
    }

    @Override
    public List<Vo> getVosByName(String name, boolean withAttrs) {
        name = '%' + name + '%';
        String where = "WHERE upper(t.name) LIKE upper(?)";
        String query = queryBuilder(where, withAttrs);

        return jdbcTemplate.query(query, new Object[] {name}, MAPPER);
    }

    @Override
    public Vo getVoByShortName(String shortName, boolean withAttrs) throws DatabaseIntegrityException {
        String where = "WHERE upper(t.short_name) = upper(?)";
        String query = queryBuilder(where, withAttrs);

        try {
            return jdbcTemplate.queryForObject(query, new Object[]{shortName}, MAPPER);
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (IncorrectResultSetColumnCountException e) {
            throw new DatabaseIntegrityException("More VOs with same shortName found [shortName: " + shortName + ']');
        }
    }

    @Override
    public List<Vo> getVosByShortName(String shortName, boolean withAttrs) {
        shortName = '%' + shortName + '%';
        String where = "WHERE upper(t.short_name) LIKE upper(?)";
        String query = queryBuilder(where);

        return jdbcTemplate.query(query, new Object[] {shortName}, MAPPER);
    }

    @Override
    public List<Vo> getVos(boolean withAttrs) {
        String query = queryBuilder(null, withAttrs);
        return jdbcTemplate.query(query, MAPPER);
    }

    @Override
    public List<Attribute> getVoAttrs(Long id, List<String> attrs) throws DatabaseIntegrityException {
        //TODO: improve
        Vo vo = getVo(id, true);
        List<Attribute> result = new ArrayList<>();
        if (attrs == null) {
            result.add(new Attribute("id", vo.getId().toString()));
            result.add(new Attribute("name", vo.getName()));
            result.add(new Attribute("shortName", vo.getShortName()));
            result.addAll(vo.getAttributes());
        } else {
            result.addAll(vo.getAttributesByKeys(attrs));
        }
        return result;
    }

    @Override
    public List<Vo> getVosWithAttrs(List<InputAttribute> attrs, boolean withAttrs) {
        //TODO: improve
        List<Vo> vos = getVos(withAttrs);
        List<Attribute> filter = Utils.convertAttrsFromInput(attrs);

        vos.removeIf(vo -> {
            assert filter != null;
            return !vo.getAttributes().containsAll(filter);
        });

        return vos;
    }

    private String queryBuilder(String where, boolean withAttrs) {
        //TODO: check table names
        StringBuilder query = new StringBuilder();
        query.append("SELECT to_jsonb(t)");
        if (withAttrs) {
            query.append(" || ");
            query.append("jsonb_build_object('attributes', json_object_agg(friendly_name, attr_value)) AS vo ");
        }
        query.append("FROM vos t ");
        if (withAttrs) {
            query.append("JOIN vo_attr_values av ON av.vo_id = t.id ");
            query.append("JOIN attr_names an ON an.id = av.attr_id ");
        }
        if (where != null) {
            query.append(where).append(' ');
        }
        query.append("GROUP BY t.id");
        return query.toString();
    }
}
