package cz.muni.ics.DAOs.impl;

import cz.muni.ics.DAOs.MemberDAO;
import cz.muni.ics.Utils;
import cz.muni.ics.exceptions.DatabaseIntegrityException;
import cz.muni.ics.mappers.MemberMapper;
import cz.muni.ics.models.Attribute;
import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.Member;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.IncorrectResultSetColumnCountException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class MemberDAOImpl implements MemberDAO {

    private static final MemberMapper MAPPER = new MemberMapper();

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Member getMember(Long id, boolean withAttrs) throws DatabaseIntegrityException {
        String where = "WHERE t.id = ?";
        String query = queryBuilder(where, withAttrs);

        try {
            return jdbcTemplate.queryForObject(query, new Object[]{id}, MAPPER);
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (IncorrectResultSetColumnCountException e) {
            throw new DatabaseIntegrityException("More members with same ID found [id: " + id + ']');
        }
    }

    @Override
    public List<Member> getMembers(boolean withAttrs) {
        String query = queryBuilder(null);

        return jdbcTemplate.query(query, MAPPER);
    }

    @Override
    public List<Attribute> getMemberAttrs(Long id, List<String> attrs) throws DatabaseIntegrityException {
        //TODO: improve
        Member member = getMember(id, true);
        List<Attribute> result = new ArrayList<>();

        if (attrs == null) {
            result.add(new Attribute("id", member.getId().toString()));
            result.add(new Attribute("userId", member.getUserId().toString()));
            result.add(new Attribute("voId", member.getVoId().toString()));
            result.add(new Attribute("status", member.getStatus()));
            result.add(new Attribute("sponsored", member.getSponsored().toString()));
            result.addAll(member.getAttributes());
        } else {
            result.addAll(member.getAttributesByKeys(attrs));
        }

        return result;
    }

    @Override
    public List<Member> getMembersWithAttrs(List<InputAttribute> attrs, boolean withAttrs) {
        //TODO improve
        List<Member> members = getMembers(withAttrs);
        List<Attribute> filter = Utils.convertAttrsFromInput(attrs);

        members.removeIf(member -> {
           assert filter != null;
           return ! member.getAttributes().containsAll(filter);
        });

        return members;
    }

    @Override
    public List<Member> getMembersOfUser(Long userId, boolean withAttrs) {
        String where = "WHERE t.user_id = ?";
        String query = queryBuilder(where, withAttrs);

        return jdbcTemplate.query(query, new Object[] {userId}, MAPPER);
    }

    @Override
    public List<Member> getMembersOfVo(Long voId, boolean withAttrs) {
        String where = "WHERE t.vo_id = ?";
        String query = queryBuilder(where, withAttrs);

        return jdbcTemplate.query(query, new Object[] {voId}, MAPPER);
    }

    @Override
    public List<Member> getMembersByStatus(String status, boolean withAttrs) {
        char param;
        switch (status) {
            case "ACTIVE":
                param = '1';
                break;
            case "EXPIRED":
                param = '0';
                break;
            default:
                throw new IllegalArgumentException("ACTIVE or EXPIRED expected, got: " + status);
        }

        String where = "WHERE t.status = ?";
        String query = queryBuilder(where, withAttrs);

        return jdbcTemplate.query(query, new Object[] {param}, MAPPER);
    }

    @Override
    public List<Member> getMembersBySponsored(boolean isSponsored, boolean withAttrs) {
        String where = "WHERE t.sponsored = ?";
        String query = queryBuilder(where, withAttrs);

        return jdbcTemplate.query(query, new Object[] {isSponsored}, MAPPER);
    }

    private String queryBuilder(String where, boolean withAttrs) {
        //TODO: check table names
        StringBuilder query = new StringBuilder();
        query.append("SELECT to_jsonb(t)");
        if (withAttrs) {
            query.append(" || ");
            query.append("jsonb_build_object('attributes', json_object_agg(friendly_name, attr_value)) AS member ");
        }
        query.append("FROM members t ");
        if (withAttrs) {
            query.append("JOIN member_attr_values av ON av.member_id = t.id ");
            query.append("JOIN attr_names an ON an.id = av.attr_id ");
        }
        if (where != null) {
            query.append(where).append(' ');
        }
        query.append("GROUP BY t.id");
        return query.toString();
    }
}
