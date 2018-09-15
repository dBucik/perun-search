package cz.muni.ics.mappers.entities;

import cz.muni.ics.mappers.MappersUtils;
import cz.muni.ics.models.entities.ExtSource;
import org.json.JSONObject;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExtSourceMapper implements RowMapper<ExtSource> {

    @Override
    public ExtSource mapRow(ResultSet rs, int i) throws SQLException {
        JSONObject entityJson = new JSONObject(rs.getString("entity"));
        JSONObject attrsJson = new JSONObject(rs.getString("attrs"));

        ExtSource res = MappersUtils.mapExtSource(entityJson);
        res.setAttributes(MappersUtils.getAttributes(attrsJson));

        return res;
    }

}
