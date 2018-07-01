package cz.muni.ics.mappers;

import cz.muni.ics.models.Attribute;
import cz.muni.ics.models.Member;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberMapper implements RowMapper<Member> {

    @NotNull
    @Override
    public Member mapRow(ResultSet rs, int i) throws SQLException {
        Member member = new Member();
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

        setCoreAttributes(member, json);
        member.setAttributes(attrs);

        return member;
    }

    private void setCoreAttributes(Member member, JSONObject json) {
        member.setId(json.getLong("id"));

        if (!(json.get("status") instanceof String)) {
            member.setStatus(json.get("status").toString());
        } else {
            member.setStatus(json.getString("status"));
        }

        if (!(json.get("vo_id") instanceof Long)) {
            member.setVoId(null);
        } else {
            member.setVoId(json.getLong("vo_id"));
        }

        if (!(json.get("user_id") instanceof Long)) {
            member.setUserId(null);
        } else {
            member.setUserId(json.getLong("user_id"));
        }

        if (json.getString("sponsored").equals("f")) {
            member.setSponsored(false);
        } else {
            member.setSponsored(true);
        }

    }
}
