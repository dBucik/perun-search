package cz.muni.ics.JDBCTemplates;

import cz.muni.ics.DAOs.DestinationDAO;
import cz.muni.ics.mappers.DestinationMapper;
import cz.muni.ics.models.Destination;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class DestinationJdbcTemplate implements DestinationDAO {

    private static final DestinationMapper MAPPER = new DestinationMapper();

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Destination getDestination(Long id) {
        //TODO query
        String query = "";
        Destination destination = jdbcTemplate.queryForObject(query, new Object[] {id}, MAPPER);
        return destination;
    }

    @Override
    public List<Destination> getDestinations() {
        //TODO query
        String query = "";
        List<Destination>  destinations = jdbcTemplate.query(query, new Object[] {}, MAPPER);
        return destinations;
    }
}
