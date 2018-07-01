package cz.muni.ics.JdbcTemplates;

import cz.muni.ics.DAOs.UserDAO;
import cz.muni.ics.mappers.AttributeMapper;
import cz.muni.ics.mappers.UserMapper;
import cz.muni.ics.models.Attribute;
import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.User;
import org.json.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserJdbcTemplate implements UserDAO {

    private static final UserMapper MAPPER = new UserMapper();
    private static final AttributeMapper ATTR_MAPPER = new AttributeMapper();

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public User getUser(Long id) {
        //TODO better query
        String query = "SELECT * FROM users WHERE id=?";
        User user = jdbcTemplate.queryForObject(query, new Object[] {id}, MAPPER);

        String attrQuery = "SELECT an.friendly_name, av.attr_value " +
                "FROM attr_names an RIGHT OUTER JOIN user_attr_values av " +
                "ON (an.id = av.attr_id) " +
                "WHERE av.user_id=?";
        List<Attribute> attrs = jdbcTemplate.query(attrQuery, new Object[] {id}, ATTR_MAPPER);
        user.setAttributes(attrs);

        return user;
    }

    @Override
    public List<User> getUsers() {
        //TODO better query
        String query = "SELECT * FROM users";
        List<User> users = jdbcTemplate.query(query, new Object[] {}, MAPPER);

        for (User user: users) {
            String attrQuery = "SELECT an.friendly_name, av.attr_value " +
                    "FROM attr_names an RIGHT OUTER JOIN user_attr_values av " +
                    "ON (an.id = av.attr_id) " +
                    "WHERE av.user_id=?";
            List<Attribute> attrs = jdbcTemplate.query(attrQuery, new Object[] {user.getId()}, ATTR_MAPPER);
            user.setAttributes(attrs);
        }

        return users;
    }

    @Override
    public List<Attribute> getUserAttrs(Long id, List<InputAttribute> attrs) {
        //TODO implement
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public List<User> getUsersWithAttrs(List<InputAttribute> attrs) {
        //TODO implement
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public List<User> getUsersByName(String titleBefore, String firstName, String middleName, String lastName, String titleAfter) {
        //TODO implement
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public List<User> getUsersByUserAcc(boolean isuserAcc) {
        //TODO implement
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public List<User> getUsersBySponsoredAcc(boolean isSponsoredAcc) {
        //TODO implement
        throw new UnsupportedOperationException("Not implemented");
    }
}
