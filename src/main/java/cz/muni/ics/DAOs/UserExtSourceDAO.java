package cz.muni.ics.DAOs;

import cz.muni.ics.models.ExtSource;

import javax.sql.DataSource;
import java.util.List;

public interface UserExtSourceDAO {

    void setDataSource(DataSource dataSource);

    ExtSource getUserExtSource(Long id);

    List<ExtSource> getUserExtSources();

}
