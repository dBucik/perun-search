package cz.muni.ics.mappers;

import cz.muni.ics.models.Facility;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FacilityMapper implements RowMapper<Facility> {

    @Override
    public Facility mapRow(ResultSet resultSet, int i) throws SQLException {
        //todo
        return null;
    }
}
