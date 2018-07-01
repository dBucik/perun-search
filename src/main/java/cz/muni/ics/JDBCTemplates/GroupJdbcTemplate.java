package cz.muni.ics.JDBCTemplates;

import cz.muni.ics.DAOs.GroupDAO;
import cz.muni.ics.mappers.AttributesToJsonMapper;
import cz.muni.ics.mappers.GroupMapper;
import cz.muni.ics.models.AttributePair;
import cz.muni.ics.models.Group;
import org.json.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupJdbcTemplate implements GroupDAO {

    private static final GroupMapper MAPPER = new GroupMapper();
    private static final AttributesToJsonMapper ATTR_MAPPER = new AttributesToJsonMapper();

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Group getGroup(Long id) {
        //TODO better query
        String query = "SELECT * FROM groups WHERE id=?";
        Group group = jdbcTemplate.queryForObject(query, new Object[] {id}, MAPPER);

        String attrQuery = "SELECT an.friendly_name, av.attr_value " +
                "FROM attr_names an RIGHT OUTER JOIN group_attr_values av " +
                "ON (an.id = av.attr_id) " +
                "WHERE av.group_id=?";
        List<AttributePair> attrs = jdbcTemplate.query(attrQuery, new Object[] {id}, ATTR_MAPPER);
        Map<String, String> attrMap = new HashMap<>();
        for (AttributePair attr: attrs) {
            attrMap.put(attr.getAttrName(), attr.getAttrValue());
        }
        group.setAttributes(new JSONObject(attrMap));

        return group;
    }

    @Override
    public List<Group> getGroups() {
        //TODO query;
        String query = "SELECT * FROM groups";
        List<Group> groups = jdbcTemplate.query(query, new Object[] {}, MAPPER);

        for (Group group: groups) {
            String attrQuery = "SELECT an.friendly_name, av.attr_value " +
                    "FROM attr_names an RIGHT OUTER JOIN group_attr_values av " +
                    "ON (an.id = av.attr_id) " +
                    "WHERE av.group_id=?";
            List<AttributePair> attrs = jdbcTemplate.query(attrQuery, new Object[] {group.getId()}, ATTR_MAPPER);
            Map<String, String> attrMap = new HashMap<>();
            for (AttributePair attr: attrs) {
                attrMap.put(attr.getAttrName(), attr.getAttrValue());
            }
            group.setAttributes(new JSONObject(attrMap));
        }

        return groups;
    }
}
