package cz.muni.ics.mappers.richEntities;

import cz.muni.ics.mappers.MappersUtils;
import cz.muni.ics.models.richEntities.RichService;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RichServiceMapper implements RowMapper<RichService> {

    @NotNull
    @Override
    public RichService mapRow(@NotNull ResultSet rs, int i) throws SQLException {
        JSONObject json = new JSONObject(rs.getString("service"));
        RichService service = MappersUtils.mapService(json, new RichService());
        service.setAttributes(MappersUtils.getAttributes(json.getJSONArray("attributes")));

        return service;
    }

}
