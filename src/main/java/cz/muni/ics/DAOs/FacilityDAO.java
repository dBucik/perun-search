package cz.muni.ics.DAOs;

import cz.muni.ics.models.Attribute;
import cz.muni.ics.models.Facility;
import cz.muni.ics.models.InputAttribute;

import javax.sql.DataSource;
import java.util.List;

/**
 * DAO for Facility entity.
 *
 * @author Dominik Frantisek Bucik bucik@ics.muni.cz
 */
public interface FacilityDAO {

    void setDataSource(DataSource ds);

    /**
     * Get facility specified by ID.
     * @param id id of facility
     * @return Found facility or null if not such found.
     */
    Facility getFacility(Long id);

    /**
     * Get facility specified by NAME.
     * @param name name of facility
     * @return Found facility or null if not such found.
     */
    Facility getFacilityByName(String name);

    /**
     * Get all facilities.
     * @return List of facilities, empty list if nothing has been found.
     */
    List<Facility> getFacilities();

    /**
     * Get attributes of facility specified by ID.
     * @param id id of facility
     * @param attrs attributes to be fetched
     * @return List of attributes.
     */
    List<Attribute> getFacilityAttrs(Long id, List<InputAttribute> attrs);

    /**
     * Get facilities that have specified attributes. (Exact matching used)
     * @param attrs attributes of facilities
     * @return List of facilities found, empty list if nothing has been found.
     */
    List<Facility> getFacilitiesByAttrs(List<InputAttribute> attrs);
}
