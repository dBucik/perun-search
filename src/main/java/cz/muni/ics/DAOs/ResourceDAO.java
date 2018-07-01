package cz.muni.ics.DAOs;

import cz.muni.ics.models.Attribute;
import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.Resource;

import javax.sql.DataSource;
import java.util.List;

public interface ResourceDAO {

    void setDataSource(DataSource dataSource);

    /**
     * Get resource specified by ID.
     * @param id id of resource
     * @return Found resource or null if not such found.
     */
    Resource getResource(Long id);

    /**
     * Get resource specified by NAME.
     * @param name name of resource
     * @return Found resource or null if not such found.
     */
    Resource getResourceByName(String name);

    /**
     * Get all resources.
     * @return List of resources, null if nothing has been found.
     */
    List<Resource> getResources();

    /**
     * Get attributes of resource specified by ID.
     * @param id id of resource
     * @param attrs attributes to be fetched
     * @return List of attributes.
     */
    List<Attribute> getResourceAttrs(Long id, List<InputAttribute> attrs);

    /**
     * Get resources that have specified attributes. (Exact matching used)
     * @param attrs attributes of resources
     * @return List of resources found, empty list if nothing has been found.
     */
    List<Resource> getResourcesWithAttrs(List<InputAttribute> attrs);

    /**
     * Get resources of facility specified by ID.
     * @param facilityId id of facility
     * @return List of resources, empty list if nothing has been found.
     */
    List<Resource> getResourcesOfFacility(Long facilityId);

    /**
     * Get resources of vo specified by ID.
     * @param voId id of vo
     * @return List of resources, empty list if nothing has been found.
     */
    List<Resource> getResourcesOfVo(Long voId);
}
