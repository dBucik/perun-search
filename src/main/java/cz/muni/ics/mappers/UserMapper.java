package cz.muni.ics.mappers;

import cz.muni.ics.models.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();

        user.setId(resultSet.getLong("id"));
        user.setFirstName(resultSet.getString("first_name"));
        user.setLastName(resultSet.getString("last_name"));
        user.setMiddleName(resultSet.getString("middle_name"));
        user.setTitleBefore(resultSet.getString("title_before"));
        user.setTitleAfter(resultSet.getString("title_after"));

        if (resultSet.getString("service_acc").equals("0")) {
            user.setServiceAcc(false);
        } else {
            user.setServiceAcc(true);
        }

        if (resultSet.getString("sponsored_acc").equals("0")) {
            user.setSponsoredAcc(false);
        } else {
            user.setSponsoredAcc(true);
        }

        return user;
    }
}
