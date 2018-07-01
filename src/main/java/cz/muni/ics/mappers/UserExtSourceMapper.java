package cz.muni.ics.mappers;

import cz.muni.ics.models.UserExtSource;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UserExtSourceMapper implements RowMapper<UserExtSource> {

    @Override
    public UserExtSource mapRow(ResultSet resultSet, int i) throws SQLException {
        UserExtSource ues = new UserExtSource();

        ues.setId(resultSet.getLong("id"));
        ues.setUserId(resultSet.getLong("user_id"));
        ues.setLoginExt(resultSet.getString("login_ext"));
        ues.setExtSourcesId(resultSet.getLong("ext_sources_id"));
        ues.setLoa(resultSet.getShort("loa"));

        return ues;
    }
}
