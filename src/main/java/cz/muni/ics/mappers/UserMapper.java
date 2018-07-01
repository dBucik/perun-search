package cz.muni.ics.mappers;

import cz.muni.ics.models.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {

    @NotNull
    @Override
    public User mapRow(@NotNull ResultSet rs, int i) throws SQLException {
        User user = new User();

        user.setId(rs.getLong("id"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setMiddleName(rs.getString("middle_name"));
        user.setTitleBefore(rs.getString("title_before"));
        user.setTitleAfter(rs.getString("title_after"));

        if (rs.getString("service_acc").equals("0")) {
            user.setServiceAcc(false);
        } else {
            user.setServiceAcc(true);
        }

        if (rs.getString("sponsored_acc").equals("0")) {
            user.setSponsoredAcc(false);
        } else {
            user.setSponsoredAcc(true);
        }

        return user;
    }
}
