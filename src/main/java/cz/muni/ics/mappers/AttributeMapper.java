package cz.muni.ics.mappers;

import cz.muni.ics.models.Attribute;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AttributeMapper implements RowMapper<Attribute> {

    @NotNull
    @Override
    public Attribute mapRow(@NotNull ResultSet rs, int i) throws SQLException {
        String attrName = rs.getString("an.friendly_name");
        String attrValue = rs.getString("av.attr_value");

        return new Attribute(attrName, attrValue);
    }
}
