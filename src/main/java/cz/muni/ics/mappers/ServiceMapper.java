package cz.muni.ics.mappers;

import cz.muni.ics.models.Service;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceMapper implements RowMapper<Service> {

    @Override
    public Service mapRow(ResultSet resultSet, int i) throws SQLException {
        Service service = new Service();

        service.setId(resultSet.getLong("id"));
        service.setName(resultSet.getString("name"));
        service.setOwnerId(resultSet.getLong("owner_id"));

        return service;
    }
}
