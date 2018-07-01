package cz.muni.ics.mappers;

import cz.muni.ics.models.ExtSource;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class ExtSourceMapper implements RowMapper<ExtSource> {

    @NotNull
    @Override
    public ExtSource mapRow(@NotNull ResultSet rs, int i) throws SQLException {
        ExtSource es = new ExtSource();

        es.setId(rs.getLong("id"));
        es.setName(rs.getString("name"));
        es.setType(rs.getString("type"));

        return es;
    }
}
