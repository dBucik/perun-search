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
        JSONObject entityJson = new JSONObject(rs.getString("entity"));
        RichUser res = MappersUtils.mapUser(entityJson, new RichUser());

        JSONObject attrsJson = new JSONObject(rs.getString("attrs"));
        res.setAttributes(MappersUtils.getAttributes(attrsJson));
        return res;
    }

}
