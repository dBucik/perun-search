package cz.muni.ics.DAOs.impl;

import cz.muni.ics.DAOs.DAOUtils;
import cz.muni.ics.DAOs.ResourceDAO;
import cz.muni.ics.exceptions.DatabaseIntegrityException;
import cz.muni.ics.mappers.entities.ResourceMapper;
import cz.muni.ics.mappers.richEntities.RichResourceMapper;
import cz.muni.ics.models.Attribute;
import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.entities.Resource;
import cz.muni.ics.models.richEntities.RichResource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.IncorrectResultSetColumnCountException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class ResourceDAOImpl implements ResourceDAO {

    private static final ResourceMapper MAPPER = new ResourceMapper();
    private static final RichResourceMapper RICH_MAPPER = new RichResourceMapper();

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Resource getResource(Long id) throws DatabaseIntegrityException {
        String where = "WHERE t.id = ?";
        String query = queryBuilder(where, false);

        try {
            return jdbcTemplate.queryForObject(query, new Object[]{id}, MAPPER);
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (IncorrectResultSetColumnCountException e) {
            throw new DatabaseIntegrityException("More resources with same ID found [id: " + id + ']');
        }
    }

    @Override
    public List<Resource> getResourcesByName(String name) {
        String where = "WHERE upper(t.name) LIKE  upper(?)";
        String query = queryBuilder(where, false);
        
        return jdbcTemplate.query(query, new Object[] {name}, MAPPER);
    }

    @Override
    public List<Resource> getResources() {
        String query = queryBuilder(null, false);
        
        return jdbcTemplate.query(query, MAPPER);
    }

    @Override
    public List<Resource> getResourcesWithAttrs(List<InputAttribute> attrs) {
        return new ArrayList<>(getRichResourcesWithAttrs(attrs));
    }

    @Override
    public List<Resource> getResourcesOfFacility(Long facilityId) {
        String where = "WHERE facility_id = ?";
        String query = queryBuilder(where, false);

        return jdbcTemplate.query(query, new Object[] {facilityId}, MAPPER);
    }

    @Override
    public List<Resource> getResourcesOfVo(Long voId) {
        String where = "WHERE vo_id = ?";
        String query = queryBuilder(where, false);

        return jdbcTemplate.query(query, new Object[] {voId}, MAPPER);
    }

    /* RICH_RESOURCE */

    @Override
    public RichResource getRichResource(Long id) throws DatabaseIntegrityException {
        String where = "WHERE t.id = ?";
        String query = queryBuilder(where, true);

        try {
            return jdbcTemplate.queryForObject(query, new Object[]{id}, RICH_MAPPER);
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (IncorrectResultSetColumnCountException e) {
            throw new DatabaseIntegrityException("More resources with same ID found [id: " + id + ']');
        }
    }

    @Override
    public List<RichResource> getRichResourcesByName(String name) {
        String where = "WHERE upper(t.name) LIKE  upper(?)";
        String query = queryBuilder(where, true);

        return jdbcTemplate.query(query, new Object[] {name}, RICH_MAPPER);
    }

    @Override
    public List<RichResource> getRichResources() {
        String query = queryBuilder(null, true);

        return jdbcTemplate.query(query, RICH_MAPPER);
    }

    @Override
    public List<RichResource> getRichResourcesWithAttrs(List<InputAttribute> attrs) {
        //TODO improve
        List<RichResource> resources = getRichResources();
        List<Attribute> filter = DAOUtils.convertAttrsFromInput(attrs);

        resources.removeIf(resource -> {
            assert filter != null;
            return ! resource.getAttributes().containsAll(filter);
        });

        return resources;
    }

    @Override
    public List<RichResource> getRichResourcesOfFacility(Long facilityId) {
        String where = "WHERE facility_id = ?";
        String query = queryBuilder(where, true);

        return jdbcTemplate.query(query, new Object[] {facilityId}, RICH_MAPPER);
    }

    @Override
    public List<RichResource> getRichResourcesOfVo(Long voId) {
        String where = "WHERE vo_id = ?";
        String query = queryBuilder(where, true);

        return jdbcTemplate.query(query, new Object[] {voId}, RICH_MAPPER);
    }

    /* ATTRIBUTES */

    @Override
    public List<Attribute> getResourceAttrs(Long id, List<String> attrs) throws DatabaseIntegrityException {
        //TODO: improve
        RichResource resource = getRichResource(id);
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

    private String queryBuilder(String where, boolean withAttrs) {
        //TODO: check table names
        StringBuilder query = new StringBuilder();
        query.append("SELECT to_jsonb(t)");
        if (withAttrs) {
            query.append(" ||");
            query.append(" jsonb_build_object('attributes', json_object_agg(friendly_name, attr_value))");
        }
        query.append(" AS resource");
        query.append(" FROM resources t");
        if (withAttrs) {
            query.append(" JOIN resource_attr_values av ON av.resource_id = t.id");
            query.append(" JOIN attr_names an ON an.id = av.attr_id");
        }
        if (where != null) {
            query.append(' ').append(where.trim());
        }
        query.append(" GROUP BY t.id");
        
        return query.toString();
    }

}
