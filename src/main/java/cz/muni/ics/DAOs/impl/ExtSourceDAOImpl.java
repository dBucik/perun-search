package cz.muni.ics.DAOs.impl;

import cz.muni.ics.DAOs.DAOUtils;
import cz.muni.ics.DAOs.ExtSourceDAO;
import cz.muni.ics.exceptions.DatabaseIntegrityException;
import cz.muni.ics.mappers.entities.ExtSourceMapper;
import cz.muni.ics.mappers.richEntities.RichExtSourceMapper;
import cz.muni.ics.models.PerunEntityType;
import cz.muni.ics.models.attributes.PerunAttribute;
import cz.muni.ics.models.entities.ExtSource;
import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.richEntities.RichExtSource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.IncorrectResultSetColumnCountException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class ExtSourceDAOImpl implements ExtSourceDAO {

    private static final ExtSourceMapper MAPPER = new ExtSourceMapper();
    private static final RichExtSourceMapper RICH_MAPPER = new RichExtSourceMapper();

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /* EXT_SOURCE */

    @Override
    public ExtSource getExtSource(Long id) throws DatabaseIntegrityException {
        String where = "WHERE t.id = ?";
        String query = DAOUtils.queryBuilder(where, false, PerunEntityType.EXT_SOURCE);
        try {
            return jdbcTemplate.queryForObject(query, new Object[] {id}, MAPPER);
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (IncorrectResultSetColumnCountException e) {
            throw new DatabaseIntegrityException("More ExtSources with same ID found [id: " + id + ']');
        }
    }

    @Override
    public List<ExtSource> getExtSources() {
        String query = DAOUtils.queryBuilder(null, false, PerunEntityType.EXT_SOURCE);

        return jdbcTemplate.query(query, MAPPER);
    }

    @Override
    public List<ExtSource> getExtSourcesByName(String name) {
        name = '%' + name + '%';
        String where = "WHERE upper(t.name) LIKE upper(?)";
        String query = DAOUtils.queryBuilder(where, false, PerunEntityType.EXT_SOURCE);

        return jdbcTemplate.query(query, new Object[] {name}, MAPPER);
    }

    @Override
    public List<ExtSource> getExtSourcesByType(String type) {
        type = '%' + type + '%';
        String where = "WHERE upper(t.type) LIKE upper(?)";
        String query = DAOUtils.queryBuilder(where, false, PerunEntityType.EXT_SOURCE);

        return jdbcTemplate.query(query, new Object[] {type}, MAPPER);
    }

    @Override
    public List<ExtSource> getExtSourcesHavingAttrs(List<InputAttribute> attrs) {
        return new ArrayList<>(getRichExtSourcesHavingAttrs(attrs));
    }

    /* RICH_EXT_SOURCE */

    @Override
    public RichExtSource getRichExtSource(Long id) throws DatabaseIntegrityException {
        String where = "WHERE t.id = ?";
        String query = DAOUtils.queryBuilder(where, true, PerunEntityType.EXT_SOURCE);
        try {
            return jdbcTemplate.queryForObject(query, new Object[] {id}, RICH_MAPPER);
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (IncorrectResultSetColumnCountException e) {
            throw new DatabaseIntegrityException("More ExtSources with same ID found [id: " + id + ']');
        }
    }

    @Override
    public List<RichExtSource> getRichExtSources() {
        String query = DAOUtils.queryBuilder(null, true, PerunEntityType.EXT_SOURCE);

        return jdbcTemplate.query(query, RICH_MAPPER);
    }

    @Override
    public List<RichExtSource> getRichExtSourcesByName(String name) {
        name = '%' + name + '%';
        String where = "WHERE upper(t.name) LIKE upper(?)";
        String query = DAOUtils.queryBuilder(where, true, PerunEntityType.EXT_SOURCE);

        return jdbcTemplate.query(query, new Object[] {name}, RICH_MAPPER);
    }

    @Override
    public List<RichExtSource> getRichExtSourcesByType(String type) {
        type = '%' + type + '%';
        String where = "WHERE upper(t.type) LIKE upper(?)";
        String query = DAOUtils.queryBuilder(where, true, PerunEntityType.EXT_SOURCE);

        return jdbcTemplate.query(query, new Object[] {type}, RICH_MAPPER);
    }

    @Override
    public List<RichExtSource> getRichExtSourcesHavingAttrs(List<InputAttribute> attrs) {
        //TODO: improve
        List<RichExtSource> all = getRichExtSources();
        List<RichExtSource> correct = new ArrayList<>();
        for (RichExtSource extSource: all) {
            if (DAOUtils.hasAttributes(extSource, attrs)) {
                correct.add(extSource);
            }
        }

        return correct;
    }

    /* ATTRIBUTES */

    @Override
    public List<PerunAttribute> getExtSourceAttrs(Long id, List<String> attrs) throws DatabaseIntegrityException {
        //TODO: improve
        RichExtSource extSource = getRichExtSource(id);
        return extSource.getAttributesByKeys(attrs);
    }

}
