package cz.muni.ics.DAOs;

import cz.muni.ics.exceptions.DatabaseIntegrityException;
import cz.muni.ics.models.Attribute;
import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.Resource;

import javax.sql.DataSource;
import java.util.List;

public interface ResourceDAO {

    void setDataSource(DataSource dataSource);

    /**
     * Get Resource specified by ID.
     * @param id id of Resource
     * @return Found Resource or null if not such found.
     * @throws DatabaseIntegrityException More than one Resource with same ID found.
     */
    Resource getResource(Long id) throws DatabaseIntegrityException;

    /**
     * Get Resource with NAME like specified parameter.
     * (Operator LIKE used, comparing ignores case)
     * @param name name of Resource
     * @return List of Resources, empty list if nothing has been found.
     */
    List<Resource> getResourcesByName(String name);

    /**
     * Get all Resources.
     * @return List of Resources, empty list if nothing has been found.
     */
    List<Resource> getResources();

    /**
     * Get attributes of Resource specified by ID.
     * @param id id of Resource
     * @param attrs attributes to be fetched
     * @return List of attributes.
     * @throws DatabaseIntegrityException More than one Resource with same ID found.
     */
    List<Attribute> getResourceAttrs(Long id, List<String> attrs) throws DatabaseIntegrityException;

    /**
     * Get Resources that have specified attributes.
     * (EXACT matching used)
     * @param attrs attributes of Resources
     * @return List of Resources, empty list if nothing has been found.
     */
    List<Resource> getResourcesWithAttrs(List<InputAttribute> attrs);

    /**
     * Get Resources of Facility specified by ID.
     * @param facilityId id of Facility
     * @return List of Resources, empty list if nothing has been found.
     */
    List<Resource> getResourcesOfFacility(Long facilityId);

    /**
     * Get Resources of Vo specified by ID.
     * @param voId id of Vo
     * @return List of Resources, empty list if nothing has been found.
     */
    List<Resource> getResourcesOfVo(Long voId);
}
