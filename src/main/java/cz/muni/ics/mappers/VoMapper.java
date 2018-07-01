package cz.muni.ics.mappers;

import cz.muni.ics.models.Vo;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class VoMapper implements RowMapper<Vo> {

    @NotNull
    @Override
    public Vo mapRow(@NotNull ResultSet rs, int i) throws SQLException {
        Vo vo = new Vo();

        vo.setId(rs.getLong("id"));
        vo.setName(rs.getString("name"));
        vo.setShortName(rs.getString("short_name"));

        return vo;
    }
}
