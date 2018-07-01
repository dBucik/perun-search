package cz.muni.ics.JDBCTemplates;

import cz.muni.ics.DAOs.ExtSourceDAO;
import cz.muni.ics.mappers.ExtSourceMapper;
import cz.muni.ics.models.ExtSource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class ExtSourceJdbcTemplate implements ExtSourceDAO {

    private static final ExtSourceMapper MAPPER = new ExtSourceMapper();

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public ExtSource getExtSource(Long id) {
        //TODO query
        String query = "";
        ExtSource extSource = jdbcTemplate.queryForObject(query, new Object[] {id}, MAPPER);
        return extSource;
    }

    @Override
    public List<ExtSource> getExtSources() {
        //TODO query
        String query = "";
        List<ExtSource> extSources = jdbcTemplate.query(query, new Object[] {}, MAPPER);
        return extSources;
    }
}
