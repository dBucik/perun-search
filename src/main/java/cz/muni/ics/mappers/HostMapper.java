package cz.muni.ics.mappers;

import cz.muni.ics.models.Attribute;
import cz.muni.ics.models.Host;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HostMapper implements RowMapper<Host> {

    @Override
    public Host mapRow(@NotNull ResultSet rs, int i) throws SQLException {
        Host host = new Host();
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

        setCoreAttributes(host, json);
        host.setAttributes(attrs);

        return host;
    }

    private void setCoreAttributes(Host host, JSONObject json) {
        host.setId(json.getLong("id"));

        if (!(json.get("hostname") instanceof String)) {
            host.setHostname(json.get("hostname").toString());
        } else {
            host.setHostname(json.getString("hostname"));
        }

        if (!(json.get("dsc") instanceof String)) {
            host.setDescription(json.get("dsc").toString());
        } else {
            host.setDescription(json.getString("dsc"));
        }

        if (!(json.get("facility_id") instanceof Long)) {
            host.setFacilityId(null);
        } else {
            host.setFacilityId(json.getLong("facility_id"));
        }
    }
}
