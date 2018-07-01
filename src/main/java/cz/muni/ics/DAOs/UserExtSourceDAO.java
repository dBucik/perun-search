package cz.muni.ics.DAOs;

import cz.muni.ics.models.UserExtSource;

import javax.sql.DataSource;
import java.util.List;

public interface UserExtSourceDAO {

    void setDataSource(DataSource dataSource);

    UserExtSource getUserExtSource(Long id);

    List<UserExtSource> getUserExtSources();

}
