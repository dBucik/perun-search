package cz.muni.ics.mappers.richEntities;

import cz.muni.ics.mappers.MappersUtils;
import cz.muni.ics.models.richEntities.RichHost;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RichHostMapper implements RowMapper<RichHost> {

    @NotNull
    @Override
    public RichHost mapRow(@NotNull ResultSet rs, int i) throws SQLException {
        JSONObject json = new JSONObject(rs.getString("host"));
        RichHost host = MappersUtils.mapHost(json, new RichHost());
        host.setAttributes(MappersUtils.getAttributes(json.getJSONArray("attributes")));

        return host;
    }

}
