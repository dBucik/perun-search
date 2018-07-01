package cz.muni.ics.mappers;

import cz.muni.ics.models.Vo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class VoMapper implements RowMapper<Vo> {

    public Vo mapRow(ResultSet rs, int i) throws SQLException {
        Vo vo = new Vo();
        return vo;
    }
}
