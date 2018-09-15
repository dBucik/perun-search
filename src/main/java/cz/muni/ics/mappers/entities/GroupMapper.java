package cz.muni.ics.mappers.entities;

import cz.muni.ics.mappers.MappersUtils;
import cz.muni.ics.models.entities.Group;
import org.json.JSONObject;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GroupMapper implements RowMapper<Group> {

    @Override
    public Group mapRow(ResultSet rs, int i) throws SQLException {
        JSONObject entityJson = new JSONObject(rs.getString("entity"));
		JSONObject attrsJson = new JSONObject(rs.getString("attrs"));

        Group res = MappersUtils.mapGroup(entityJson);
        res.setAttributes(MappersUtils.getAttributes(attrsJson));

        return res;
    }

}
