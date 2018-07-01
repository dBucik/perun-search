package cz.muni.ics.JdbcTemplates;

import cz.muni.ics.DAOs.ResourceDAO;
import cz.muni.ics.mappers.AttributeMapper;
import cz.muni.ics.mappers.ResourceMapper;
import cz.muni.ics.models.Attribute;
import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.Resource;
import org.json.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResourceJdbcTemplate implements ResourceDAO {

    private static final ResourceMapper MAPPER = new ResourceMapper();
    private static final AttributeMapper ATTR_MAPPER = new AttributeMapper();

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Resource getResource(Long id) {
        //TODO better query
        String query = "SELECT * FROM resources WHERE id=?";
        Resource resource = jdbcTemplate.queryForObject(query, new Object[] {id}, MAPPER);

        String attrQuery = "SELECT an.friendly_name, av.attr_value " +
                "FROM attr_names an RIGHT OUTER JOIN resource_attr_values av " +
                "ON (an.id = av.attr_id) " +
                "WHERE av.resource_id=?";
        List<Attribute> attrs = jdbcTemplate.query(attrQuery, new Object[] {id}, ATTR_MAPPER);
        resource.setAttributes(attrs);

        return resource;
    }

    @Override
    public Resource getResourceByName(String name) {
        //TODO implement
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public List<Resource> getResources() {
        //TODO query
        String query = "SELECT * FROM resources";
        List<Resource> resources = jdbcTemplate.query(query, new Object[] {}, MAPPER);

        for (Resource resource: resources) {
            String attrQuery = "SELECT an.friendly_name, av.attr_value " +
                    "FROM attr_names an RIGHT OUTER JOIN resource_attr_values av " +
                    "ON (an.id = av.attr_id) " +
                    "WHERE av.resource_id=?";
            List<Attribute> attrs = jdbcTemplate.query(attrQuery, new Object[] {resource.getId()}, ATTR_MAPPER);
            resource.setAttributes(attrs);
        }

        return resources;
    }

    @Override
    public List<Attribute> getResourceAttrs(Long id, List<InputAttribute> attrs) {
        //TODO implement
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public List<Resource> getResourcesWithAttrs(List<InputAttribute> attrs) {
        //TODO implement
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public List<Resource> getResourcesOfFacility(Long facilityId) {
        //TODO implement
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public List<Resource> getResourcesOfVo(Long voId) {
        //TODO implement
        throw new UnsupportedOperationException("Not implemented");
    }
}
