package cz.muni.ics.JdbcTemplates;

import cz.muni.ics.DAOs.UserDAO;
import cz.muni.ics.exceptions.DatabaseIntegrityException;
import cz.muni.ics.mappers.AttributeMapper;
import cz.muni.ics.mappers.UserMapper;
import cz.muni.ics.models.Attribute;
import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.IncorrectResultSetColumnCountException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

import static cz.muni.ics.Utils.convertAttrsFromInput;

public class UserJdbcTemplate implements UserDAO {

    private static final UserMapper MAPPER = new UserMapper();

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public User getUser(Long id) throws DatabaseIntegrityException {
        String where = "WHERE t.id=?";
        String query = queryBuilder(where);

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
        String query = queryBuilder(null);
        return jdbcTemplate.query(query, MAPPER);
    }

    @Override
    public List<Attribute> getUserAttrs(Long id, List<String> attrs) throws DatabaseIntegrityException {
        User user = getUser(id);
        List<Attribute> result = new ArrayList<>();
        if (attrs == null) {
            result.add(new Attribute("id", user.getId().toString()));
            result.add(new Attribute("title_before", user.getTitleBefore()));
            result.add(new Attribute("first_name", user.getFirstName()));
            result.add(new Attribute("middle_name", user.getMiddleName()));
            result.add(new Attribute("last_name", user.getLastName()));
            result.add(new Attribute("title_after", user.getTitleAfter()));
            result.addAll(user.getAttributes());
        } else {
            result.addAll(user.getAttributesByKeys(attrs));
        }
        return result;
    }

    @Override
    public List<User> getUsersWithAttrs(List<InputAttribute> attrs) {
        List<User> users = getUsers();
        List<Attribute> filter = convertAttrsFromInput(attrs);

        users.removeIf(user -> {
            assert filter != null;
            return ! user.getAttributes().containsAll(filter);
        });

        return users;
    }

    @Override
    public List<User> getUsersByName(String titleBefore, String firstName, String middleName,
                                     String lastName, String titleAfter)
    {
        StringBuilder param = new StringBuilder();
        param.append((titleBefore == null) ? "% " : '%' + titleBefore + "% ");
        param.append((firstName == null) ? "% " : '%' + firstName + "% ");
        param.append((middleName == null) ? "% " : '%' + middleName + "% ");
        param.append((lastName == null) ? "% " : '%' + lastName + "% ");
        param.append((titleAfter == null) ? "%" : '%' + titleAfter + "% ");

        String where = "WHERE upper(COALESCE(t.title_before || ' ', 'A') || COALESCE(t.first_name || ' ', '') || " +
                "COALESCE(t.middle_name || ' ', '') || COALESCE(t.last_name || ' ', '') || " +
                "COALESCE(t.title_after || ' ', '')) AS full_name LIKE upper(?)";
        String query = queryBuilder(where);
        return jdbcTemplate.query(query,
                new Object[] {param.toString()}, MAPPER);
    }

    @Override
    public List<User> getUsersByServiceAcc(boolean isServiceAcc) {
        String param = isServiceAcc ? "t" : "f";
        String where = "WHERE t.service_acc = ?";
        String query = queryBuilder(where);
        return jdbcTemplate.query(query, new Object[] {param}, MAPPER);
    }

    @Override
    public List<User> getUsersBySponsoredAcc(boolean isSponsoredAcc) {
        String param = isSponsoredAcc ? "t" : "f";
        String where = "WHERE t.sponsored_acc = ?";
        String query = queryBuilder(where);
        return jdbcTemplate.query(query, new Object[] {param}, MAPPER);
    }

    private String queryBuilder(String where) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT to_jsonb(t) || ");
        query.append("jsonb_build_object('attributes', json_object_agg(friendly_name, attr_value)) AS user ");
        query.append("FROM users t ");
        query.append("JOIN user_attr_values av ON av.user_id = t.id ");
        query.append("JOIN attr_names an ON an.id = av.attr_id ");
        if (where != null) {
            query.append(where).append(' ');
        }
        query.append("GROUP BY t.id");
        return query.toString();
    }
}
