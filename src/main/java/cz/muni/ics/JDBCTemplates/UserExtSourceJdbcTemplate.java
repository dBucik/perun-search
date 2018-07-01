package cz.muni.ics.JDBCTemplates;

import cz.muni.ics.DAOs.UserExtSourceDAO;
import cz.muni.ics.mappers.UserExtSourceMapper;
import cz.muni.ics.models.UserExtSource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class UserExtSourceJdbcTemplate implements UserExtSourceDAO {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public UserExtSource getUserExtSource(Long id) {
        //TODO query
        String query = "";
        UserExtSource ues = jdbcTemplate.queryForObject(query, new Object[] {id}, new UserExtSourceMapper());
        return ues;
    }

    @Override
    public List<UserExtSource> getUserExtSources() {
        //TODO query
        String query = "";
        List<UserExtSource> ueses = jdbcTemplate.query(query, new Object[] {}, new UserExtSourceMapper());
        return ueses;
    }
}
