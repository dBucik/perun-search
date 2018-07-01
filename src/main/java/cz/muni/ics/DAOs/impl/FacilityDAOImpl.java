package cz.muni.ics.DAOs.impl;

import cz.muni.ics.DAOs.DAOUtils;
import cz.muni.ics.DAOs.FacilityDAO;
import cz.muni.ics.exceptions.DatabaseIntegrityException;
import cz.muni.ics.mappers.entities.FacilityMapper;
import cz.muni.ics.mappers.richEntities.RichFacilityMapper;
import cz.muni.ics.models.Attribute;
import cz.muni.ics.models.entities.Facility;
import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.richEntities.RichFacility;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.IncorrectResultSetColumnCountException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class FacilityDAOImpl implements FacilityDAO {

    private static final FacilityMapper MAPPER = new FacilityMapper();
    private static final RichFacilityMapper RICH_MAPPER = new RichFacilityMapper();

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /* FACILITY */

    @Override
    public Facility getFacility(Long id) throws DatabaseIntegrityException {
        String where = "WHERE t.id = ?";
        String query = queryBuilder(where, false);

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
        String query = queryBuilder(null, false);

        return jdbcTemplate.query(query, MAPPER);
    }

    @Override
    public List<Facility> getFacilitiesByAttrs(List<InputAttribute> attrs) {
        return new ArrayList<>(getRichFacilitiesByAttrs(attrs));
    }

    @Override
    public List<Facility> getFacilitiesByName(String name) {
        name = '%' + name + '%';
        String where = "WHERE upper(t.name) LIKE upper(?)";
        String query = queryBuilder(where, false);

        return jdbcTemplate.query(query, new Object[] {name}, MAPPER);
    }

    /* RICH_FACILITY */

    @Override
    public RichFacility getRichFacility(Long id) throws DatabaseIntegrityException {
        String where = "WHERE t.id = ?";
        String query = queryBuilder(where, true);

        try {
            return jdbcTemplate.queryForObject(query, new Object[]{id}, RICH_MAPPER);
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (IncorrectResultSetColumnCountException e) {
            throw new DatabaseIntegrityException("More facilities with same ID found [id: " + id + ']');
        }
    }

    @Override
    public List<RichFacility> getRichFacilities() {
        String query = queryBuilder(null, true);

        return jdbcTemplate.query(query, RICH_MAPPER);
    }

    @Override
    public List<RichFacility> getRichFacilitiesByAttrs(List<InputAttribute> attrs) {
        //TODO: improve
        List<RichFacility> facilities = getRichFacilities();
        List<Attribute> filter = DAOUtils.convertAttrsFromInput(attrs);

        facilities.removeIf(facility -> {
            assert filter != null;
            return !facility.getAttributes().containsAll(filter);
        });

        return facilities;
    }

    @Override
    public List<RichFacility> getRichFacilitiesByName(String name) {
        name = '%' + name + '%';
        String where = "WHERE upper(t.name) LIKE upper(?)";
        String query = queryBuilder(where, true);

        return jdbcTemplate.query(query, new Object[] {name}, RICH_MAPPER);
    }

    /* ATTRIBUTES */

    @Override
    public List<Attribute> getFacilityAttrs(Long id, List<String> attrs) throws DatabaseIntegrityException {
        //TODO: improve
        RichFacility facility = getRichFacility(id);
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

    private String queryBuilder(String where, boolean withAttrs) {
        //TODO: check table names
        StringBuilder query = new StringBuilder();
        query.append("SELECT to_jsonb(t)");
        if (withAttrs) {
            query.append(" ||");
            query.append(" jsonb_build_object('attributes', json_object_agg(friendly_name, attr_value))");
        }
        query.append(" AS facility");
        query.append(" FROM facilities t");
        if (withAttrs) {
            query.append(" JOIN facility_attr_values av ON av.facility_id = t.id");
            query.append(" JOIN attr_names an ON an.id = av.attr_id");
        }
        if (where != null) {
            query.append(' ').append(where.trim());
        }
        query.append(" GROUP BY t.id");
        return query.toString();
    }

}
