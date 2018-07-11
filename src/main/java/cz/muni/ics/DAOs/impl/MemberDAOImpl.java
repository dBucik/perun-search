package cz.muni.ics.DAOs.impl;

import cz.muni.ics.DAOs.DAOUtils;
import cz.muni.ics.DAOs.MemberDAO;
import cz.muni.ics.exceptions.DatabaseIntegrityException;
import cz.muni.ics.mappers.entities.MemberMapper;
import cz.muni.ics.mappers.richEntities.RichMemberMapper;
import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.PerunEntityType;
import cz.muni.ics.models.attributes.PerunAttribute;
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

    private JdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Member getMember(Long id) throws DatabaseIntegrityException {
        String where = "WHERE t.id = ?";
        String query = DAOUtils.simpleQueryBuilder(where, PerunEntityType.MEMBER);

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
        String query = DAOUtils.simpleQueryBuilder(null, PerunEntityType.MEMBER);

        return jdbcTemplate.query(query, MAPPER);
    }

    @Override
    public List<Member> getMembersHavingAttrs(List<InputAttribute> attrs) {
        return new ArrayList<>(getCompleteRichMembersHavingAttrs(attrs));
    }

    @Override
    public List<Member> getMembersOfUser(Long userId) {
        String where = "WHERE t.user_id = ?";
        String query = DAOUtils.simpleQueryBuilder(where, PerunEntityType.MEMBER);

        return jdbcTemplate.query(query, new Object[] {userId}, MAPPER);
    }

    @Override
    public List<Member> getMembersOfVo(Long voId) {
        String where = "WHERE t.vo_id = ?";
        String query = DAOUtils.simpleQueryBuilder(where, PerunEntityType.MEMBER);

        return jdbcTemplate.query(query, new Object[] {voId}, MAPPER);
    }

    @Override
    public List<Member> getMembersByStatus(String status) {
        Character param = resolveStatusParam(status);
        String where = "WHERE t.status = ?";
        String query = DAOUtils.simpleQueryBuilder(where, PerunEntityType.MEMBER);

        return jdbcTemplate.query(query, new Object[] { param }, MAPPER);
    }

    @Override
    public List<Member> getMembersBySponsored(boolean isSponsored) {
        String where = "WHERE t.sponsored = ?";
        String query = DAOUtils.simpleQueryBuilder(where, PerunEntityType.MEMBER);

        return jdbcTemplate.query(query, new Object[] {isSponsored}, MAPPER);
    }

    /* COMPLETE_RICH_MEMBER */

    @Override
    public RichMember getCompleteRichMember(Long id) throws DatabaseIntegrityException {
        String entityWhere = "WHERE t.id = ?";
        String query = DAOUtils.queryBuilder(entityWhere, null, PerunEntityType.MEMBER);

        try {
            return jdbcTemplate.queryForObject(query, new Object[]{id}, RICH_MAPPER);
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (IncorrectResultSetColumnCountException e) {
            throw new DatabaseIntegrityException("More members with same ID found [id: " + id + ']');
        }
    }

    @Override
    public List<RichMember> getCompleteRichMembers() {
        String query = DAOUtils.queryBuilder(null, null, PerunEntityType.MEMBER);

        return jdbcTemplate.query(query, RICH_MAPPER);
    }

    @Override
    public List<RichMember> getCompleteRichMembersHavingAttrs(List<InputAttribute> attrs) {
        //TODO improve
        List<RichMember> all = getCompleteRichMembers();
        List<RichMember> correct = new ArrayList<>();
        for (RichMember member: all) {
            if (DAOUtils.hasAttributes(member, attrs)) {
                correct.add(member);
            }
        }

        return correct;
    }

    @Override
    public List<RichMember> getCompleteRichMembersOfUser(Long userId) {
        String entityWhere = "WHERE t.user_id = ?";
        String query = DAOUtils.queryBuilder(entityWhere, null, PerunEntityType.MEMBER);

        return jdbcTemplate.query(query, new Object[] {userId}, RICH_MAPPER);
    }

    @Override
    public List<RichMember> getCompleteRichMembersOfVo(Long voId) {
        String entityWhere = "WHERE t.vo_id = ?";
        String query = DAOUtils.queryBuilder(entityWhere, null, PerunEntityType.MEMBER);

        return jdbcTemplate.query(query, new Object[] {voId}, RICH_MAPPER);
    }

    @Override
    public List<RichMember> getCompleteRichMembersByStatus(String status) {
        Character param = resolveStatusParam(status);
        String entityWhere = "WHERE t.status = ?";
        String query = DAOUtils.queryBuilder(entityWhere, null, PerunEntityType.MEMBER);

        return jdbcTemplate.query(query, new Object[] { param }, RICH_MAPPER);
    }

    @Override
    public List<RichMember> getCompleteRichMembersBySponsored(boolean isSponsored) {
        String entityWhere = "WHERE t.sponsored = ?";
        String query = DAOUtils.queryBuilder(entityWhere, null, PerunEntityType.MEMBER);

        return jdbcTemplate.query(query, new Object[] {isSponsored}, RICH_MAPPER);
    }

    /* ATTRIBUTES */

    @Override
    public List<PerunAttribute> getMemberAttrs(Long id, List<String> attrs) throws DatabaseIntegrityException {
        //TODO: improve
        RichMember member = getCompleteRichMember(id);
        return member.getAttributesByKeys(attrs);
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
