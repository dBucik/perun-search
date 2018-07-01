package cz.muni.ics.mappers;

import cz.muni.ics.models.Host;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HostMapper implements RowMapper<Host> {

    @Override
    public Host mapRow(@NotNull ResultSet rs, int i) throws SQLException {
        Host host = new Host();

        host.setId(rs.getLong("id"));
        host.setHostname(rs.getString("hostname"));
        host.setDescription(rs.getString("dsc"));
        host.setFacilityId(rs.getLong("facility_id"));

        return host;
    }
}
