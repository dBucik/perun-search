package cz.muni.ics.mappers;

import cz.muni.ics.models.Group;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GroupMapper implements RowMapper<Group> {

    @Override
    public Group mapRow(ResultSet resultSet, int i) throws SQLException {
        Group group = new Group();

        group.setId(resultSet.getLong("id"));
        group.setName(resultSet.getString("name"));
        group.setDescription(resultSet.getString("dsc"));
        group.setVoId(resultSet.getLong("vo_id"));
        group.setParentGroupId(resultSet.getLong("parent_group_id"));

        return group;
    }
}
