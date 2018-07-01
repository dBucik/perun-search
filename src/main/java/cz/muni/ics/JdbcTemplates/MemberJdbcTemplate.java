package cz.muni.ics.JdbcTemplates;

import cz.muni.ics.DAOs.MemberDAO;
import cz.muni.ics.mappers.AttributeMapper;
import cz.muni.ics.mappers.MemberMapper;
import cz.muni.ics.models.Attribute;
import cz.muni.ics.models.Member;
import org.json.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberJdbcTemplate implements MemberDAO {

    private static final MemberMapper MAPPER = new MemberMapper();
    private static final AttributeMapper ATTR_MAPPER = new AttributeMapper();

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Member getMember(Long id) {
        //TODO better query
        String query = "SELECT * FROM members WHERE id=?";
        Member member = jdbcTemplate.queryForObject(query, new Object[] {id}, MAPPER);

        String attrQuery = "SELECT an.friendly_name, av.attr_value " +
                "FROM attr_names an RIGHT OUTER JOIN member_attr_values av " +
                "ON (an.id = av.attr_id) " +
                "WHERE av.member_id=?";
        List<Attribute> attrs = jdbcTemplate.query(attrQuery, new Object[] {id}, ATTR_MAPPER);
        member.setAttributes(attrs);

        return member;
    }

    @Override
    public List<Member> getMembers() {
        //TODO better query
        String query = "SELECT * FROM members";
        List<Member> members = jdbcTemplate.query(query, new Object[] {}, MAPPER);

        for(Member member: members) {
            String attrQuery = "SELECT an.friendly_name, av.attr_value " +
                    "FROM attr_names an RIGHT OUTER JOIN member_attr_values av " +
                    "ON (an.id = av.attr_id) " +
                    "WHERE av.member_id=?";
            List<Attribute> attrs = jdbcTemplate.query(attrQuery, new Object[] {member.getId()}, ATTR_MAPPER);
            member.setAttributes(attrs);
        }

        return members;
    }
}
