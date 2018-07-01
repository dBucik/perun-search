package cz.muni.ics.mappers;

import cz.muni.ics.models.Attribute;
import cz.muni.ics.models.ExtSource;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ExtSourceMapper implements RowMapper<ExtSource> {

    @NotNull
    @Override
    public ExtSource mapRow(@NotNull ResultSet rs, int i) throws SQLException {
        ExtSource extSource = new ExtSource();
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

        setCoreAttributes(extSource, json);
        extSource.setAttributes(attrs);

        return extSource;
    }

    private void setCoreAttributes(ExtSource extSource, JSONObject json) {
        extSource.setId(json.getLong("id"));

        if (!(json.get("name") instanceof String)) {
            extSource.setName(json.get("name").toString());
        } else {
            extSource.setName(json.getString("name"));
        }

        if (!(json.get("type") instanceof String)) {
            extSource.setType(json.get("type").toString());
        } else {
            extSource.setType(json.getString("type"));
        }
    }
}
