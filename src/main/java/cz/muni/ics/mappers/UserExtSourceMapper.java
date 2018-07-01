package cz.muni.ics.mappers;

import cz.muni.ics.models.Attribute;
import cz.muni.ics.models.UserExtSource;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserExtSourceMapper implements RowMapper<UserExtSource> {

    @NotNull
    @Override
    public UserExtSource mapRow(@NotNull ResultSet rs, int i) throws SQLException {
        UserExtSource ues = new UserExtSource();
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

        setCoreAttributes(ues, json);
        ues.setAttributes(attrs);

        return ues;
    }

    private void setCoreAttributes(UserExtSource ues, JSONObject json) {
        ues.setId(json.getLong("id"));

        if (!(json.get("login_ext") instanceof String)) {
            ues.setLoginExt(json.get("login_ext").toString());
        } else {
            ues.setLoginExt(json.getString("login_ext"));
        }

        if (!(json.get("user_id") instanceof Long)) {
            ues.setUserId(null);
        } else {
            ues.setUserId(json.getLong("user_id"));
        }

        if (!(json.get("ext_sources_id") instanceof Long)) {
            ues.setExtSourcesId(null);
        } else {
            ues.setExtSourcesId(json.getLong("ext_sources_id"));
        }

        if (!(json.get("loa") instanceof Short)) {
            ues.setLoa(null);
        } else {
            ues.setLoa(json.getNumber("loa").shortValue());
        }
    }
}
