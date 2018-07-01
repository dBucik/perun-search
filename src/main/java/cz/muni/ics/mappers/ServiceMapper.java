package cz.muni.ics.mappers;

import cz.muni.ics.models.Attribute;
import cz.muni.ics.models.Service;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceMapper implements RowMapper<Service> {

    @NotNull
    @Override
    public Service mapRow(@NotNull ResultSet rs, int i) throws SQLException {
        Service service = new Service();
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

        setCoreAttributes(service, json);
        service.setAttributes(attrs);

        return service;
    }

    private void setCoreAttributes(Service service, JSONObject json) {
        service.setId(json.getLong("id"));

        if (!(json.get("name") instanceof String)) {
            service.setName(json.get("name").toString());
        } else {
            service.setName(json.getString("name"));
        }

        if (!(json.get("owner_id") instanceof Long)) {
            service.setOwnerId(null);
        } else {
            service.setOwnerId(json.getLong("owner_id"));
        }
    }
}
