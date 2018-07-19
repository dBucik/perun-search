package cz.muni.ics.mappers.richEntities;

import cz.muni.ics.mappers.MappersUtils;
import cz.muni.ics.models.richEntities.RichFacility;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RichFacilityMapper implements RowMapper<RichFacility> {

    @NotNull
    @Override
    public RichFacility mapRow(@NotNull ResultSet rs, int i) throws SQLException {
        JSONObject entityJson = new JSONObject(rs.getString("entity"));
        RichFacility res = MappersUtils.mapFacility(entityJson, new RichFacility());

        JSONObject attrsJson = new JSONObject(rs.getString("attrs"));
        res.setAttributes(MappersUtils.getAttributes(attrsJson));
        return res;
    }

}
