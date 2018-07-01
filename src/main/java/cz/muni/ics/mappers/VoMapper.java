package cz.muni.ics.mappers;

import cz.muni.ics.models.Vo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class VoMapper implements RowMapper<Vo> {

    public Vo mapRow(ResultSet rs, int i) throws SQLException {
        Long id = rs.getLong("id");
        String name = rs.getString("name");
        String shortName = rs.getString("short_name");
        Vo vo = new Vo();
        vo.setId(id);
        vo.setName(name);
        vo.setShortName(shortName);

        return vo;
    }
}
