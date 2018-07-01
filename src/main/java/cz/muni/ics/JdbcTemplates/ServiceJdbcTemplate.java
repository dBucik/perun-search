package cz.muni.ics.JdbcTemplates;

import cz.muni.ics.DAOs.ServiceDAO;
import cz.muni.ics.mappers.AttributeMapper;
import cz.muni.ics.mappers.ServiceMapper;
import cz.muni.ics.models.Attribute;
import cz.muni.ics.models.Service;
import org.json.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceJdbcTemplate implements ServiceDAO {

    private static final ServiceMapper MAPPER = new ServiceMapper();
    private static final AttributeMapper ATTR_MAPPER = new AttributeMapper();

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Service getService(Long id) {
        //TODO better query
        String query = "SELECT * FROM services WHERE id=?";
        Service service = jdbcTemplate.queryForObject(query, new Object[] {id}, MAPPER);

        String attrQuery = "SELECT an.friendly_name, av.attr_value " +
                "FROM attr_names an RIGHT OUTER JOIN service_required_attrs av " +
                "ON (an.id = av.attr_id) " +
                "WHERE av.service_id=?";
        List<Attribute> attrs = jdbcTemplate.query(attrQuery, new Object[] {id}, ATTR_MAPPER);
        service.setAttributes(attrs);

        return service;
    }

    @Override
    public List<Service> getServices() {
        //TODO better query
        String query = "SELECT * FROM services";
        List<Service> services = jdbcTemplate.query(query, new Object[] {}, MAPPER);

        for (Service service: services) {
            String attrQuery = "SELECT an.friendly_name, av.attr_value " +
                    "FROM attr_names an RIGHT OUTER JOIN service_required_attrs av " +
                    "ON (an.id = av.attr_id) " +
                    "WHERE av.service_id=?";
            List<Attribute> attrs = jdbcTemplate.query(attrQuery, new Object[] {service.getId()}, ATTR_MAPPER);
            service.setAttributes(attrs);
        }

        return services;
    }
}
