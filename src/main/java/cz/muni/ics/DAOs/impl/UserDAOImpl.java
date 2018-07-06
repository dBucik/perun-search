package cz.muni.ics.DAOs.impl;

import cz.muni.ics.DAOs.DAOUtils;
import cz.muni.ics.DAOs.UserDAO;
import cz.muni.ics.exceptions.DatabaseIntegrityException;
import cz.muni.ics.mappers.entities.UserMapper;
import cz.muni.ics.mappers.richEntities.RichUserMapper;
import cz.muni.ics.models.InputAttribute;
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

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /* USER */

    @Override
    public User getUser(Long id) throws DatabaseIntegrityException {
        String where = "WHERE t.id=?";
        String query = queryBuilder(where, false);

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
        String query = queryBuilder(null, false);
        return jdbcTemplate.query(query, MAPPER);
    }

    @Override
    public List<User> getUsersHavingAttrs(List<InputAttribute> attrs) {
        return new ArrayList<>(getRichUsersHavingAttrs(attrs));
    }

    @Override
    public List<User> getUsersByName(String titleBefore, String firstName, String middleName,
                                     String lastName, String titleAfter)
    {
        String where = "WHERE upper(COALESCE(t.title_before || ' ', 'A') || COALESCE(t.first_name || ' ', '') || " +
                "COALESCE(t.middle_name || ' ', '') || COALESCE(t.last_name || ' ', '') || " +
                "COALESCE(t.title_after || ' ', '')) AS full_name LIKE upper(?)";
        String query = queryBuilder(where, false);
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
        String query = queryBuilder(where, false);
        return jdbcTemplate.query(query, new Object[] {param}, MAPPER);
    }

    @Override
    public List<User> getUsersBySponsoredAcc(boolean isSponsoredAcc) {
        String param = isSponsoredAcc ? "t" : "f";
        String where = "WHERE t.sponsored_acc = ?";
        String query = queryBuilder(where, false);
        return jdbcTemplate.query(query, new Object[] {param}, MAPPER);
    }

    /* RICH_USER */

    @Override
    public RichUser getRichUser(Long id) throws DatabaseIntegrityException {
        String where = "WHERE t.id=?";
        String query = queryBuilder(where, true);

        try {
            return jdbcTemplate.queryForObject(query, new Object[]{id}, RICH_MAPPER);
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (IncorrectResultSetColumnCountException e) {
            throw new DatabaseIntegrityException("More Users with same ID found [id: " + id + ']');
        }
    }

    @Override
    public List<RichUser> getRichUsers() {
        String query = queryBuilder(null, true);
        return jdbcTemplate.query(query, RICH_MAPPER);
    }

    @Override
    public List<RichUser> getRichUsersHavingAttrs(List<InputAttribute> attrs) {
        //TODO: improve
        List<RichUser> all = getRichUsers();
        List<RichUser> correct = new ArrayList<>();
        for (RichUser user: all) {
            if (DAOUtils.hasAttributes(user, attrs)) {
                correct.add(user);
            }
        }

        return correct;
    }

    @Override
    public List<RichUser> getRichUsersByName(String titleBefore, String firstName, String middleName, String lastName, String titleAfter) {
        String where = "WHERE upper(COALESCE(t.title_before || ' ', 'A') || COALESCE(t.first_name || ' ', '') || " +
                "COALESCE(t.middle_name || ' ', '') || COALESCE(t.last_name || ' ', '') || " +
                "COALESCE(t.title_after || ' ', '')) AS full_name LIKE upper(?)";
        String query = queryBuilder(where, true);
        String param = ((titleBefore == null) ? "% " : '%' + titleBefore + "% ") +
                ((firstName == null) ? "% " : '%' + firstName + "% ") +
                ((middleName == null) ? "% " : '%' + middleName + "% ") +
                ((lastName == null) ? "% " : '%' + lastName + "% ") +
                ((titleAfter == null) ? "%" : '%' + titleAfter + "% ");

        return jdbcTemplate.query(query, new Object[] { param }, RICH_MAPPER);
    }

    @Override
    public List<RichUser> getRichUsersByServiceAcc(boolean isServiceAcc) {
        String param = isServiceAcc ? "t" : "f";
        String where = "WHERE t.service_acc = ?";
        String query = queryBuilder(where, true);
        return jdbcTemplate.query(query, new Object[] {param}, RICH_MAPPER);
    }

    @Override
    public List<RichUser> getRichUsersBySponsoredAcc(boolean isSponsoredAcc) {
        String param = isSponsoredAcc ? "t" : "f";
        String where = "WHERE t.sponsored_acc = ?";
        String query = queryBuilder(where, true);
        return jdbcTemplate.query(query, new Object[] {param}, RICH_MAPPER);
    }

    /* ATTRIBUTES */

    @Override
    public List<PerunAttribute> getUserAttrs(Long id, List<String> attrs) throws DatabaseIntegrityException {
        //TODO: improve
        RichUser user = getRichUser(id);
        return user.getAttributesByKeys(attrs);
    }

    private String queryBuilder(String where, boolean withAttrs) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT to_jsonb(t)");
        if (withAttrs) {
            query.append(" ||");
            query.append(" jsonb_build_object('attributes', json_agg(jsonb_build_object('key', friendly_name," +
                    " 'val', attr_value, 'val_text', attr_value_text, 'type', type)))");
        }
        query.append(" AS user");
        query.append(" FROM users t");
        if (withAttrs) {
            query.append(" JOIN user_attr_values av ON av.user_id = t.id");
            query.append(" JOIN attr_names an ON an.id = av.attr_id");
        }
        if (where != null) {
            query.append(' ').append(where.trim());
        }
        query.append(" GROUP BY t.id");
        return query.toString();
    }

}
