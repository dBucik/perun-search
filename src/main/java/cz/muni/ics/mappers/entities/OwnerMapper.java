package cz.muni.ics.mappers.entities;

import cz.muni.ics.models.entities.Owner;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OwnerMapper implements RowMapper<Owner> {

    @NotNull
    @Override
    public Owner mapRow(@NotNull ResultSet rs, int i) throws SQLException {
        Owner owner = new Owner();
        owner.setId(rs.getLong("id"));
        owner.setName(rs.getString("name"));
        owner.setContact(rs.getString("contact"));
        owner.setStatus(rs.getString("status"));
        owner.setType(rs.getString("type"));

        return owner;
    }

}
