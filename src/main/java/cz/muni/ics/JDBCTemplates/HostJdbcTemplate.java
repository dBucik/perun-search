package cz.muni.ics.JDBCTemplates;

import cz.muni.ics.DAOs.HostDAO;
import cz.muni.ics.mappers.HostMapper;
import cz.muni.ics.models.Host;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class HostJdbcTemplate implements HostDAO {

    private static final HostMapper MAPPER = new HostMapper();

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Host getHost(Long id) {
        //TODO query
        String query = "";
        Host host = jdbcTemplate.queryForObject(query, new Object[] {id}, MAPPER);
        return host;
    }

    @Override
    public List<Host> getHosts() {
        //TODO query
        String query = "";
        List<Host> hosts = jdbcTemplate.query(query, new Object[] {}, MAPPER);
        return hosts;
    }
}
