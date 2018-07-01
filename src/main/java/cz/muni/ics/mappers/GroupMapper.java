package cz.muni.ics.mappers;

import cz.muni.ics.models.Group;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GroupMapper implements RowMapper<Group> {

    @NotNull
    @Override
    public Group mapRow(@NotNull ResultSet rs, int i) throws SQLException {
        Group group = new Group();

        group.setId(rs.getLong("id"));
        group.setName(rs.getString("name"));
        group.setDescription(rs.getString("dsc"));
        group.setVoId(rs.getLong("vo_id"));
        group.setParentGroupId(rs.getLong("parent_group_id"));

        return group;
    }
}
