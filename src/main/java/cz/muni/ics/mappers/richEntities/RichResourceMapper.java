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
        JSONObject entityJson = new JSONObject(rs.getString("entity"));
        RichResource res = MappersUtils.mapResource(entityJson, new RichResource());

        JSONObject attrsJson = new JSONObject(rs.getString("attrs"));
        res.setAttributes(MappersUtils.getAttributes(attrsJson));
        return res;
    }

}
