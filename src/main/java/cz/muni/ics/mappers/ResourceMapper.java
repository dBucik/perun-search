package cz.muni.ics.mappers;

import cz.muni.ics.models.Resource;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResourceMapper implements RowMapper<Resource> {

    @NotNull
    @Override
    public Resource mapRow(@NotNull ResultSet rs, int i) throws SQLException {
        Resource resource = new Resource();

        resource.setId(rs.getLong("id"));
        resource.setFacilityId(rs.getLong("facility_id"));
        resource.setName(rs.getString("name"));
        resource.setDescription(rs.getString("dsc"));
        resource.setVoId(rs.getLong("vo_id"));

        return resource;
    }
}
