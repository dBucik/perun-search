package cz.muni.ics.DAOs;

import cz.muni.ics.exceptions.DatabaseIntegrityException;
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
     * Get Facility specified by ID.
     * @param id id of Facility
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return Found Facility or null if not such found.
     * @throws DatabaseIntegrityException More than one Facility with same ID found.
     */
    Facility getFacility(Long id, boolean withAttrs) throws DatabaseIntegrityException;

    /**
     * Get all Facilities.
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return List of Facilities, empty list if nothing has been found.
     */
    List<Facility> getFacilities(boolean withAttrs);

    /**
     * Get attributes of Facility specified by ID.
     * @param id id of Facility
     * @param attrs attributes to be fetched
     * @return List of attributes.
     * @throws DatabaseIntegrityException More than one Facility with same ID found.
     */
    List<Attribute> getFacilityAttrs(Long id, List<String> attrs) throws DatabaseIntegrityException;

    /**
     * Get Facilities that have specified attributes.
     * (EXACT matching used)
     * @param attrs attributes of Facilities
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return List of Facilities found, empty list if nothing has been found.
     */
    List<Facility> getFacilitiesByAttrs(List<InputAttribute> attrs, boolean withAttrs);

    /**
     * Get Facilities with NAME like specified parameter.
     * (LIKE operator used, comparing ignores case)
     * @param name name of Facility
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return List of Facilities, empty list if nothing has been found.
     */
    List<Facility> getFacilitiesByName(String name, boolean withAttrs);
}
