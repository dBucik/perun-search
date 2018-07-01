package cz.muni.ics.DAOs.impl;

import cz.muni.ics.DAOs.DAOUtils;
import cz.muni.ics.DAOs.ServiceDAO;
import cz.muni.ics.exceptions.DatabaseIntegrityException;
import cz.muni.ics.mappers.entities.ServiceMapper;
import cz.muni.ics.mappers.richEntities.RichServiceMapper;
import cz.muni.ics.models.Attribute;
import cz.muni.ics.models.InputAttribute;
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
        String query = queryBuilder(where, false);

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
        String query = queryBuilder(where, false);

        return jdbcTemplate.query(query, new Object[] {name}, MAPPER);
    }

    @Override
    public List<Service> getServices() {
        String query = queryBuilder(null, false);

        return jdbcTemplate.query(query, MAPPER);
    }

    @Override
    public List<Service> getServicesWithAttrs(List<InputAttribute> attrs) {
        return new ArrayList<>(getRichServicesWithAttrs(attrs));
    }

    @Override
    public List<Service> getServicesOfOwner(Long ownerId) {
        String where = "WHERE t.owner_id = ?";
        String query = queryBuilder(where, false);

        return jdbcTemplate.query(query, new Object[] {ownerId}, MAPPER);
    }

    /* RICH SERVICE */

    @Override
    public RichService getRichService(Long id) throws DatabaseIntegrityException {
        String where = "WHERE t.id = ?";
        String query = queryBuilder(where, true);

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
        String where = "WHERE upper(t.name) LIKE  upper(?)";
        String query = queryBuilder(where, true);

        return jdbcTemplate.query(query, new Object[] {name}, RICH_MAPPER);
    }

    @Override
    public List<RichService> getRichServices() {
        String query = queryBuilder(null, true);

        return jdbcTemplate.query(query, RICH_MAPPER);
    }

    @Override
    public List<RichService> getRichServicesWithAttrs(List<InputAttribute> attrs) {
        //TODO improve
        List<RichService> services = getRichServices();
        List<Attribute> filter = DAOUtils.convertAttrsFromInput(attrs);

        services.removeIf(service -> {
            assert filter != null;
            return ! service.getAttributes().containsAll(filter);
        });

        return services;
    }

    @Override
    public List<RichService> getRichServicesOfOwner(Long ownerId) {
        String where = "WHERE t.owner_id = ?";
        String query = queryBuilder(where, true);

        return jdbcTemplate.query(query, new Object[] {ownerId}, RICH_MAPPER);
    }

    /* ATTRIBUTES */

    @Override
    public List<Attribute> getServiceAttrs(Long id, List<String> attrs) throws DatabaseIntegrityException {
        //TODO: improve
        RichService service = getRichService(id);
        List<Attribute> result = new ArrayList<>();

        if (attrs == null) {
            result.add(new Attribute("id", service.getId().toString()));
            result.add(new Attribute("name", service.getName()));
            result.add(new Attribute("ownerId", service.getOwnerId().toString()));
            result.addAll(service.getAttributes());
        } else {
            result.addAll(service.getAttributesByKeys(attrs));
        }

        return result;
    }

    private String queryBuilder(String where, boolean withAttrs) {
        //TODO: check table names
        StringBuilder query = new StringBuilder();
        query.append("SELECT to_jsonb(t)");
        if (withAttrs) {
            query.append(" || ");
            query.append("jsonb_build_object('attributes', json_object_agg(friendly_name, attr_value)) AS service ");
        }
        query.append("FROM services t ");
        if (withAttrs) {
            query.append("JOIN service_required_attrs av ON av.service_id = t.id ");
            query.append("JOIN attr_names an ON an.id = av.attr_id ");
        }
        if (where != null) {
            query.append(where).append(' ');
        }
        query.append("GROUP BY t.id");

        return query.toString();
    }

}
