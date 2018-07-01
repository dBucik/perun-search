package cz.muni.ics.mappers;

import cz.muni.ics.models.Attribute;
import cz.muni.ics.models.Vo;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class VoMapper implements RowMapper<Vo> {

    @NotNull
    @Override
    public Vo mapRow(@NotNull ResultSet rs, int i) throws SQLException {
        Vo vo = new Vo();
        JSONObject json = new JSONObject(rs.getString("vo"));
        JSONObject attrsJSON = json.getJSONObject("attributes");
        List<Attribute> attrs = new ArrayList<>();

        for (String key: attrsJSON.keySet()) {
            if (!(attrsJSON.get(key) instanceof String)) {
                attrs.add(new Attribute(key, attrsJSON.get(key).toString()));
            } else {
                attrs.add(new Attribute(key, attrsJSON.getString(key)));
            }
        }

        setCoreAttributes(vo, json);
        vo.setAttributes(attrs);

        return vo;
    }

    private void setCoreAttributes(Vo vo, JSONObject json) {
        vo.setId(json.getLong("id"));

        if (!(json.get("name") instanceof String)) {
            vo.setName(json.get("name").toString());
        } else {
            vo.setName(json.getString("name"));
        }

        if (!(json.get("short_name") instanceof String)) {
            vo.setShortName(json.get("short_name").toString());
        } else {
            vo.setShortName(json.getString("short_name"));
        }
    }
}
