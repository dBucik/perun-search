package cz.muni.ics.DAOs.impl;

import cz.muni.ics.DAOs.DAOUtils;
import cz.muni.ics.DAOs.ResourceDAO;
import cz.muni.ics.exceptions.DatabaseIntegrityException;
import cz.muni.ics.mappers.entities.ResourceMapper;
import cz.muni.ics.mappers.richEntities.RichResourceMapper;
import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.PerunEntityType;
import cz.muni.ics.models.attributes.PerunAttribute;
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
        String query = DAOUtils.queryBuilder(where, false, PerunEntityType.RESOURCE);

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
        String query = DAOUtils.queryBuilder(where, false, PerunEntityType.RESOURCE);
        
        return jdbcTemplate.query(query, new Object[] {name}, MAPPER);
    }

    @Override
    public List<Resource> getResources() {
        String query = DAOUtils.queryBuilder(null, false, PerunEntityType.RESOURCE);
        
        return jdbcTemplate.query(query, MAPPER);
    }

    @Override
    public List<Resource> getResourcesHavingAttrs(List<InputAttribute> attrs) {
        return new ArrayList<>(getRichResourcesHavingAttrs(attrs));
    }

    @Override
    public List<Resource> getResourcesOfFacility(Long facilityId) {
        String where = "WHERE facility_id = ?";
        String query = DAOUtils.queryBuilder(where, false, PerunEntityType.RESOURCE);

        return jdbcTemplate.query(query, new Object[] {facilityId}, MAPPER);
    }

    @Override
    public List<Resource> getResourcesOfVo(Long voId) {
        String where = "WHERE vo_id = ?";
        String query = DAOUtils.queryBuilder(where, false, PerunEntityType.RESOURCE);

        return jdbcTemplate.query(query, new Object[] {voId}, MAPPER);
    }

    /* RICH_RESOURCE */

    @Override
    public RichResource getRichResource(Long id) throws DatabaseIntegrityException {
        String where = "WHERE t.id = ?";
        String query = DAOUtils.queryBuilder(where, true, PerunEntityType.RESOURCE);

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
        String query = DAOUtils.queryBuilder(where, true, PerunEntityType.RESOURCE);

        return jdbcTemplate.query(query, new Object[] {name}, RICH_MAPPER);
    }

    @Override
    public List<RichResource> getRichResources() {
        String query = DAOUtils.queryBuilder(null, true, PerunEntityType.RESOURCE);

        return jdbcTemplate.query(query, RICH_MAPPER);
    }

    @Override
    public List<RichResource> getRichResourcesHavingAttrs(List<InputAttribute> attrs) {
        //TODO improve
        List<RichResource> all = getRichResources();
        List<RichResource> correct = new ArrayList<>();
        for (RichResource resource: all) {
            if (DAOUtils.hasAttributes(resource, attrs)) {
                correct.add(resource);
            }
        }

        return correct;
    }

    @Override
    public List<RichResource> getRichResourcesOfFacility(Long facilityId) {
        String where = "WHERE facility_id = ?";
        String query = DAOUtils.queryBuilder(where, true, PerunEntityType.RESOURCE);

        return jdbcTemplate.query(query, new Object[] {facilityId}, RICH_MAPPER);
    }

    @Override
    public List<RichResource> getRichResourcesOfVo(Long voId) {
        String where = "WHERE vo_id = ?";
        String query = DAOUtils.queryBuilder(where, true, PerunEntityType.RESOURCE);

        return jdbcTemplate.query(query, new Object[] {voId}, RICH_MAPPER);
    }

    /* ATTRIBUTES */

    @Override
    public List<PerunAttribute> getResourceAttrs(Long id, List<String> attrs) throws DatabaseIntegrityException {
        //TODO: improve
        RichResource resource = getRichResource(id);
        return resource.getAttributesByKeys(attrs);
    }

}
