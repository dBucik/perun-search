package cz.muni.ics.JDBCTemplates;

import cz.muni.ics.DAOs.ResourceDAO;
import cz.muni.ics.mappers.ResourceMapper;
import cz.muni.ics.models.Resource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class ResourceJdbcTemplate implements ResourceDAO {

    private static final ResourceMapper MAPPER = new ResourceMapper();

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Resource getResource(Long id) {
        //TODO query
        String query = "";
        Resource resource = jdbcTemplate.queryForObject(query, new Object[] {id}, MAPPER);
        return resource;
    }

    @Override
    public List<Resource> getResources() {
        //TODO query
        String query = "";
        List<Resource> resources = jdbcTemplate.query(query, new Object[] {}, MAPPER);
        return getResources();
    }
}
