package cz.muni.ics.DAOs.impl;

import cz.muni.ics.DAOs.GroupDAO;
import cz.muni.ics.Utils;
import cz.muni.ics.exceptions.DatabaseIntegrityException;
import cz.muni.ics.mappers.GroupMapper;
import cz.muni.ics.models.Attribute;
import cz.muni.ics.models.Group;
import cz.muni.ics.models.InputAttribute;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.IncorrectResultSetColumnCountException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class GroupDAOImpl implements GroupDAO {

    private static final GroupMapper MAPPER = new GroupMapper();

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Group getGroup(Long id) throws DatabaseIntegrityException {
        String where = "WHERE t.id = ?";
        String query = queryBuilder(where);

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
        String query = queryBuilder(where);

        return jdbcTemplate.query(query, new Object[] {name}, MAPPER);
    }

    @Override
    public List<Group> getGroups() {
        String query = queryBuilder(null);

        return jdbcTemplate.query(query, MAPPER);
    }

    @Override
    public List<Attribute> getGroupAttrs(Long id, List<String> attrs) throws DatabaseIntegrityException {
        Group group = getGroup(id);
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

    @Override
    public List<Group> getGroupsWithAttrs(List<InputAttribute> attrs) {
        //TODO improve query
        List<Group> groups = getGroups();
        List<Attribute> filter = Utils.convertAttrsFromInput(attrs);

        groups.removeIf(group -> {
           assert filter != null;
           return !group.getAttributes().containsAll(filter);
        });

        return groups;
    }

    @Override
    public Group getParentGroup(Long childGroupId) throws DatabaseIntegrityException {
        Group child = getGroup(childGroupId);

        String where = "WHERE t.id = ?";
        String query = queryBuilder(where);

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
        String query = queryBuilder(where);

        return jdbcTemplate.query(query, new Object[] {voId}, MAPPER);
    }

    private String queryBuilder(String where) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT to_jsonb(t) || ");
        query.append("jsonb_build_object('attributes', json_object_agg(friendly_name, attr_value)) AS group ");
        query.append("FROM groups t ");
        query.append("JOIN group_attr_values av ON av.group_id = t.id ");
        query.append("JOIN attr_names an ON an.id = av.attr_id ");
        if (where != null) {
            query.append(where).append(' ');
        }
        query.append("GROUP BY t.id");
        return query.toString();
    }
}
