package cz.muni.ics.JDBCTemplates;

import cz.muni.ics.DAOs.MemberDAO;
import cz.muni.ics.mappers.MemberMapper;
import cz.muni.ics.models.Member;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class MemberJdbcTemplate implements MemberDAO {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Member getMember(Long id) {
        //TODO query
        String query = "";
        Member member = jdbcTemplate.queryForObject(query, new Object[] {id}, new MemberMapper());
        return member;
    }

    @Override
    public List<Member> getMembers() {
        //TODO query
        String query = "";
        List<Member> members = jdbcTemplate.query(query, new Object[] {}, new MemberMapper());
        return members;
    }
}
