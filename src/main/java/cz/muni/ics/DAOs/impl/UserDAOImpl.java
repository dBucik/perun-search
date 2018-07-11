package cz.muni.ics.DAOs.impl;

import cz.muni.ics.DAOs.DAOUtils;
import cz.muni.ics.DAOs.UserDAO;
import cz.muni.ics.exceptions.DatabaseIntegrityException;
import cz.muni.ics.mappers.entities.UserMapper;
import cz.muni.ics.mappers.richEntities.RichUserMapper;
import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.PerunEntityType;
import cz.muni.ics.models.attributes.PerunAttribute;
import cz.muni.ics.models.entities.User;
import cz.muni.ics.models.richEntities.RichUser;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.IncorrectResultSetColumnCountException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    private static final UserMapper MAPPER = new UserMapper();
    private static final RichUserMapper RICH_MAPPER = new RichUserMapper();

    private JdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /* USER */

    @Override
    public User getUser(Long id) throws DatabaseIntegrityException {
        String where = "WHERE t.id=?";
        String query = DAOUtils.simpleQueryBuilder(where, PerunEntityType.USER);

        try {
            return jdbcTemplate.queryForObject(query, new Object[]{id}, MAPPER);
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (IncorrectResultSetColumnCountException e) {
            throw new DatabaseIntegrityException("More Users with same ID found [id: " + id + ']');
        }
    }

    @Override
    public List<User> getUsers() {
        String query = DAOUtils.simpleQueryBuilder(null, PerunEntityType.USER);
        return jdbcTemplate.query(query, MAPPER);
    }

    @Override
    public List<User> getUsersHavingAttrs(List<InputAttribute> attrs) {
        return new ArrayList<>(getCompleteRichUsersHavingAttrs(attrs));
    }

    @Override
    public List<User> getUsersByName(String titleBefore, String firstName, String middleName,
                                     String lastName, String titleAfter)
    {
        String where = "WHERE upper(COALESCE(t.title_before || ' ', 'A') || COALESCE(t.first_name || ' ', '') || " +
                "COALESCE(t.middle_name || ' ', '') || COALESCE(t.last_name || ' ', '') || " +
                "COALESCE(t.title_after || ' ', '')) AS full_name LIKE upper(?)";
        String query = DAOUtils.simpleQueryBuilder(where, PerunEntityType.USER);
        String param = ((titleBefore == null) ? "% " : '%' + titleBefore + "% ") +
                ((firstName == null) ? "% " : '%' + firstName + "% ") +
                ((middleName == null) ? "% " : '%' + middleName + "% ") +
                ((lastName == null) ? "% " : '%' + lastName + "% ") +
                ((titleAfter == null) ? "%" : '%' + titleAfter + "% ");

        return jdbcTemplate.query(query, new Object[] {param}, MAPPER);
    }

    @Override
    public List<User> getUsersByServiceAcc(boolean isServiceAcc) {
        String param = isServiceAcc ? "t" : "f";
        String where = "WHERE t.service_acc = ?";
        String query = DAOUtils.simpleQueryBuilder(where, PerunEntityType.USER);
        return jdbcTemplate.query(query, new Object[] {param}, MAPPER);
    }

    @Override
    public List<User> getUsersBySponsoredAcc(boolean isSponsoredAcc) {
        String param = isSponsoredAcc ? "t" : "f";
        String where = "WHERE t.sponsored_acc = ?";
        String query = DAOUtils.simpleQueryBuilder(where, PerunEntityType.USER);
        return jdbcTemplate.query(query, new Object[] {param}, MAPPER);
    }

    /* COMPLETE_RICH_USER */

    @Override
    public RichUser getCompleteRichUser(Long id) throws DatabaseIntegrityException {
        String entityWhere = "WHERE t.id=?";
        String query = DAOUtils.queryBuilder(entityWhere, null, PerunEntityType.USER);

        try {
            return jdbcTemplate.queryForObject(query, new Object[]{id}, RICH_MAPPER);
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (IncorrectResultSetColumnCountException e) {
            throw new DatabaseIntegrityException("More Users with same ID found [id: " + id + ']');
        }
    }

    @Override
    public List<RichUser> getCompleteRichUsers() {
        String query = DAOUtils.queryBuilder(null, null, PerunEntityType.USER);
        return jdbcTemplate.query(query, RICH_MAPPER);
    }

    @Override
    public List<RichUser> getCompleteRichUsersByName(String titleBefore, String firstName, String middleName, String lastName, String titleAfter) {
        String entityWhere = "WHERE upper(COALESCE(t.title_before || ' ', 'A') || COALESCE(t.first_name || ' ', '') || " +
                "COALESCE(t.middle_name || ' ', '') || COALESCE(t.last_name || ' ', '') || " +
                "COALESCE(t.title_after || ' ', '')) AS full_name LIKE upper(?)";
        String query = DAOUtils.queryBuilder(entityWhere, null, PerunEntityType.USER);
        String param = ((titleBefore == null) ? "% " : '%' + titleBefore + "% ") +
                ((firstName == null) ? "% " : '%' + firstName + "% ") +
                ((middleName == null) ? "% " : '%' + middleName + "% ") +
                ((lastName == null) ? "% " : '%' + lastName + "% ") +
                ((titleAfter == null) ? "%" : '%' + titleAfter + "% ");

        return jdbcTemplate.query(query, new Object[] { param }, RICH_MAPPER);
    }

    @Override
    public List<RichUser> getCompleteRichUsersByServiceAcc(boolean isServiceAcc) {
        String param = isServiceAcc ? "t" : "f";
        String entityWhere = "WHERE t.service_acc = ?";
        String query = DAOUtils.queryBuilder(entityWhere, null, PerunEntityType.USER);
        return jdbcTemplate.query(query, new Object[] {param}, RICH_MAPPER);
    }

    @Override
    public List<RichUser> getCompleteRichUsersBySponsoredAcc(boolean isSponsoredAcc) {
        String param = isSponsoredAcc ? "t" : "f";
        String entityWhere = "WHERE t.sponsored_acc = ?";
        String query = DAOUtils.queryBuilder(entityWhere, null, PerunEntityType.USER);
        return jdbcTemplate.query(query, new Object[] {param}, RICH_MAPPER);
    }

    @Override
    public List<RichUser> getCompleteRichUsersHavingAttrs(List<InputAttribute> attrs) {
        //TODO: improve
        List<RichUser> all = getCompleteRichUsers();
        List<RichUser> correct = new ArrayList<>();
        for (RichUser user: all) {
            if (DAOUtils.hasAttributes(user, attrs)) {
                correct.add(user);
            }
        }

        return correct;
    }

    /* ATTRIBUTES */

    @Override
    public List<PerunAttribute> getUserAttrs(Long id, List<String> attrs) throws DatabaseIntegrityException {
        //TODO: improve
        RichUser user = getCompleteRichUser(id);
        return user.getAttributesByKeys(attrs);
    }

}
