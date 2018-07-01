package cz.muni.ics.DAOs.impl;

import cz.muni.ics.DAOs.ResourceDAO;
import cz.muni.ics.Utils;
import cz.muni.ics.exceptions.DatabaseIntegrityException;
import cz.muni.ics.mappers.entities.ResourceMapper;
import cz.muni.ics.models.Attribute;
import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.entities.Resource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.IncorrectResultSetColumnCountException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class ResourceDAOImpl implements ResourceDAO {

    private static final ResourceMapper MAPPER = new ResourceMapper();

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Resource getResource(Long id, boolean withAttrs) throws DatabaseIntegrityException {
        String where = "WHERE t.id = ?";
        String query = queryBuilder(where, withAttrs);

        try {
            return jdbcTemplate.queryForObject(query, new Object[]{id}, MAPPER);
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (IncorrectResultSetColumnCountException e) {
            throw new DatabaseIntegrityException("More resources with same ID found [id: " + id + ']');
        }
    }

    @Override
    public List<Resource> getResourcesByName(String name, boolean withAttrs) {
        String where = "WHERE upper(t.name) LIKE  upper(?)";
        String query = queryBuilder(where, withAttrs);
        
        return jdbcTemplate.query(query, new Object[] {name}, MAPPER);
    }

    @Override
    public List<Resource> getResources(boolean withAttrs) {
        String query = queryBuilder(null, withAttrs);
        
        return jdbcTemplate.query(query, MAPPER);
    }

    @Override
    public List<Attribute> getResourceAttrs(Long id, List<String> attrs) throws DatabaseIntegrityException {
        //TODO: improve
        Resource resource = getResource(id, true);
        List<Attribute> result = new ArrayList<>();

        if (attrs == null) {
            result.add(new Attribute("id", resource.getId().toString()));
            result.add(new Attribute("name", resource.getName()));
            result.add(new Attribute("description", resource.getDescription()));
            result.add(new Attribute("facilityId", resource.getFacilityId().toString()));
            result.add(new Attribute("voId", resource.getVoId().toString()));
            result.addAll(resource.getAttributes());
        } else {
            result.addAll(resource.getAttributesByKeys(attrs));
        }

        return result;
    }

    @Override
    public List<Resource> getResourcesWithAttrs(List<InputAttribute> attrs, boolean withAttrs) {
        //TODO improve
        List<Resource> resources = getResources(withAttrs);
        List<Attribute> filter = Utils.convertAttrsFromInput(attrs);

        resources.removeIf(resource -> {
            assert filter != null;
            return ! resource.getAttributes().containsAll(filter);
        });

        return resources;
    }

    @Override
    public List<Resource> getResourcesOfFacility(Long facilityId, boolean withAttrs) {
        String where = "WHERE facility_id = ?";
        String query = queryBuilder(where, withAttrs);

        return jdbcTemplate.query(query, new Object[] {facilityId}, MAPPER);
    }

    @Override
    public List<Resource> getResourcesOfVo(Long voId, boolean withAttrs) {
        String where = "WHERE vo_id = ?";
        String query = queryBuilder(where, withAttrs);

        return jdbcTemplate.query(query, new Object[] {voId}, MAPPER);
    }

    private String queryBuilder(String where, boolean withAttrs) {
        //TODO: check table names
        StringBuilder query = new StringBuilder();
        query.append("SELECT to_jsonb(t)");
        if (withAttrs) {
            query.append(" || ");
            query.append("jsonb_build_object('attributes', json_object_agg(friendly_name, attr_value)) AS resource ");
        }
        query.append("FROM resources t ");
        if (withAttrs) {
            query.append("JOIN resource_attr_values av ON av.resource_id = t.id ");
            query.append("JOIN attr_names an ON an.id = av.attr_id ");
        }
        if (where != null) {
            query.append(where).append(' ');
        }
        query.append("GROUP BY t.id");
        
        return query.toString();
    }
}
