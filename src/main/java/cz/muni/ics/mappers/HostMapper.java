package cz.muni.ics.mappers;

import cz.muni.ics.models.Host;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HostMapper implements RowMapper<Host> {

    @Override
    public Host mapRow(ResultSet resultSet, int i) throws SQLException {
        //todo
        return null;
    }
}
