package cz.muni.ics.JDBCTemplates;

import cz.muni.ics.DAOs.ExtSourceDAO;
import cz.muni.ics.mappers.AttributesToJsonMapper;
import cz.muni.ics.mappers.ExtSourceMapper;
import cz.muni.ics.models.AttributePair;
import cz.muni.ics.models.ExtSource;
import org.json.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExtSourceJdbcTemplate implements ExtSourceDAO {

    private static final ExtSourceMapper MAPPER = new ExtSourceMapper();
    private static final AttributesToJsonMapper ATTR_MAPPER = new AttributesToJsonMapper();

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public ExtSource getExtSource(Long id) {
        //TODO better query
        String query = "SELECT * FROM ext_sources WHERE id=?";
        ExtSource extSource = jdbcTemplate.queryForObject(query, new Object[] {id}, MAPPER);

        String attrQuery = "SELECT an.friendly_name, av.attr_value " +
                "FROM attr_names an RIGHT OUTER JOIN ext_sources_attributes av " +
                "ON (an.id = av.attr_id) " +
                "WHERE av.ext_sources_id=?";
        List<AttributePair> attrs = jdbcTemplate.query(attrQuery, new Object[] {id}, ATTR_MAPPER);
        Map<String, String> attrMap = new HashMap<>();
        for (AttributePair attr: attrs) {
            attrMap.put(attr.getAttrName(), attr.getAttrValue());
        }
        extSource.setAttributes(new JSONObject(attrMap));

        return extSource;
    }

    @Override
    public List<ExtSource> getExtSources() {
        //TODO better query
        String query = "SELECT * FROM ext_sources";
        List<ExtSource> extSources = jdbcTemplate.query(query, new Object[] {}, MAPPER);

        for (ExtSource es: extSources) {
            String attrQuery = "SELECT an.friendly_name, av.attr_value " +
                    "FROM attr_names an RIGHT OUTER JOIN ext_sources_attributes av " +
                    "ON (an.id = av.attr_id) " +
                    "WHERE av.ext_sources_id=?";
            List<AttributePair> attrs = jdbcTemplate.query(attrQuery, new Object[] {es.getId()}, ATTR_MAPPER);
            Map<String, String> attrMap = new HashMap<>();
            for (AttributePair attr: attrs) {
                attrMap.put(attr.getAttrName(), attr.getAttrValue());
            }
            es.setAttributes(new JSONObject(attrMap));
        }
        return extSources;
    }
}
