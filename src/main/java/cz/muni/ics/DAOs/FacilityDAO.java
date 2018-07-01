package cz.muni.ics.DAOs;

import cz.muni.ics.models.Facility;

import javax.sql.DataSource;
import java.util.List;

public interface FacilityDAO {

    void setDataSource(DataSource ds);

    Facility getFacility(Long id);

    List<Facility> getFacilities();
}
