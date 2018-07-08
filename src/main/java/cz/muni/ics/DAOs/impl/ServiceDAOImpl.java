package cz.muni.ics.DAOs.impl;

import cz.muni.ics.DAOs.DAOUtils;
import cz.muni.ics.DAOs.ServiceDAO;
import cz.muni.ics.exceptions.DatabaseIntegrityException;
import cz.muni.ics.mappers.entities.ServiceMapper;
import cz.muni.ics.mappers.richEntities.RichServiceMapper;
import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.PerunEntityType;
import cz.muni.ics.models.attributes.PerunAttribute;
import cz.muni.ics.models.entities.Service;
import cz.muni.ics.models.richEntities.RichService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.IncorrectResultSetColumnCountException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class ServiceDAOImpl implements ServiceDAO {

    private static final ServiceMapper MAPPER = new ServiceMapper();
    private static final RichServiceMapper RICH_MAPPER = new RichServiceMapper();

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Service getService(Long id) throws DatabaseIntegrityException {
        String where = "WHERE t.id = ?";
        String query = DAOUtils.simpleQueryBuilder(where, PerunEntityType.SERVICE);

        try {
            return jdbcTemplate.queryForObject(query, new Object[]{id}, MAPPER);
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (IncorrectResultSetColumnCountException e) {
            throw new DatabaseIntegrityException("More services with same ID found [id: " + id + ']');
        }
    }

    @Override
    public List<Service> getServicesByName(String name) {
        String where = "WHERE upper(t.name) LIKE  upper(?)";
        String query = DAOUtils.simpleQueryBuilder(where, PerunEntityType.SERVICE);

        return jdbcTemplate.query(query, new Object[] {name}, MAPPER);
    }

    @Override
    public List<Service> getServices() {
        String query = DAOUtils.simpleQueryBuilder(null, PerunEntityType.SERVICE);

        return jdbcTemplate.query(query, MAPPER);
    }

    @Override
    public List<Service> getServicesHavingAttrs(List<InputAttribute> attrs) {
        return new ArrayList<>(getRichServicesHavingAttrs(attrs));
    }

    @Override
    public List<Service> getServicesOfOwner(Long ownerId) {
        String where = "WHERE t.owner_id = ?";
        String query = DAOUtils.simpleQueryBuilder(where, PerunEntityType.SERVICE);

        return jdbcTemplate.query(query, new Object[] {ownerId}, MAPPER);
    }

    /* RICH SERVICE */

    @Override
    public RichService getRichService(Long id) throws DatabaseIntegrityException {
        String entityWhere = "WHERE t.id = ?";
        String query = DAOUtils.queryBuilder(entityWhere, null, PerunEntityType.SERVICE);

        try {
            return jdbcTemplate.queryForObject(query, new Object[]{id}, RICH_MAPPER);
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (IncorrectResultSetColumnCountException e) {
            throw new DatabaseIntegrityException("More services with same ID found [id: " + id + ']');
        }
    }

    @Override
    public List<RichService> getRichServicesByName(String name) {
        String entityWhere = "WHERE upper(t.name) LIKE  upper(?)";
        String query = DAOUtils.queryBuilder(entityWhere, null, PerunEntityType.SERVICE);

        return jdbcTemplate.query(query, new Object[] {name}, RICH_MAPPER);
    }

    @Override
    public List<RichService> getRichServices() {
        String query = DAOUtils.queryBuilder(null, null, PerunEntityType.SERVICE);

        return jdbcTemplate.query(query, RICH_MAPPER);
    }

    @Override
    public List<RichService> getRichServicesOfOwner(Long ownerId) {
        String entityWhere = "WHERE t.owner_id = ?";
        String query = DAOUtils.queryBuilder(entityWhere, null, PerunEntityType.SERVICE);

        return jdbcTemplate.query(query, new Object[] {ownerId}, RICH_MAPPER);
    }

    @Override
    public List<RichService> getRichServicesHavingAttrs(List<InputAttribute> attrs) {
        //TODO improve
        List<RichService> all = getRichServices();
        List<RichService> correct = new ArrayList<>();
        for (RichService service: all) {
            if (DAOUtils.hasAttributes(service, attrs)) {
                correct.add(service);
            }
        }

        return correct;
    }

    /* ATTRIBUTES */

    @Override
    public List<PerunAttribute> getServiceAttrs(Long id, List<String> attrs) throws DatabaseIntegrityException {
        //TODO: improve
        RichService service = getRichService(id);
        return service.getAttributesByKeys(attrs);
    }

}
