package cz.muni.ics.mappers;

import cz.muni.ics.models.UserExtSource;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UserExtSourceMapper implements RowMapper<UserExtSource> {

    @NotNull
    @Override
    public UserExtSource mapRow(@NotNull ResultSet rs, int i) throws SQLException {
        UserExtSource ues = new UserExtSource();

        ues.setId(rs.getLong("id"));
        ues.setUserId(rs.getLong("user_id"));
        ues.setLoginExt(rs.getString("login_ext"));
        ues.setExtSourcesId(rs.getLong("ext_sources_id"));
        ues.setLoa(rs.getShort("loa"));

        return ues;
    }
}
