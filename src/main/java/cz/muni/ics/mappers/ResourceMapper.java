package cz.muni.ics.mappers;

import cz.muni.ics.models.Resource;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResourceMapper implements RowMapper<Resource> {

    @Override
    public Resource mapRow(ResultSet resultSet, int i) throws SQLException {
        Resource resource = new Resource();

        resource.setId(resultSet.getLong("id"));
        resource.setFacilityId(resultSet.getLong("facility_id"));
        resource.setName(resultSet.getString("name"));
        resource.setDescription(resultSet.getString("dsc"));
        resource.setVoId(resultSet.getLong("vo_id"));

        return resource;
    }
}
