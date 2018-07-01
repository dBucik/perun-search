package cz.muni.ics.mappers.richEntities;

import cz.muni.ics.mappers.MappersUtils;
import cz.muni.ics.models.richEntities.RichVo;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RichVoMapper implements RowMapper<RichVo> {

    @NotNull
    @Override
    public RichVo mapRow(@NotNull ResultSet rs, int i) throws SQLException {
        JSONObject json = new JSONObject(rs.getString("vo"));
        RichVo vo = MappersUtils.mapVo(json, new RichVo());
        vo.setAttributes(MappersUtils.getAttributes(json.getJSONObject("attributes")));

        return vo;
    }

}
