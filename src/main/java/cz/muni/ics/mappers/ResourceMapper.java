package cz.muni.ics.mappers;

import cz.muni.ics.models.Attribute;
import cz.muni.ics.models.Resource;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResourceMapper implements RowMapper<Resource> {

    @NotNull
    @Override
    public Resource mapRow(@NotNull ResultSet rs, int i) throws SQLException {
        Resource resource = new Resource();
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

        setCoreAttributes(resource, json);
        resource.setAttributes(attrs);

        return resource;
    }

    private void setCoreAttributes(Resource resource, JSONObject json) {
        resource.setId(json.getLong("id"));

        if (!(json.get("name") instanceof String)) {
            resource.setName(json.get("name").toString());
        } else {
            resource.setName(json.getString("name"));
        }

        if (!(json.get("dsc") instanceof String)) {
            resource.setDescription(json.get("dsc").toString());
        } else {
            resource.setDescription(json.getString("dsc"));
        }

        if (!(json.get("facility_id") instanceof Long)) {
            resource.setFacilityId(null);
        } else {
            resource.setFacilityId(json.getLong("facility_id"));
        }

        if (!(json.get("vo_id") instanceof Long)) {
            resource.setVoId(null);
        } else {
            resource.setVoId(json.getLong("vo_id"));
        }
    }
}
