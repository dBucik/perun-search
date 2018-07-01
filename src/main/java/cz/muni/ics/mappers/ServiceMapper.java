package cz.muni.ics.mappers;

import cz.muni.ics.models.Service;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceMapper implements RowMapper<Service> {

    @NotNull
    @Override
    public Service mapRow(@NotNull ResultSet rs, int i) throws SQLException {
        Service service = new Service();

        service.setId(rs.getLong("id"));
        service.setName(rs.getString("name"));
        service.setOwnerId(rs.getLong("owner_id"));

        return service;
    }
}
