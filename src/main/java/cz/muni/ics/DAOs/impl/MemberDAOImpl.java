package cz.muni.ics.DAOs.impl;

import cz.muni.ics.DAOs.DAOUtils;
import cz.muni.ics.DAOs.MemberDAO;
import cz.muni.ics.exceptions.DatabaseIntegrityException;
import cz.muni.ics.mappers.entities.MemberMapper;
import cz.muni.ics.mappers.richEntities.RichMemberMapper;
import cz.muni.ics.models.Attribute;
import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.entities.Member;
import cz.muni.ics.models.richEntities.RichMember;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.IncorrectResultSetColumnCountException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class MemberDAOImpl implements MemberDAO {

    private static final MemberMapper MAPPER = new MemberMapper();
    private static final RichMemberMapper RICH_MAPPER = new RichMemberMapper();

    private static final Character ACTIVE = '1';
    private static final Character EXPIRED = '0';

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Member getMember(Long id) throws DatabaseIntegrityException {
        String where = "WHERE t.id = ?";
        String query = queryBuilder(where, false);

        try {
            return jdbcTemplate.queryForObject(query, new Object[]{id}, MAPPER);
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (IncorrectResultSetColumnCountException e) {
            throw new DatabaseIntegrityException("More members with same ID found [id: " + id + ']');
        }
    }

    @Override
    public List<Member> getMembers() {
        String query = queryBuilder(null, false);

        return jdbcTemplate.query(query, MAPPER);
    }

    @Override
    public List<Member> getMembersHavingAttrs(List<InputAttribute> attrs) {
        return new ArrayList<>(getRichMembersHavingAttrs(attrs));
    }

    @Override
    public List<Member> getMembersOfUser(Long userId) {
        String where = "WHERE t.user_id = ?";
        String query = queryBuilder(where, false);

        return jdbcTemplate.query(query, new Object[] {userId}, MAPPER);
    }

    @Override
    public List<Member> getMembersOfVo(Long voId) {
        String where = "WHERE t.vo_id = ?";
        String query = queryBuilder(where, false);

        return jdbcTemplate.query(query, new Object[] {voId}, MAPPER);
    }

    @Override
    public List<Member> getMembersByStatus(String status) {
        Character param = resolveStatusParam(status);
        String where = "WHERE t.status = ?";
        String query = queryBuilder(where, false);

        return jdbcTemplate.query(query, new Object[] { param }, MAPPER);
    }

    @Override
    public List<Member> getMembersBySponsored(boolean isSponsored) {
        String where = "WHERE t.sponsored = ?";
        String query = queryBuilder(where, false);

        return jdbcTemplate.query(query, new Object[] {isSponsored}, MAPPER);
    }

    /* RICH_MEMBER */

    @Override
    public RichMember getRichMember(Long id) throws DatabaseIntegrityException {
        String where = "WHERE t.id = ?";
        String query = queryBuilder(where, true);

        try {
            return jdbcTemplate.queryForObject(query, new Object[]{id}, RICH_MAPPER);
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (IncorrectResultSetColumnCountException e) {
            throw new DatabaseIntegrityException("More members with same ID found [id: " + id + ']');
        }
    }

    @Override
    public List<RichMember> getRichMembers() {
        String query = queryBuilder(null, true);

        return jdbcTemplate.query(query, RICH_MAPPER);
    }

    @Override
    public List<RichMember> getRichMembersHavingAttrs(List<InputAttribute> attrs) {
        //TODO improve
        List<RichMember> members = getRichMembers();
        List<Attribute> filter = DAOUtils.convertAttrsFromInput(attrs);

        members.removeIf(member -> {
            assert filter != null;
            return ! member.getAttributes().containsAll(filter);
        });

        return members;
    }

    @Override
    public List<RichMember> getRichMembersOfUser(Long userId) {
        String where = "WHERE t.user_id = ?";
        String query = queryBuilder(where, true);

        return jdbcTemplate.query(query, new Object[] {userId}, RICH_MAPPER);
    }

    @Override
    public List<RichMember> getRichMembersOfVo(Long voId) {
        String where = "WHERE t.vo_id = ?";
        String query = queryBuilder(where, true);

        return jdbcTemplate.query(query, new Object[] {voId}, RICH_MAPPER);
    }

    @Override
    public List<RichMember> getRichMembersByStatus(String status) {
        Character param = resolveStatusParam(status);
        String where = "WHERE t.status = ?";
        String query = queryBuilder(where, true);

        return jdbcTemplate.query(query, new Object[] { param }, RICH_MAPPER);
    }

    @Override
    public List<RichMember> getRichMembersBySponsored(boolean isSponsored) {
        String where = "WHERE t.sponsored = ?";
        String query = queryBuilder(where, true);

        return jdbcTemplate.query(query, new Object[] {isSponsored}, RICH_MAPPER);
    }

    /* ATTRIBUTES */

    @Override
    public List<Attribute> getMemberAttrs(Long id, List<String> attrs) throws DatabaseIntegrityException {
        //TODO: improve
        RichMember member = getRichMember(id);
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

    private String queryBuilder(String where, boolean withAttrs) {
        //TODO: check table names
        StringBuilder query = new StringBuilder();
        query.append("SELECT to_jsonb(t)");
        if (withAttrs) {
            query.append(" ||");
            query.append(" jsonb_build_object('attributes', json_object_agg(friendly_name, attr_value))");
        }
        query.append(" AS member");
        query.append(" FROM members t");
        if (withAttrs) {
            query.append(" JOIN member_attr_values av ON av.member_id = t.id");
            query.append(" JOIN attr_names an ON an.id = av.attr_id");
        }
        if (where != null) {
            query.append(' ').append(where.trim());
        }
        query.append(" GROUP BY t.id");
        return query.toString();
    }

    private Character resolveStatusParam(String status) {
        switch (status) {
            case "ACTIVE":
                return  ACTIVE;
            case "EXPIRED":
                return  EXPIRED;
            default:
                throw new IllegalArgumentException("ACTIVE or EXPIRED expected, got: " + status);
        }
    }

}
