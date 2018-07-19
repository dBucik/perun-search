package cz.muni.ics.DAOs;

import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.entities.Owner;

import javax.sql.DataSource;
import java.util.List;

public interface OwnerDAO {

    void setDataSource(DataSource dataSource);

    List<Owner> getOwners(List<InputAttribute> core);

}
