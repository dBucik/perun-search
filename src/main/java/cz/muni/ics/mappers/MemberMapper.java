package cz.muni.ics.mappers;

import cz.muni.ics.models.Member;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberMapper implements RowMapper<Member> {

    @Override
    public Member mapRow(ResultSet resultSet, int i) throws SQLException {
        Member member = new Member();

        member.setId(resultSet.getLong("id"));
        member.setVoId(resultSet.getLong("vo_id"));
        member.setUserId(resultSet.getLong("user_id"));
        if (resultSet.getString("sponsored").equals("f")) {
            member.setSponsored(false);
        } else {
            member.setSponsored(true);
        }

        return member;
    }
}
