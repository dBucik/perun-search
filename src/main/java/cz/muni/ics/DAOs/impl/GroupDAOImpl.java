package cz.muni.ics.DAOs.impl;

import cz.muni.ics.DAOs.DAOUtils;
import cz.muni.ics.DAOs.GroupDAO;
import cz.muni.ics.exceptions.DatabaseIntegrityException;
import cz.muni.ics.mappers.entities.GroupMapper;
import cz.muni.ics.mappers.richEntities.RichGroupMapper;
import cz.muni.ics.models.PerunEntityType;
import cz.muni.ics.models.attributes.PerunAttribute;
import cz.muni.ics.models.entities.Group;
import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.richEntities.RichGroup;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.IncorrectResultSetColumnCountException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class GroupDAOImpl implements GroupDAO {

    private static final GroupMapper MAPPER = new GroupMapper();
    private static final RichGroupMapper RICH_MAPPER = new RichGroupMapper();

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /* GROUP */

    @Override
    public Group getGroup(Long id) throws DatabaseIntegrityException {
        String where = "WHERE t.id = ?";
        String query = DAOUtils.queryBuilder(where, false, PerunEntityType.GROUP);

        try {
            return jdbcTemplate.queryForObject(query, new Object[] {id}, MAPPER);
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (IncorrectResultSetColumnCountException e) {
            throw new DatabaseIntegrityException("More groups with same ID found [id: " + id + ']');
        }
    }

    @Override
    public List<Group> getGroupsByName(String name) {
        name = '%' + name + '%';
        String where = "WHERE upper(t.name) LIKE upper(?)";
        String query = DAOUtils.queryBuilder(where, false, PerunEntityType.GROUP);

        return jdbcTemplate.query(query, new Object[] {name}, MAPPER);
    }

    @Override
    public List<Group> getGroups() {
        String query = DAOUtils.queryBuilder(null, false, PerunEntityType.GROUP);

        return jdbcTemplate.query(query, MAPPER);
    }

    @Override
    public List<Group> getGroupsHavingAttrs(List<InputAttribute> attrs) {
        return new ArrayList<>(getRichGroupsHavingAttrs(attrs));
    }

    @Override
    public Group getParentGroup(Long childGroupId) throws DatabaseIntegrityException {
        Group child = getGroup(childGroupId);

        String where = "WHERE t.id = ?";
        String query = DAOUtils.queryBuilder(where, false, PerunEntityType.GROUP);

        try {
            return jdbcTemplate.queryForObject(query, new Object[]{child.getParentGroupId()}, MAPPER);
        } catch (EmptyResultDataAccessException e) {
            throw new DatabaseIntegrityException("No parent group found for group [child group id: " + childGroupId + ']');
        } catch (IncorrectResultSetColumnCountException e) {
            throw new DatabaseIntegrityException("More groups with same ID found [id: " + child.getParentGroupId() + ']');
        }
    }

    @Override
    public List<Group> getGroupsOfVo(Long voId) {
        String where = "WHERE t.vo_id = ?";
        String query = DAOUtils.queryBuilder(where, false, PerunEntityType.GROUP);

        return jdbcTemplate.query(query, new Object[] {voId}, MAPPER);
    }

    /* RICH_GROUP */

    @Override
    public RichGroup getRichGroup(Long id) throws DatabaseIntegrityException {
        String where = "WHERE t.id = ?";
        String query = DAOUtils.queryBuilder(where, true, PerunEntityType.GROUP);

        try {
            return jdbcTemplate.queryForObject(query, new Object[] {id}, RICH_MAPPER);
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (IncorrectResultSetColumnCountException e) {
            throw new DatabaseIntegrityException("More groups with same ID found [id: " + id + ']');
        }
    }

    @Override
    public List<RichGroup> getRichGroupsByName(String name) {
        name = '%' + name + '%';
        String where = "WHERE upper(t.name) LIKE upper(?)";
        String query = DAOUtils.queryBuilder(where, true, PerunEntityType.GROUP);

        return jdbcTemplate.query(query, new Object[] {name}, RICH_MAPPER);
    }

    @Override
    public List<RichGroup> getRichGroups() {
        String query = DAOUtils.queryBuilder(null, true, PerunEntityType.GROUP);

        return jdbcTemplate.query(query, RICH_MAPPER);
    }

    @Override
    public List<RichGroup> getRichGroupsHavingAttrs(List<InputAttribute> attrs) {
        //TODO: improve
        List<RichGroup> all = getRichGroups();
        List<RichGroup> correct = new ArrayList<>();
        for (RichGroup group: all) {
            if (DAOUtils.hasAttributes(group, attrs)) {
                correct.add(group);
            }
        }

        return correct;
    }

    @Override
    public RichGroup getParentRichGroup(Long childRichGroupId) throws DatabaseIntegrityException {
        Group child = getGroup(childRichGroupId);

        String where = "WHERE t.id = ?";
        String query = DAOUtils.queryBuilder(where, true, PerunEntityType.GROUP);

        try {
            return jdbcTemplate.queryForObject(query, new Object[]{child.getParentGroupId()}, RICH_MAPPER);
        } catch (EmptyResultDataAccessException e) {
            throw new DatabaseIntegrityException("No parent group found for group [child group id: " + childRichGroupId + ']');
        } catch (IncorrectResultSetColumnCountException e) {
            throw new DatabaseIntegrityException("More groups with same ID found [id: " + child.getParentGroupId() + ']');
        }
    }

    @Override
    public List<RichGroup> getRichGroupsOfVo(Long voId) {
        String where = "WHERE t.vo_id = ?";
        String query = DAOUtils.queryBuilder(where, true, PerunEntityType.GROUP);

        return jdbcTemplate.query(query, new Object[] {voId}, RICH_MAPPER);
    }

    /* ATTRIBUTES */

    @Override
    public List<PerunAttribute> getGroupAttrs(Long id, List<String> attrs) throws DatabaseIntegrityException {
        //TODO: improve
        RichGroup group = getRichGroup(id);
        return group.getAttributesByKeys(attrs);
    }

}
