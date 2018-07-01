package cz.muni.ics.mappers;

import cz.muni.ics.models.Attribute;
import cz.muni.ics.models.User;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserMapper implements RowMapper<User> {

    @NotNull
    @Override
    public User mapRow(@NotNull ResultSet rs, int i) throws SQLException {
        User user = new User();
        JSONObject json = new JSONObject(rs.getString("user"));
        JSONObject attrsJSON = json.getJSONObject("attributes");
        List<Attribute> attrs = new ArrayList<>();

        for (String key: attrsJSON.keySet()) {
            if (!(attrsJSON.get(key) instanceof String)) {
                attrs.add(new Attribute(key, attrsJSON.get(key).toString()));
            } else {
                attrs.add(new Attribute(key, attrsJSON.getString(key)));
            }
        }

        setCoreAttributes(user, json);
        user.setAttributes(attrs);

        return user;
    }

    private void setCoreAttributes(User user, JSONObject json) {
        user.setId(json.getLong("id"));

        if (!(json.get("first_name") instanceof String)) {
            user.setFirstName(json.get("first_name").toString());
        } else {
            user.setFirstName(json.getString("first_name"));
        }

        if (!(json.get("last_name") instanceof String)) {
            user.setLastName(json.get("last_name").toString());
        } else {
            user.setLastName(json.getString("last_name"));
        }

        if (!(json.get("middle_name") instanceof String)) {
            user.setMiddleName(json.get("middle_name").toString());
        } else {
            user.setMiddleName(json.getString("middle_name"));
        }

        if (!(json.get("title_before") instanceof String)) {
            user.setTitleBefore(json.get("title_before").toString());
        } else {
            user.setTitleBefore(json.getString("title_before"));
        }

        if (!(json.get("title_after") instanceof String)) {
            user.setTitleAfter(json.get("title_after").toString());
        } else {
            user.setTitleAfter(json.getString("title_after"));
        }

        if (json.getString("service_acc").equals("0")) {
            user.setServiceAcc(false);
        } else {
            user.setServiceAcc(true);
        }

        if (json.getString("sponsored_acc").equals("0")) {
            user.setSponsoredAcc(false);
        } else {
            user.setSponsoredAcc(true);
        }
    }
}
