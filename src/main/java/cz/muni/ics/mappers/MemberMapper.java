package cz.muni.ics.mappers;

import cz.muni.ics.models.Member;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberMapper implements RowMapper<Member> {

    @NotNull
    @Override
    public Member mapRow(ResultSet rs, int i) throws SQLException {
        Member member = new Member();

        member.setId(rs.getLong("id"));
        member.setVoId(rs.getLong("vo_id"));
        member.setUserId(rs.getLong("user_id"));
        if (rs.getString("sponsored").equals("f")) {
            member.setSponsored(false);
        } else {
            member.setSponsored(true);
        }

        return member;
    }
}
