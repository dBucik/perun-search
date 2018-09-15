package cz.muni.ics.mappers.entities;

import cz.muni.ics.mappers.MappersUtils;
import cz.muni.ics.models.entities.Host;
import org.json.JSONObject;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HostMapper implements RowMapper<Host> {

    @Override
    public Host mapRow(ResultSet rs, int i) throws SQLException {
        JSONObject entityJson = new JSONObject(rs.getString("entity"));
        JSONObject attrsJson = new JSONObject(rs.getString("attrs"));

        Host res = MappersUtils.mapHost(entityJson);
        res.setAttributes(MappersUtils.getAttributes(attrsJson));

        return res;
    }

}
