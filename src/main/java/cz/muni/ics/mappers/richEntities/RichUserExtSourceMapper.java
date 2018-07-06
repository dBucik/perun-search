package cz.muni.ics.mappers.richEntities;

import cz.muni.ics.mappers.MappersUtils;
import cz.muni.ics.models.richEntities.RichUserExtSource;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RichUserExtSourceMapper implements RowMapper<RichUserExtSource> {

    @NotNull
    @Override
    public RichUserExtSource mapRow(@NotNull ResultSet rs, int i) throws SQLException {
        JSONObject json = new JSONObject(rs.getString("userExtSource"));
        RichUserExtSource ues = MappersUtils.mapUserExtSource(json, new RichUserExtSource());
        ues.setAttributes(MappersUtils.getAttributes(json.getJSONArray("attributes")));

        return ues;
    }

}
