package cz.muni.ics.JdbcTemplates;

import cz.muni.ics.DAOs.FacilityDAO;
import cz.muni.ics.Utils;
import cz.muni.ics.exceptions.DatabaseIntegrityException;
import cz.muni.ics.mappers.AttributeMapper;
import cz.muni.ics.mappers.FacilityMapper;
import cz.muni.ics.models.Attribute;
import cz.muni.ics.models.Facility;
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

public class FacilityJdbcTemplate implements FacilityDAO {

    private static final FacilityMapper MAPPER = new FacilityMapper();

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Facility getFacility(Long id) throws DatabaseIntegrityException {
        String where = "WHERE t.id = ?";
        String query = queryBuilder(where);

        try {
            return jdbcTemplate.queryForObject(query, new Object[]{id}, MAPPER);
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (IncorrectResultSetColumnCountException e) {
            throw new DatabaseIntegrityException("More facilities with same ID found [id: " + id + ']');
        }
    }

    @Override
    public List<Facility> getFacilities() {
        String query = queryBuilder(null);

        return jdbcTemplate.query(query, MAPPER);
    }

    @Override
    public List<Attribute> getFacilityAttrs(Long id, List<String> attrs) throws DatabaseIntegrityException {
        Facility facility = getFacility(id);
        List<Attribute> result = new ArrayList<>();

        if (attrs == null) {
            result.add(new Attribute("id", facility.getId().toString()));
            result.add(new Attribute("name", facility.getName()));
            result.add(new Attribute("description", facility.getDescription()));
            result.addAll(facility.getAttributes());
        } else {
            result.addAll(facility.getAttributesByKeys(attrs));
        }

        return result;
    }

    @Override
    public List<Facility> getFacilitiesByAttrs(List<InputAttribute> attrs) {
        List<Facility> facilities = getFacilities();
        List<Attribute> filter = Utils.convertAttrsFromInput(attrs);

        facilities.removeIf(facility -> {
           assert filter != null;
           return !facility.getAttributes().containsAll(filter);
        });

        return facilities;
    }

    @Override
    public List<Facility> getFacilitiesByName(String name) {
        name = '%' + name + '%';
        String where = "WHERE upper(t.name) LIKE upper(?)";
        String query = queryBuilder(where);

        return jdbcTemplate.query(query, new Object[] {name}, MAPPER);
    }

    private String queryBuilder(String where) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT to_jsonb(t) || ");
        query.append("jsonb_build_object('attributes', json_object_agg(friendly_name, attr_value)) AS facility ");
        query.append("FROM facilities t ");
        query.append("JOIN facility_attr_values av ON av.facility_id = t.id ");
        query.append("JOIN attr_names an ON an.id = av.attr_id ");
        if (where != null) {
            query.append(where).append(' ');
        }
        query.append("GROUP BY t.id");
        return query.toString();
    }
}
