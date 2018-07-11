package cz.muni.ics.DAOs;

import cz.muni.ics.exceptions.DatabaseIntegrityException;
import cz.muni.ics.models.entities.Owner;

import javax.sql.DataSource;
import java.util.List;

public interface OwnerDAO {

    void setDataSource(DataSource dataSource);

    /**
     * Get Owner specified by ID.
     * @param id id of Owner
     * @return Found Owner or null if not such found.
     * @throws DatabaseIntegrityException More than one Owner with same ID found.
     */
    Owner getOwner(Long id) throws DatabaseIntegrityException;

    /**
     * Get Owner with NAME like specified parameter.
     * (Operator LIKE used, comparing ignores case)
     * @param name name of Owner
     * @return List of Owners, empty list if nothing has been found.
     */
    List<Owner> getOwnersByName(String name);

    /**
     * Get Owner with TYPE like specified parameter.
     * (Operator LIKE used, comparing ignores case)
     * @param type type of Owner
     * @return List of Owners, empty list if nothing has been found.
     */
    List<Owner> getOwnersByType(String type);
}
