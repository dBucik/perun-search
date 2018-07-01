package cz.muni.ics.DAOs.impl;

import cz.muni.ics.DAOs.UserExtSourceDAO;
import cz.muni.ics.Utils;
import cz.muni.ics.exceptions.DatabaseIntegrityException;
import cz.muni.ics.mappers.UserExtSourceMapper;
import cz.muni.ics.models.Attribute;
import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.UserExtSource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.IncorrectResultSetColumnCountException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class UserExtSourceDAOImpl implements UserExtSourceDAO {

    private static final UserExtSourceMapper MAPPER = new UserExtSourceMapper();

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public UserExtSource getUserExtSource(Long id) throws DatabaseIntegrityException {
        String where = "WHERE t.id = ?";
        String query = queryBuilder(where);
        try {
            return jdbcTemplate.queryForObject(query, new Object[] {id}, MAPPER);
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (IncorrectResultSetColumnCountException e) {
            throw new DatabaseIntegrityException("More UserExtSources with same ID found [id: " + id + ']');
        }
    }

    @Override
    public List<UserExtSource> getUserExtSources() {
        String query = queryBuilder(null);

        return jdbcTemplate.query(query, MAPPER);
    }

    @Override
    public List<Attribute> getUserExtSourceAttrs(Long id, List<String> attrs) throws DatabaseIntegrityException {
        UserExtSource userExtSource = getUserExtSource(id);
        List<Attribute> result = new ArrayList<>();
        if (attrs == null) {
            result.add(new Attribute("id", userExtSource.getId().toString()));
            result.add(new Attribute("userId", userExtSource.getUserId().toString()));
            result.add(new Attribute("loginExt", userExtSource.getLoginExt()));
            result.add(new Attribute("extSourcesId", userExtSource.getExtSourcesId().toString()));
            result.add(new Attribute("loa", userExtSource.getLoa().toString()));
            result.addAll(userExtSource.getAttributes());
        } else {
            result.addAll(userExtSource.getAttributesByKeys(attrs));
        }

        return result;
    }

    @Override
    public List<UserExtSource> getUserExtSourcesWithAttrs(List<InputAttribute> attrs) {
        List<UserExtSource> userExtSources = getUserExtSources();
        List<Attribute> filter = Utils.convertAttrsFromInput(attrs);
        userExtSources.removeIf(extSource -> {
            assert filter != null;
            return ! extSource.getAttributes().containsAll(filter);
        });

        return userExtSources;
    }

    @Override
    public List<UserExtSource> getUserExtSourcesOfUser(Long userId) {
        String where = "WHERE t.user_id = ?";
        String query = queryBuilder(where);

        return jdbcTemplate.query(query, new Object[] {userId}, MAPPER);
    }

    @Override
    public List<UserExtSource> getUserExtSourcesOfExtSource(Long extSourceId) {
        String where = "WHERE t.ext_source_id = ?";
        String query = queryBuilder(where);

        return jdbcTemplate.query(query, new Object[] {extSourceId}, MAPPER);
    }

    @Override
    public List<UserExtSource> getUserExtSourcesByLoginExt(String loginExt) {
        loginExt = '%' + loginExt + '%';
        String where = "WHERE upper(t.login_ext) LIKE upper(?)";
        String query = queryBuilder(where);

        return jdbcTemplate.query(query, new Object[] {loginExt}, MAPPER);
    }

    private String queryBuilder(String where) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT to_jsonb(t) || ");
        query.append("jsonb_build_object('attributes', json_object_agg(friendly_name, attr_value)) AS userExtSource ");
        query.append("FROM user_ext_sources t ");
        query.append("JOIN user_ext_source_attr_values av ON av.user_ext_source_id = t.id ");
        query.append("JOIN attr_names an ON an.id = av.attr_id ");
        if (where != null) {
            query.append(where).append(' ');
        }
        query.append("GROUP BY t.id");
        return query.toString();
    }
}
