package cz.muni.ics.mappers.entities;

import cz.muni.ics.mappers.MappersUtils;
import cz.muni.ics.models.entities.Vo;
import org.json.JSONObject;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VoMapper implements RowMapper<Vo> {

    @Override
    public Vo mapRow(ResultSet rs, int i) throws SQLException {
        JSONObject entityJson = new JSONObject(rs.getString("entity"));
        JSONObject attrsJson = new JSONObject(rs.getString("attrs"));

        Vo res = MappersUtils.mapVo(entityJson);
        res.setAttributes(MappersUtils.getAttributes(attrsJson));

        return res;
    }

}
