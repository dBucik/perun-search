package cz.muni.ics.JDBCTemplates;

import cz.muni.ics.DAOs.UserDAO;
import cz.muni.ics.mappers.UserMapper;
import cz.muni.ics.models.User;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class UserJdbcTemplate implements UserDAO {

    private static final UserMapper MAPPER = new UserMapper();

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public User getUser(Long id) {
        //TODO query
        String query = "";
        User user = jdbcTemplate.queryForObject(query, new Object[] {id}, MAPPER);
        return user;
    }

    @Override
    public List<User> getUsers() {
        //TODO query
        String query = "";
        List<User> users = jdbcTemplate.query(query, new Object[] {}, MAPPER);
        return users;
    }
}
