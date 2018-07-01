package cz.muni.ics.DAOs;

import cz.muni.ics.exceptions.DatabaseIntegrityException;
import cz.muni.ics.models.entities.Owner;

import javax.sql.DataSource;
import java.util.List;

public interface OwnerDAO {

    void setDataSource(DataSource dataSource);

    Owner getOwner(Long id) throws DatabaseIntegrityException;

    List<Owner> getOwnersByName(String name);

    List<Owner> getOwnersByType(String type);
}
