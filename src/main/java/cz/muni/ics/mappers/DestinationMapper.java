package cz.muni.ics.mappers;

import cz.muni.ics.models.Destination;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DestinationMapper implements RowMapper<Destination> {

    @NotNull
    @Override
    public Destination mapRow(@NotNull ResultSet rs, int i) throws SQLException {
        Destination dest = new Destination();

        dest.setId(rs.getLong("id"));
        dest.setName(rs.getString("name"));
        dest.setDescription(rs.getString("dsc"));

        return dest;
    }
}
