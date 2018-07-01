package cz.muni.ics.mappers.entities;

import cz.muni.ics.mappers.MappersUtils;
import cz.muni.ics.models.entities.ExtSource;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExtSourceMapper implements RowMapper<ExtSource> {

    @NotNull
    @Override
    public ExtSource mapRow(@NotNull ResultSet rs, int i) throws SQLException {
        JSONObject json = new JSONObject(rs.getString("extSource"));

        return MappersUtils.mapExtSource(json, new ExtSource());
    }

}
