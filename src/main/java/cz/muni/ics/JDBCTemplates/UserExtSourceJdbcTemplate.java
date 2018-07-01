package cz.muni.ics.JDBCTemplates;

import cz.muni.ics.DAOs.UserExtSourceDAO;
import cz.muni.ics.mappers.AttributesToJsonMapper;
import cz.muni.ics.mappers.UserExtSourceMapper;
import cz.muni.ics.models.AttributePair;
import cz.muni.ics.models.ExtSource;
import cz.muni.ics.models.UserExtSource;
import org.json.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserExtSourceJdbcTemplate implements UserExtSourceDAO {

    private static final UserExtSourceMapper MAPPER = new UserExtSourceMapper();
    private static final AttributesToJsonMapper ATTR_MAPPER = new AttributesToJsonMapper();

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public UserExtSource getUserExtSource(Long id) {
        //TODO better query
        String query = "SELECT * FROM user_ext_sources WHERE id=?";
        UserExtSource ues = jdbcTemplate.queryForObject(query, new Object[] {id}, MAPPER);

        String attrQuery = "SELECT an.friendly_name, av.attr_value " +
                "FROM attr_names an RIGHT OUTER JOIN user_ext_source_attr_values av " +
                "ON (an.id = av.attr_id) " +
                "WHERE av.user_ext_source_id=?";
        List<AttributePair> attrs = jdbcTemplate.query(attrQuery, new Object[] {id}, ATTR_MAPPER);
        Map<String, String> attrMap = new HashMap<>();
        for (AttributePair attr: attrs) {
            attrMap.put(attr.getAttrName(), attr.getAttrValue());
        }
        ues.setAttributes(new JSONObject(attrMap));

        return ues;
    }

    @Override
    public List<UserExtSource> getUserExtSources() {
        //TODO better query
        String query = "SELECT * FROM user_ext_sources";
        List<UserExtSource> ueses = jdbcTemplate.query(query, new Object[] {}, MAPPER);
        for (UserExtSource ues: ueses) {
            String attrQuery = "SELECT an.friendly_name, av.attr_value " +
                    "FROM attr_names an RIGHT OUTER JOIN user_ext_source_attr_values av " +
                    "ON (an.id = av.attr_id) " +
                    "WHERE av.user_ext_source_id=?";
            List<AttributePair> attrs = jdbcTemplate.query(attrQuery, new Object[] {ues.getId()}, ATTR_MAPPER);
            Map<String, String> attrMap = new HashMap<>();
            for (AttributePair attr: attrs) {
                attrMap.put(attr.getAttrName(), attr.getAttrValue());
            }
            ues.setAttributes(new JSONObject(attrMap));
        }
        return ueses;
    }
}
