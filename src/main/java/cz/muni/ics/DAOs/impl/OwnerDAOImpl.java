package cz.muni.ics.DAOs.impl;

import cz.muni.ics.DAOs.OwnerDAO;
import cz.muni.ics.exceptions.DatabaseIntegrityException;
import cz.muni.ics.mappers.entities.OwnerMapper;
import cz.muni.ics.models.entities.Owner;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.IncorrectResultSetColumnCountException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class OwnerDAOImpl implements OwnerDAO {

    private static final OwnerMapper MAPPER = new OwnerMapper();

    private JdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Owner getOwner(Long id) throws DatabaseIntegrityException {
        String query = "SELECT * FROM owners WHERE id=?";

        try {
            return jdbcTemplate.queryForObject(query, new Object[]{id}, MAPPER);
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (IncorrectResultSetColumnCountException e) {
            throw new DatabaseIntegrityException("More owners with same ID found [id: " + id + ']');
        }
    }

    @Override
    public List<Owner> getOwnersByName(String name) {
        name = '%' + name + '%';
        String query = "SELECT * FROM owners WHERE upper(name) LIKE upper(?)";

        return jdbcTemplate.query(query, new Object[] {name}, MAPPER);
    }

    @Override
    public List<Owner> getOwnersByType(String type) {
        type = '%' + type + '%';
        String query = "SELECT * FROM owners WHERE upper(type) LIKE upper(?)";

        return jdbcTemplate.query(query, new Object[] {type}, MAPPER);
    }
}
