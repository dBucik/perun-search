package cz.muni.ics.DAOs.impl;

import cz.muni.ics.DAOs.DAOUtils;
import cz.muni.ics.DAOs.UserExtSourceDAO;
import cz.muni.ics.exceptions.DatabaseIntegrityException;
import cz.muni.ics.mappers.entities.UserExtSourceMapper;
import cz.muni.ics.mappers.richEntities.RichUserExtSourceMapper;
import cz.muni.ics.models.Attribute;
import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.entities.UserExtSource;
import cz.muni.ics.models.richEntities.RichUserExtSource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.IncorrectResultSetColumnCountException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class UserExtSourceDAOImpl implements UserExtSourceDAO {

    private static final UserExtSourceMapper MAPPER = new UserExtSourceMapper();
    private static final RichUserExtSourceMapper RICH_MAPPER = new RichUserExtSourceMapper();

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
        String query = queryBuilder(where, false);
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
        String query = queryBuilder(null, false);

        return jdbcTemplate.query(query, MAPPER);
    }

    @Override
    public List<UserExtSource> getUserExtSourcesWithAttrs(List<InputAttribute> attrs) {
        return new ArrayList<>(getRichUserExtSourcesWithAttrs(attrs));
    }

    @Override
    public List<UserExtSource> getUserExtSourcesOfUser(Long userId) {
        String where = "WHERE t.user_id = ?";
        String query = queryBuilder(where, false);

        return jdbcTemplate.query(query, new Object[] { userId }, MAPPER);
    }

    @Override
    public List<UserExtSource> getUserExtSourcesOfExtSource(Long extSourceId) {
        String where = "WHERE t.ext_source_id = ?";
        String query = queryBuilder(where, false);

        return jdbcTemplate.query(query, new Object[] { extSourceId }, MAPPER);
    }

    @Override
    public List<UserExtSource> getUserExtSourcesByLoginExt(String loginExt) {
        loginExt = '%' + loginExt + '%';
        String where = "WHERE upper(t.login_ext) LIKE upper(?)";
        String query = queryBuilder(where, false);

        return jdbcTemplate.query(query, new Object[] { loginExt }, MAPPER);
    }

    /* RICH_USER_EXT_SOURCE */

    @Override
    public RichUserExtSource getRichUserExtSource(Long id) throws DatabaseIntegrityException {
        String where = "WHERE t.id = ?";
        String query = queryBuilder(where, true);
        try {
            return jdbcTemplate.queryForObject(query, new Object[] {id}, RICH_MAPPER);
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (IncorrectResultSetColumnCountException e) {
            throw new DatabaseIntegrityException("More UserExtSources with same ID found [id: " + id + ']');
        }
    }

    @Override
    public List<RichUserExtSource> getRichUserExtSources() {
        String query = queryBuilder(null, true);

        return jdbcTemplate.query(query, RICH_MAPPER);
    }

    @Override
    public List<RichUserExtSource> getRichUserExtSourcesWithAttrs(List<InputAttribute> attrs) {
        //TODO: improve
        List<RichUserExtSource> userExtSources = getRichUserExtSources();
        List<Attribute> filter = DAOUtils.convertAttrsFromInput(attrs);
        userExtSources.removeIf(extSource -> {
            assert filter != null;
            return ! extSource.getAttributes().containsAll(filter);
        });

        return userExtSources;
    }

    @Override
    public List<RichUserExtSource> getRichUserExtSourcesOfUser(Long userId) {
        String where = "WHERE t.user_id = ?";
        String query = queryBuilder(where, true);

        return jdbcTemplate.query(query, new Object[] { userId }, RICH_MAPPER);
    }

    @Override
    public List<RichUserExtSource> getRichUserExtSourcesOfExtSource(Long extSourceId) {
        String where = "WHERE t.ext_source_id = ?";
        String query = queryBuilder(where, true);

        return jdbcTemplate.query(query, new Object[] { extSourceId }, RICH_MAPPER);
    }

    @Override
    public List<RichUserExtSource> getRichUserExtSourcesByLoginExt(String loginExt) {
        loginExt = '%' + loginExt + '%';
        String where = "WHERE upper(t.login_ext) LIKE upper(?)";
        String query = queryBuilder(where, true);

        return jdbcTemplate.query(query, new Object[] { loginExt }, RICH_MAPPER);
    }

    /* ATTRIBUTES */

    @Override
    public List<Attribute> getUserExtSourceAttrs(Long id, List<String> attrs) throws DatabaseIntegrityException {
        //TODO: improve
        RichUserExtSource userExtSource = getRichUserExtSource(id);
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

    private String queryBuilder(String where, boolean withAttrs) {
        //TODO: check table names
        StringBuilder query = new StringBuilder();
        query.append("SELECT to_jsonb(t)");
        if (withAttrs) {
            query.append(" ||");
            query.append(" jsonb_build_object('attributes', json_object_agg(friendly_name, attr_value))");
        }
        query.append(" AS userExtSource");
        query.append(" FROM user_ext_sources t");
        if (withAttrs) {
            query.append(" JOIN user_ext_source_attr_values av ON av.user_ext_source_id = t.id");
            query.append(" JOIN attr_names an ON an.id = av.attr_id");
        }
        if (where != null) {
            query.append(' ').append(where.trim());
        }
        query.append(" GROUP BY t.id");
        return query.toString();
    }

}
