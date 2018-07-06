package cz.muni.ics.mappers.richEntities;

import cz.muni.ics.mappers.MappersUtils;
import cz.muni.ics.models.richEntities.RichExtSource;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RichExtSourceMapper implements RowMapper<RichExtSource> {

    @NotNull
    @Override
    public RichExtSource mapRow(@NotNull ResultSet rs, int i) throws SQLException {
        JSONObject json = new JSONObject(rs.getString("extSource"));
        RichExtSource res = MappersUtils.mapExtSource(json, new RichExtSource());
        res.setAttributes(MappersUtils.getAttributes(json.getJSONArray("attributes")));

        return res;
    }

}
