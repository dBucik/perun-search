package cz.muni.ics.mappers;

import cz.muni.ics.models.Attribute;
import cz.muni.ics.models.Group;
import cz.muni.ics.models.Vo;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupMapper implements RowMapper<Group> {

    @NotNull
    @Override
    public Group mapRow(@NotNull ResultSet rs, int i) throws SQLException {
        Group group = new Group();
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

        setCoreAttributes(group, json);
        group.setAttributes(attrs);

        return group;
    }

    private void setCoreAttributes(Group group, JSONObject json) {
        group.setId(json.getLong("id"));

        if (!(json.get("name") instanceof String)) {
            group.setName(null);
        } else {
            group.setName(json.getString("name"));
        }

        if (!(json.get("dsc") instanceof String)) {
            group.setDescription(null);
        } else {
            group.setDescription(json.getString("dsc"));
        }

        if (!(json.get("vo_id") instanceof Long)) {
            group.setVoId(null);
        } else {
            group.setVoId(json.getLong("vo_id"));
        }

        if (!(json.get("parent_group_id") instanceof Long)) {
            group.setParentGroupId(null);
        } else {
            group.setParentGroupId(json.getLong("parent_group_id"));
        }
    }
}
