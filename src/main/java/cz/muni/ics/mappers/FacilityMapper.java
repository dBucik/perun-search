package cz.muni.ics.mappers;

import cz.muni.ics.models.Attribute;
import cz.muni.ics.models.Facility;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FacilityMapper implements RowMapper<Facility> {

    @NotNull
    @Override
    public Facility mapRow(@NotNull ResultSet rs, int i) throws SQLException {
        Facility facility = new Facility();
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

        setCoreAttributes(facility, json);
        facility.setAttributes(attrs);

        return facility;
    }

    private void setCoreAttributes(Facility facility, JSONObject json) {
        facility.setId(json.getLong("id"));

        if (!(json.get("name") instanceof String)) {
            facility.setName(json.get("name").toString());
        } else {
            facility.setName(json.getString("name"));
        }

        if (!(json.get("dsc") instanceof String)) {
            facility.setDescription(json.get("dsc").toString());
        } else {
            facility.setDescription(json.getString("dsc"));
        }
    }
}
