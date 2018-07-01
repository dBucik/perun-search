package cz.muni.ics.DAOs.impl;

import cz.muni.ics.DAOs.DAOUtils;
import cz.muni.ics.DAOs.GroupDAO;
import cz.muni.ics.exceptions.DatabaseIntegrityException;
import cz.muni.ics.mappers.entities.GroupMapper;
import cz.muni.ics.mappers.richEntities.RichGroupMapper;
import cz.muni.ics.models.Attribute;
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
        String query = queryBuilder(where, false);

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
        String query = queryBuilder(where, false);

        return jdbcTemplate.query(query, new Object[] {name}, MAPPER);
    }

    @Override
    public List<Group> getGroups() {
        String query = queryBuilder(null, false);

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
        String query = queryBuilder(where, false);

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
        String query = queryBuilder(where, false);

        return jdbcTemplate.query(query, new Object[] {voId}, MAPPER);
    }

    /* RICH_GROUP */

    @Override
    public RichGroup getRichGroup(Long id) throws DatabaseIntegrityException {
        String where = "WHERE t.id = ?";
        String query = queryBuilder(where, true);

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
        String query = queryBuilder(where, true);

        return jdbcTemplate.query(query, new Object[] {name}, RICH_MAPPER);
    }

    @Override
    public List<RichGroup> getRichGroups() {
        String query = queryBuilder(null, true);

        return jdbcTemplate.query(query, RICH_MAPPER);
    }

    @Override
    public List<RichGroup> getRichGroupsHavingAttrs(List<InputAttribute> attrs) {
        List<RichGroup> groups = getRichGroups();
        List<Attribute> filter = DAOUtils.convertAttrsFromInput(attrs);

        groups.removeIf(group -> {
            assert filter != null;
            return !group.getAttributes().containsAll(filter);
        });

        return groups;
    }

    @Override
    public RichGroup getParentRichGroup(Long childRichGroupId) throws DatabaseIntegrityException {
        Group child = getGroup(childRichGroupId);

        String where = "WHERE t.id = ?";
        String query = queryBuilder(where, true);

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
        String query = queryBuilder(where, true);

        return jdbcTemplate.query(query, new Object[] {voId}, RICH_MAPPER);
    }

    /* ATTRIBUTES */

    @Override
    public List<Attribute> getGroupAttrs(Long id, List<String> attrs) throws DatabaseIntegrityException {
        //TODO: improve
        RichGroup group = getRichGroup(id);
        List<Attribute> result = new ArrayList<>();

        if (attrs == null) {
            result.add(new Attribute("id", group.getId().toString()));
            result.add(new Attribute("name", group.getName()));
            result.add(new Attribute("description", group.getDescription()));
            result.add(new Attribute("voId", group.getVoId().toString()));
            result.add(new Attribute("parentGroupId", group.getParentGroupId().toString()));
            result.addAll(group.getAttributes());
        } else {
            result.addAll(group.getAttributesByKeys(attrs));
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
        query.append(" AS group");
        query.append(" FROM groups t");
        if (withAttrs) {
            query.append(" JOIN group_attr_values av ON av.group_id = t.id");
            query.append(" JOIN attr_names an ON an.id = av.attr_id");
        }
        if (where != null) {
            query.append(' ').append(where.trim());
        }
        query.append(" GROUP BY t.id");
        return query.toString();
    }

}
