package cz.muni.ics.DAOs.impl;

import cz.muni.ics.DAOs.ServiceDAO;
import cz.muni.ics.Utils;
import cz.muni.ics.exceptions.DatabaseIntegrityException;
import cz.muni.ics.mappers.ServiceMapper;
import cz.muni.ics.models.Attribute;
import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.Service;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.IncorrectResultSetColumnCountException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class ServiceDAOImpl implements ServiceDAO {

    private static final ServiceMapper MAPPER = new ServiceMapper();

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Service getService(Long id, boolean withAttrs) throws DatabaseIntegrityException {
        String where = "WHERE t.id = ?";
        String query = queryBuilder(where, withAttrs);

        try {
            return jdbcTemplate.queryForObject(query, new Object[]{id}, MAPPER);
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (IncorrectResultSetColumnCountException e) {
            throw new DatabaseIntegrityException("More services with same ID found [id: " + id + ']');
        }
    }

    @Override
    public List<Service> getServicesByName(String name, boolean withAttrs) {
        String where = "WHERE upper(t.name) LIKE  upper(?)";
        String query = queryBuilder(where, withAttrs);

        return jdbcTemplate.query(query, new Object[] {name}, MAPPER);
    }

    @Override
    public List<Service> getServices(boolean withAttrs) {
        String query = queryBuilder(null, withAttrs);

        return jdbcTemplate.query(query, MAPPER);
    }

    @Override
    public List<Attribute> getServiceAttrs(Long id, List<String> attrs) throws DatabaseIntegrityException {
        //TODO: improve
        Service service = getService(id, true);
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

    @Override
    public List<Service> getServicesWithAttrs(List<InputAttribute> attrs, boolean withAttrs) {
        //TODO improve
        List<Service> services = getServices(withAttrs);
        List<Attribute> filter = Utils.convertAttrsFromInput(attrs);

        services.removeIf(service -> {
            assert filter != null;
            return ! service.getAttributes().containsAll(filter);
        });

        return services;
    }

    @Override
    public List<Service> getServicesOfOwner(Long ownerId, boolean withAttrs) {
        String where = "WHERE t.owner_id = ?";
        String query = queryBuilder(where, withAttrs);

        return jdbcTemplate.query(query, new Object[] {ownerId}, MAPPER);
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
