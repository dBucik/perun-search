package cz.muni.ics.DAOs;

import cz.muni.ics.models.User;

import javax.sql.DataSource;
import java.util.List;

public interface UserDAO {

    void setDataSource(DataSource dataSource);

    User getUser(Long id);

    List<User> getUsers();
}
