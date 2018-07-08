package cz.muni.ics.DAOs.impl;

import cz.muni.ics.DAOs.DAOUtils;
import cz.muni.ics.DAOs.UserExtSourceDAO;
import cz.muni.ics.exceptions.DatabaseIntegrityException;
import cz.muni.ics.mappers.entities.UserExtSourceMapper;
import cz.muni.ics.mappers.richEntities.RichUserExtSourceMapper;
import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.PerunEntityType;
import cz.muni.ics.models.attributes.PerunAttribute;
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
        String query = DAOUtils.simpleQueryBuilder(where, PerunEntityType.USER_EXT_SOURCE);
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
        String query = DAOUtils.simpleQueryBuilder(null, PerunEntityType.USER_EXT_SOURCE);

        return jdbcTemplate.query(query, MAPPER);
    }

    @Override
    public List<UserExtSource> getUserExtSourcesHavingAttrs(List<InputAttribute> attrs) {
        return new ArrayList<>(getRichUserExtSourcesHavingAttrs(attrs));
    }

    @Override
    public List<UserExtSource> getUserExtSourcesOfUser(Long userId) {
        String where = "WHERE t.user_id = ?";
        String query = DAOUtils.simpleQueryBuilder(where, PerunEntityType.USER_EXT_SOURCE);

        return jdbcTemplate.query(query, new Object[] { userId }, MAPPER);
    }

    @Override
    public List<UserExtSource> getUserExtSourcesOfExtSource(Long extSourceId) {
        String where = "WHERE t.ext_source_id = ?";
        String query = DAOUtils.simpleQueryBuilder(where, PerunEntityType.USER_EXT_SOURCE);

        return jdbcTemplate.query(query, new Object[] { extSourceId }, MAPPER);
    }

    @Override
    public List<UserExtSource> getUserExtSourcesByLoginExt(String loginExt) {
        loginExt = '%' + loginExt + '%';
        String where = "WHERE upper(t.login_ext) LIKE upper(?)";
        String query = DAOUtils.simpleQueryBuilder(where, PerunEntityType.USER_EXT_SOURCE);

        return jdbcTemplate.query(query, new Object[] { loginExt }, MAPPER);
    }

    /* RICH_USER_EXT_SOURCE */

    @Override
    public RichUserExtSource getRichUserExtSource(Long id) throws DatabaseIntegrityException {
        String entityWhere = "WHERE t.id = ?";
        String query = DAOUtils.queryBuilder(entityWhere, null, PerunEntityType.USER_EXT_SOURCE);
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
        String query = DAOUtils.queryBuilder(null, null, PerunEntityType.USER_EXT_SOURCE);

        return jdbcTemplate.query(query, RICH_MAPPER);
    }

    @Override
    public List<RichUserExtSource> getRichUserExtSourcesOfUser(Long userId) {
        String entityWhere = "WHERE t.user_id = ?";
        String query = DAOUtils.queryBuilder(entityWhere, null, PerunEntityType.USER_EXT_SOURCE);

        return jdbcTemplate.query(query, new Object[] { userId }, RICH_MAPPER);
    }

    @Override
    public List<RichUserExtSource> getRichUserExtSourcesOfExtSource(Long extSourceId) {
        String entityWhere = "WHERE t.ext_source_id = ?";
        String query = DAOUtils.queryBuilder(entityWhere, null, PerunEntityType.USER_EXT_SOURCE);

        return jdbcTemplate.query(query, new Object[] { extSourceId }, RICH_MAPPER);
    }

    @Override
    public List<RichUserExtSource> getRichUserExtSourcesByLoginExt(String loginExt) {
        loginExt = '%' + loginExt + '%';
        String entityWhere = "WHERE upper(t.login_ext) LIKE upper(?)";
        String query = DAOUtils.queryBuilder(entityWhere, null, PerunEntityType.USER_EXT_SOURCE);

        return jdbcTemplate.query(query, new Object[] { loginExt }, RICH_MAPPER);
    }

    @Override
    public List<RichUserExtSource> getRichUserExtSourcesHavingAttrs(List<InputAttribute> attrs) {
        //TODO: improve
        List<RichUserExtSource> all = getRichUserExtSources();
        List<RichUserExtSource> correct = new ArrayList<>();
        for (RichUserExtSource userExtSource: all) {
            if (DAOUtils.hasAttributes(userExtSource, attrs)) {
                correct.add(userExtSource);
            }
        }

        return correct;
    }

    /* ATTRIBUTES */

    @Override
    public List<PerunAttribute> getUserExtSourceAttrs(Long id, List<String> attrs) throws DatabaseIntegrityException {
        //TODO: improve
        RichUserExtSource userExtSource = getRichUserExtSource(id);
        return userExtSource.getAttributesByKeys(attrs);
    }

}
