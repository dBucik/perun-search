package cz.muni.ics.mappers;

import cz.muni.ics.models.Resource;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResourceMapper implements RowMapper<Resource> {

    @Override
    public Resource mapRow(ResultSet resultSet, int i) throws SQLException {
        //todo
        return null;
    }
}
