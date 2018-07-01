package cz.muni.ics.JdbcTemplates;

import cz.muni.ics.DAOs.ExtSourceDAO;
import cz.muni.ics.Utils;
import cz.muni.ics.exceptions.DatabaseIntegrityException;
import cz.muni.ics.mappers.AttributeMapper;
import cz.muni.ics.mappers.ExtSourceMapper;
import cz.muni.ics.models.Attribute;
import cz.muni.ics.models.ExtSource;
import cz.muni.ics.models.InputAttribute;
import org.json.JSONObject;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.IncorrectResultSetColumnCountException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExtSourceJdbcTemplate implements ExtSourceDAO {

    private static final ExtSourceMapper MAPPER = new ExtSourceMapper();

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public ExtSource getExtSource(Long id) throws DatabaseIntegrityException {
        String where = "WHERE t.id = ?";
        String query = queryBuilder(where);
        try {
            return jdbcTemplate.queryForObject(query, new Object[] {id}, MAPPER);
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (IncorrectResultSetColumnCountException e) {
            throw new DatabaseIntegrityException("More ExtSources with same ID found [id: " + id + ']');
        }
    }

    @Override
    public List<ExtSource> getExtSources() {
        String query = queryBuilder(null);

        return jdbcTemplate.query(query, MAPPER);
    }

    @Override
    public List<Attribute> getExtSourceAttrs(Long id, List<String> attrs) throws DatabaseIntegrityException {
        ExtSource extSource = getExtSource(id);
        List<Attribute> result = new ArrayList<>();
        if (attrs == null) {
            result.add(new Attribute("id", extSource.getId().toString()));
            result.add(new Attribute("name", extSource.getName()));
            result.add(new Attribute("type", extSource.getType()));
            result.addAll(extSource.getAttributes());
        } else {
            result.addAll(extSource.getAttributesByKeys(attrs));
        }

        return result;
    }

    @Override
    public List<ExtSource> getExtSourcesByName(String name) {
        name = '%' + name + '%';
        String where = "WHERE upper(t.name) LIKE upper(?)";
        String query = queryBuilder(where);

        return jdbcTemplate.query(query, new Object[] {name}, MAPPER);
    }

    @Override
    public List<ExtSource> getExtSourcesByType(String type) {
        type = '%' + type + '%';
        String where = "WHERE upper(t.type) LIKE upper(?)";
        String query = queryBuilder(where);

        return jdbcTemplate.query(query, new Object[] {type}, MAPPER);
    }

    @Override
    public List<ExtSource> getExtSourcesWithAttrs(List<InputAttribute> attrs) {
        List<ExtSource> extSources = getExtSources();
        List<Attribute> filter = Utils.convertAttrsFromInput(attrs);
        extSources.removeIf(extSource -> {
            assert filter != null;
            return ! extSource.getAttributes().containsAll(filter);
        });

        return extSources;
    }

    private String queryBuilder(String where) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT to_jsonb(t) || ");
        query.append("jsonb_build_object('attributes', json_object_agg(friendly_name, attr_value)) AS extSource ");
        query.append("FROM ext_sources t ");
        query.append("JOIN ext_source_attr_values av ON av.ext_source_id = t.id ");
        query.append("JOIN attr_names an ON an.id = av.attr_id ");
        if (where != null) {
            query.append(where).append(' ');
        }
        query.append("GROUP BY t.id");
        return query.toString();
    }
}
