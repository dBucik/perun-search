package cz.muni.ics.mappers;

import cz.muni.ics.models.UserExtSource;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UserExtSourceMapper implements RowMapper<UserExtSource> {

    @Override
    public UserExtSource mapRow(ResultSet resultSet, int i) throws SQLException {
        //todo
        return null;
    }
}
