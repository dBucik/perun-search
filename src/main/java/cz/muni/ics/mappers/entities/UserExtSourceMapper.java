package cz.muni.ics.mappers.entities;

import cz.muni.ics.mappers.MappersUtils;
import cz.muni.ics.models.entities.UserExtSource;
import org.json.JSONObject;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserExtSourceMapper implements RowMapper<UserExtSource> {

    @Override
    public UserExtSource mapRow(ResultSet rs, int i) throws SQLException {
        JSONObject entityJson = new JSONObject(rs.getString("entity"));
        JSONObject attrsJson = new JSONObject(rs.getString("attrs"));

        UserExtSource res = MappersUtils.mapUserExtSource(entityJson);
        res.setAttributes(MappersUtils.getAttributes(attrsJson));

        return res;
    }

}
