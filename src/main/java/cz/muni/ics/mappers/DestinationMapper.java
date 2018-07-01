package cz.muni.ics.mappers;

import cz.muni.ics.models.Destination;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DestinationMapper implements RowMapper<Destination> {

    @Override
    public Destination mapRow(ResultSet resultSet, int i) throws SQLException {
        //todo
        return null;
    }
}
