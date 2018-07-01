package cz.muni.ics.DAOs;

import cz.muni.ics.models.Destination;

import javax.sql.DataSource;
import java.util.List;

public interface DestinationDAO {

    void setDataSource(DataSource dataSource);

    Destination getDestination(Long id);

    List<Destination> getDestinations();

    Destination getDestinationByName(String name);
}
