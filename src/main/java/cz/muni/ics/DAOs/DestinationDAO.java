package cz.muni.ics.DAOs;

import cz.muni.ics.exceptions.DatabaseIntegrityException;
import cz.muni.ics.models.Destination;

import javax.sql.DataSource;
import java.util.List;

public interface DestinationDAO {

    void setDataSource(DataSource dataSource);

    /**
     * Get Destination specified by ID.
     * @param id id of Destination
     * @return Found Destination or null if no such found.
     * @throws DatabaseIntegrityException Thrown when more than one Destinations with the same ID found.
     */
    Destination getDestination(Long id) throws DatabaseIntegrityException;

    /**
     * Get all Destinations.
     * @return List of Destinations, empty list if nothing has been found.
     */
    List<Destination> getDestinations();

    /**
     * Get Destinations with NAMEs like specified parameter.
     * (LIKE operator used, comparing ignores case)
     * @param name substring in Destination name
     * @return List of Destinations, empty list if nothing has been found.
     */
    List<Destination> getDestinationsByName(String name);
}
