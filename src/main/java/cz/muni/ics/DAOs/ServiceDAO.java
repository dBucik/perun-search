package cz.muni.ics.DAOs;

import cz.muni.ics.models.Service;

import javax.sql.DataSource;
import java.util.List;

public interface ServiceDAO {

    void setDataSource(DataSource dataSource);

    Service getService(Long id);

    List<Service> getServices();
}
