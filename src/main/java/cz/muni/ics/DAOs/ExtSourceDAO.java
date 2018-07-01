package cz.muni.ics.DAOs;

import cz.muni.ics.models.ExtSource;

import javax.sql.DataSource;
import java.util.List;

public interface ExtSourceDAO {

    void setDataSource(DataSource dataSource);

    ExtSource getExtSource(Long id);

    List<ExtSource> getExtSources();
}
