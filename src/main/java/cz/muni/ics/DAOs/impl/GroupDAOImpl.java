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

    private JdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /* GROUP */

    @Override
    public Group getGroup(Long id) throws DatabaseIntegrityException {
        String where = "WHERE t.id = ?";
        String query = DAOUtils.simpleQueryBuilder(where, PerunEntityType.GROUP);

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
        String query = DAOUtils.simpleQueryBuilder(where, PerunEntityType.GROUP);

        return jdbcTemplate.query(query, new Object[] {name}, MAPPER);
    }

    @Override
    public List<Group> getGroups() {
        String query = DAOUtils.simpleQueryBuilder(null, PerunEntityType.GROUP);

        return jdbcTemplate.query(query, MAPPER);
    }

    @Override
    public List<Group> getGroupsHavingAttrs(List<InputAttribute> attrs) {
        return new ArrayList<>(getCompleteRichGroupsHavingAttrs(attrs));
    }

    @Override
    public Group getParentGroup(Long childGroupId) throws DatabaseIntegrityException {
        Group child = getGroup(childGroupId);

        String where = "WHERE t.id = ?";
        String query = DAOUtils.simpleQueryBuilder(where, PerunEntityType.GROUP);

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
        String query = DAOUtils.simpleQueryBuilder(where, PerunEntityType.GROUP);

        return jdbcTemplate.query(query, new Object[] {voId}, MAPPER);
    }

    /* COMPLETE_RICH_GROUP */

    @Override
    public RichGroup getCompleteRichGroup(Long id) throws DatabaseIntegrityException {
        String entityWhere = "WHERE t.id = ?";
        String query = DAOUtils.queryBuilder(entityWhere, null, PerunEntityType.GROUP);

        try {
            return jdbcTemplate.queryForObject(query, new Object[] {id}, RICH_MAPPER);
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (IncorrectResultSetColumnCountException e) {
            throw new DatabaseIntegrityException("More groups with same ID found [id: " + id + ']');
        }
    }

    @Override
    public List<RichGroup> getCompleteRichGroupsByName(String name) {
        name = '%' + name + '%';
        String entityWhere = "WHERE upper(t.name) LIKE upper(?)";
        String query = DAOUtils.queryBuilder(entityWhere, null, PerunEntityType.GROUP);

        return jdbcTemplate.query(query, new Object[] {name}, RICH_MAPPER);
    }

    @Override
    public List<RichGroup> getCompleteRichGroups() {
        String query = DAOUtils.queryBuilder(null, null, PerunEntityType.GROUP);

        return jdbcTemplate.query(query, RICH_MAPPER);
    }

    @Override
    public RichGroup getCompleteParentRichGroup(Long childRichGroupId) throws DatabaseIntegrityException {
        Group child = getGroup(childRichGroupId);

        String entityWhere = "WHERE t.id = ?";
        String query = DAOUtils.queryBuilder(entityWhere, null, PerunEntityType.GROUP);

        try {
            return jdbcTemplate.queryForObject(query, new Object[]{child.getParentGroupId()}, RICH_MAPPER);
        } catch (EmptyResultDataAccessException e) {
            throw new DatabaseIntegrityException("No parent group found for group [child group id: " + childRichGroupId + ']');
        } catch (IncorrectResultSetColumnCountException e) {
            throw new DatabaseIntegrityException("More groups with same ID found [id: " + child.getParentGroupId() + ']');
        }
    }

    @Override
    public List<RichGroup> getCompleteRichGroupsOfVo(Long voId) {
        String entityWhere = "WHERE t.vo_id = ?";
        String query = DAOUtils.queryBuilder(entityWhere, null, PerunEntityType.GROUP);

        return jdbcTemplate.query(query, new Object[] {voId}, RICH_MAPPER);
    }

    @Override
    public List<RichGroup> getCompleteRichGroupsHavingAttrs(List<InputAttribute> attrs) {
        //TODO: improve
        List<RichGroup> all = getCompleteRichGroups();
        List<RichGroup> correct = new ArrayList<>();
        for (RichGroup group: all) {
            if (DAOUtils.hasAttributes(group, attrs)) {
                correct.add(group);
            }
        }

        return correct;
    }

    /* ATTRIBUTES */

    @Override
    public List<PerunAttribute> getGroupAttrs(Long id, List<String> attrs) throws DatabaseIntegrityException {
        //TODO: improve
        RichGroup group = getCompleteRichGroup(id);
        return group.getAttributesByKeys(attrs);
    }

}
