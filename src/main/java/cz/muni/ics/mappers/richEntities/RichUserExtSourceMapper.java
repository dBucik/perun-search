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
        JSONObject entityJson = new JSONObject(rs.getString("entity"));
        RichUserExtSource res = MappersUtils.mapUserExtSource(entityJson, new RichUserExtSource());

        JSONObject attrsJson = new JSONObject(rs.getString("attrs"));
        res.setAttributes(MappersUtils.getAttributes(attrsJson));
        return res;
    }

}
