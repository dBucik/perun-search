package cz.muni.ics.mappers;

import cz.muni.ics.models.ExtSource;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UserExtSourceMapper implements RowMapper<ExtSource> {

    @Override
    public ExtSource mapRow(ResultSet resultSet, int i) throws SQLException {
        //todo
        return null;
    }
}
