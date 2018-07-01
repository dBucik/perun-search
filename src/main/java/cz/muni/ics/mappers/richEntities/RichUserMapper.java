package cz.muni.ics.mappers.richEntities;

import cz.muni.ics.mappers.MappersUtils;
import cz.muni.ics.models.richEntities.RichUser;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RichUserMapper implements RowMapper<RichUser> {

    @NotNull
    @Override
    public RichUser mapRow(@NotNull ResultSet rs, int i) throws SQLException {
        JSONObject json = new JSONObject(rs.getString("user"));
        RichUser user = MappersUtils.mapUser(json, new RichUser());
        user.setAttributes(MappersUtils.getAttributes(json.getJSONObject("attributes")));

        return user;
    }

}
