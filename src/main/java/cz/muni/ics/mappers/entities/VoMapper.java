package cz.muni.ics.mappers.entities;

import cz.muni.ics.mappers.MappersUtils;
import cz.muni.ics.models.entities.Vo;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VoMapper implements RowMapper<Vo> {

    @NotNull
    @Override
    public Vo mapRow(@NotNull ResultSet rs, int i) throws SQLException {
        JSONObject json = new JSONObject(rs.getString("vo"));

        return MappersUtils.mapVo(json, new Vo());
    }

}
