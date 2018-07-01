package cz.muni.ics.JDBCTemplates;

import cz.muni.ics.DAOs.UserExtSourceDAO;
import cz.muni.ics.mappers.UserExtSourceMapper;
import cz.muni.ics.models.ExtSource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class UserExtSourceJdbcTemplate implements UserExtSourceDAO {

    private static final UserExtSourceMapper MAPPER = new UserExtSourceMapper();

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public ExtSource getUserExtSource(Long id) {
        //TODO query
        String query = "";
        ExtSource ues = jdbcTemplate.queryForObject(query, new Object[] {id}, MAPPER);
        return ues;
    }

    @Override
    public List<ExtSource> getUserExtSources() {
        //TODO query
        String query = "";
        List<ExtSource> ueses = jdbcTemplate.query(query, new Object[] {}, MAPPER);
        return ueses;
    }
}
