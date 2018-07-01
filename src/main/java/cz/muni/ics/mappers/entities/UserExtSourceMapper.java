package cz.muni.ics.mappers.entities;

import cz.muni.ics.mappers.MappersUtils;
import cz.muni.ics.models.entities.UserExtSource;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserExtSourceMapper implements RowMapper<UserExtSource> {

    @NotNull
    @Override
    public UserExtSource mapRow(@NotNull ResultSet rs, int i) throws SQLException {
        JSONObject json = new JSONObject(rs.getString("userExtSource"));

        return MappersUtils.mapUserExtSource(json, new UserExtSource());
    }

}
