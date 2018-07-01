package cz.muni.ics.DAOs;

import cz.muni.ics.exceptions.DatabaseIntegrityException;
import cz.muni.ics.models.Attribute;
import cz.muni.ics.models.entities.Facility;
import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.richEntities.RichFacility;

import javax.sql.DataSource;
import java.util.List;

/**
 * DAO for Facility entity.
 *
 * @author Dominik Frantisek Bucik bucik@ics.muni.cz
 */
public interface FacilityDAO {

    void setDataSource(DataSource ds);

    /* FACILITY */

    /**
     * Get Facility specified by ID.
     * @param id id of Facility
     * @return Found Facility or null if not such found.
     * @throws DatabaseIntegrityException More than one Facility with same ID found.
     */
    Facility getFacility(Long id) throws DatabaseIntegrityException;

    /**
     * Get all Facilities.
     * @return List of Facilities, empty list if nothing has been found.
     */
    List<Facility> getFacilities();

    /**
     * Get Facilities with NAME like specified parameter.
     * (LIKE operator used, comparing ignores case)
     * @param name name of Facility
     * @return List of Facilities, empty list if nothing has been found.
     */
    List<Facility> getFacilitiesByName(String name);

    /**
     * Get Facilities that have specified attributes.
     * (EXACT matching used)
     * @param attrs attributes of Facilities
     * @return List of Facilities found, empty list if nothing has been found.
     */
    List<Facility> getFacilitiesHavingAttrs(List<InputAttribute> attrs);

    /* RICH_FACILITY */

    /**
     * Get RichFacility specified by ID.
     * @param id id of RichFacility
     * @return Found RichFacility or null if not such found.
     * @throws DatabaseIntegrityException More than one RichFacility with same ID found.
     */
    RichFacility getRichFacility(Long id) throws DatabaseIntegrityException;

    /**
     * Get all RichFacilities.
     * @return List of RichFacilities, empty list if nothing has been found.
     */
    List<RichFacility> getRichFacilities();

    /**
     * Get RichFacilities with NAME like specified parameter.
     * (LIKE operator used, comparing ignores case)
     * @param name name of RichFacility
     * @return List of RichFacilities, empty list if nothing has been found.
     */
    List<RichFacility> getRichFacilitiesByName(String name);

    /**
     * Get RichFacilities that have specified attributes.
     * (EXACT matching used)
     * @param attrs attributes of RichFacilities
     * @return List of RichFacilities found, empty list if nothing has been found.
     */
    List<RichFacility> getRichFacilitiesHavingAttrs(List<InputAttribute> attrs);

    /* ATTRIBUTES */

    /**
     * Get attributes of Facility specified by ID.
     * @param id id of Facility
     * @param attrs attributes to be fetched
     * @return List of attributes.
     * @throws DatabaseIntegrityException More than one Facility with same ID found.
     */
    List<Attribute> getFacilityAttrs(Long id, List<String> attrs) throws DatabaseIntegrityException;

}
