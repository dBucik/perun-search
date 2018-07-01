package cz.muni.ics.mappers;

import cz.muni.ics.models.Host;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HostMapper implements RowMapper<Host> {

    @Override
    public Host mapRow(ResultSet resultSet, int i) throws SQLException {
        Host host = new Host();

        host.setId(resultSet.getLong("id"));
        host.setHostname(resultSet.getString("hostname"));
        host.setDescription(resultSet.getString("dsc"));
        host.setFacilityId(resultSet.getLong("facility_id"));

        return host;
    }
}
