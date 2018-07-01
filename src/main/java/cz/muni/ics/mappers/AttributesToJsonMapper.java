package cz.muni.ics.mappers;

import cz.muni.ics.models.AttributePair;
import org.json.JSONObject;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AttributesToJsonMapper implements RowMapper<AttributePair> {

    @Override
    public AttributePair mapRow(ResultSet resultSet, int i) throws SQLException {
        String attrName = resultSet.getString("an.friendly_name");
        String attrValue = resultSet.getString("av.attr_value");

        return new AttributePair(attrName, attrValue);
    }
}
