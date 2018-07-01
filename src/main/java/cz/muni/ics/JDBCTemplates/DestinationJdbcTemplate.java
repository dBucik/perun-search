package cz.muni.ics.JDBCTemplates;

import cz.muni.ics.DAOs.DestinationDAO;
import cz.muni.ics.mappers.AttributesToJsonMapper;
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
        String query = "SELECT * FROM destinations WHERE id=?";
        Destination destination = jdbcTemplate.queryForObject(query, new Object[] {id}, MAPPER);

        return destination;
    }

    @Override
    public List<Destination> getDestinations() {
        String query = "SELECT * FROM destinations";
        List<Destination>  destinations = jdbcTemplate.query(query, new Object[] {}, MAPPER);

        return destinations;
    }
}
