package cz.muni.ics.mappers.entities;

import cz.muni.ics.mappers.MappersUtils;
import cz.muni.ics.models.entities.Group;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GroupMapper implements RowMapper<Group> {

    @NotNull
    @Override
    public Group mapRow(@NotNull ResultSet rs, int i) throws SQLException {
        JSONObject json = new JSONObject(rs.getString("group"));

        return MappersUtils.mapGroup(json, new Group());
    }

}
