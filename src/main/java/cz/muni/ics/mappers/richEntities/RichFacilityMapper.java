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
        JSONObject json = new JSONObject(rs.getString("facility"));
        RichFacility facility = MappersUtils.mapFacility(json, new RichFacility());
        facility.setAttributes(MappersUtils.getAttributes(json.getJSONObject("attributes")));

        return facility;
    }

}
