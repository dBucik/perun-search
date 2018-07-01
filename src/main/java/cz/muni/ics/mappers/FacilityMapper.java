package cz.muni.ics.mappers;

import cz.muni.ics.models.Facility;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FacilityMapper implements RowMapper<Facility> {

    @NotNull
    @Override
    public Facility mapRow(@NotNull ResultSet rs, int i) throws SQLException {
        Facility facility = new Facility();

        facility.setId(rs.getLong("id"));
        facility.setName(rs.getString("name"));
        facility.setDescription(rs.getString("dsc"));

        return facility;
    }
}
