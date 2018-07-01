package cz.muni.ics.JdbcTemplates;

import cz.muni.ics.DAOs.HostDAO;
import cz.muni.ics.mappers.AttributeMapper;
import cz.muni.ics.mappers.HostMapper;
import cz.muni.ics.models.Attribute;
import cz.muni.ics.models.Host;
import org.json.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HostJdbcTemplate implements HostDAO {

    private static final HostMapper MAPPER = new HostMapper();
    private static final AttributeMapper ATTR_MAPPER = new AttributeMapper();

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Host getHost(Long id) {
        //TODO better query
        String query = "SELECT *  FROM hosts WHERE id=?";
        Host host = jdbcTemplate.queryForObject(query, new Object[] {id}, MAPPER);

        String attrQuery = "SELECT an.friendly_name, av.attr_value " +
                "FROM attr_names an RIGHT OUTER JOIN host_attr_values av " +
                "ON (an.id = av.attr_id) " +
                "WHERE av.host_id=?";
        List<Attribute> attrs = jdbcTemplate.query(attrQuery, new Object[] {id}, ATTR_MAPPER);
        Map<String, String> attrMap = new HashMap<>();
        for (Attribute attr: attrs) {
            attrMap.put(attr.getAttrName(), attr.getAttrValue());
        }
        host.setAttributes(new JSONObject(attrMap));

        return host;
    }

    @Override
    public List<Host> getHosts() {
        //TODO better query
        String query = "SELECT * FROM hosts";
        List<Host> hosts = jdbcTemplate.query(query, new Object[] {}, MAPPER);

        for (Host host: hosts) {
            String attrQuery = "SELECT an.friendly_name, av.attr_value " +
                    "FROM attr_names an RIGHT OUTER JOIN host_attr_values av " +
                    "ON (an.id = av.attr_id) " +
                    "WHERE av.host_id=?";
            List<Attribute> attrs = jdbcTemplate.query(attrQuery, new Object[] { host.getId()}, ATTR_MAPPER);
            Map<String, String> attrMap = new HashMap<>();
            for (Attribute attr: attrs) {
                attrMap.put(attr.getAttrName(), attr.getAttrValue());
            }
            host.setAttributes(new JSONObject(attrMap));
        }
        return hosts;
    }
}
