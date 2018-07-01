package cz.muni.ics.mappers;

import cz.muni.ics.models.ExtSource;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class ExtSourceMapper implements RowMapper<ExtSource> {

    @Override
    public ExtSource mapRow(ResultSet resultSet, int i) throws SQLException {
        ExtSource es = new ExtSource();

        es.setId(resultSet.getLong("id"));
        es.setName(resultSet.getString("name"));
        es.setType(resultSet.getString("type"));

        return es;
    }
}
