package cz.muni.ics.mappers.richEntities;

import cz.muni.ics.mappers.MappersUtils;
import cz.muni.ics.models.richEntities.RichMember;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RichMemberMapper implements RowMapper<RichMember> {

    @NotNull
    @Override
    public RichMember mapRow(@NotNull ResultSet rs, int i) throws SQLException {
        JSONObject json = new JSONObject(rs.getString("member"));
        RichMember member = MappersUtils.mapMember(json, new RichMember());
        member.setAttributes(MappersUtils.getAttributes(json.getJSONObject("attributes")));

        return member;
    }

}
