package cz.muni.ics.mappers.richEntities;

import cz.muni.ics.mappers.MappersUtils;
import cz.muni.ics.models.richEntities.RichGroup;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RichGroupMapper implements RowMapper<RichGroup> {

    @NotNull
    @Override
    public RichGroup mapRow(@NotNull ResultSet rs, int i) throws SQLException {
        JSONObject entityJson = new JSONObject(rs.getString("entity"));
        RichGroup res = MappersUtils.mapGroup(entityJson, new RichGroup());

        JSONObject attrsJson = new JSONObject(rs.getString("attrs"));
        res.setAttributes(MappersUtils.getAttributes(attrsJson));
        return res;
    }

}
