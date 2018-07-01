package cz.muni.ics.DAOs.impl;

import cz.muni.ics.DAOs.DestinationDAO;
import cz.muni.ics.exceptions.DatabaseIntegrityException;
import cz.muni.ics.mappers.entities.DestinationMapper;
import cz.muni.ics.models.entities.Destination;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.IncorrectResultSetColumnCountException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class DestinationDAOImpl implements DestinationDAO {

    private static final DestinationMapper MAPPER = new DestinationMapper();

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Destination getDestination(Long id) throws DatabaseIntegrityException {
        String query = "SELECT * FROM destinations WHERE id=?";
        try {
            return jdbcTemplate.queryForObject(query, new Object[] {id}, MAPPER);
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (IncorrectResultSetColumnCountException e) {
            throw new DatabaseIntegrityException("", e);
        }
    }

    @Override
    public List<Destination> getDestinationsByName(String name) {
        String query = "SELECT * FROM destinations WHERE upper(name) LIKE upper(?)";
        return jdbcTemplate.query(query, new Object[] {name}, MAPPER);
    }

    @Override
    public List<Destination> getDestinations() {
        String query = "SELECT * FROM destinations";

        return jdbcTemplate.query(query, new Object[] {}, MAPPER);
    }
}
