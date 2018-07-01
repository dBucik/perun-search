package cz.muni.ics.DAOs;

import cz.muni.ics.models.Resource;

import javax.sql.DataSource;
import java.util.List;

public interface ResourceDAO {

    void setDataSource(DataSource dataSource);

    Resource getResource(Long id);

    List<Resource> getResources();
}
