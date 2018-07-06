package cz.muni.ics.mappers.richEntities;

import cz.muni.ics.mappers.MappersUtils;
import cz.muni.ics.models.richEntities.RichResource;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RichResourceMapper implements RowMapper<RichResource> {

    @NotNull
    @Override
    public RichResource mapRow(@NotNull ResultSet rs, int i) throws SQLException {
        JSONObject json = new JSONObject(rs.getString("resource"));
        RichResource resource = MappersUtils.mapResource(json, new RichResource());
        resource.setAttributes(MappersUtils.getAttributes(json.getJSONArray("attributes")));

        return resource;
    }

}
