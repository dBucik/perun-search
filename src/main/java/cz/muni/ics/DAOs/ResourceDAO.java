package cz.muni.ics.DAOs;

import cz.muni.ics.exceptions.DatabaseIntegrityException;
import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.attributes.PerunAttribute;
import cz.muni.ics.models.entities.Resource;
import cz.muni.ics.models.richEntities.RichResource;

import javax.sql.DataSource;
import java.util.List;

public interface ResourceDAO {

    void setDataSource(DataSource dataSource);
    
    /* RESOURCE */
    
    /**
     * Get Resource specified by ID.
     * @param id id of Resource
     * @return Found Resource or null if not such found.
     * @throws DatabaseIntegrityException More than one Resource with same ID found.
     */
    Resource getResource(Long id) throws DatabaseIntegrityException;

    /**
     * Get all Resources.
     * @return List of Resources, empty list if nothing has been found.
     */
    List<Resource> getResources();

    /**
     * Get Resource with NAME like specified parameter.
     * (Operator LIKE used, comparing ignores case)
     * @param name name of Resource
     * @return List of Resources, empty list if nothing has been found.
     */
    List<Resource> getResourcesByName(String name);

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

    /**
     * Get Resources that have specified attributes.
     * (EXACT matching used)
     * @param attrs attributes of Resources
     * @return List of Resources, empty list if nothing has been found.
     */
    List<Resource> getResourcesHavingAttrs(List<InputAttribute> attrs);

    /* COMPLETE_RICH_RESOURCE */

    /**
     * Get RichResource specified by ID.
     * @param id id of RichResource
     * @return Found RichResource or null if not such found.
     * @throws DatabaseIntegrityException More than one RichResource with same ID found.
     */
    RichResource getCompleteRichResource(Long id) throws DatabaseIntegrityException;

    /**
     * Get all RichResources.
     * @return List of RichResources, empty list if nothing has been found.
     */
    List<RichResource> getCompleteRichResources();

    /**
     * Get RichResource with NAME like specified parameter.
     * (Operator LIKE used, comparing ignores case)
     * @param name name of RichResource
     * @return List of RichResources, empty list if nothing has been found.
     */
    List<RichResource> getCompleteRichResourcesByName(String name);

    /**
     * Get RichResources of Facility specified by ID.
     * @param facilityId id of Facility
     * @return List of RichResources, empty list if nothing has been found.
     */
    List<RichResource> getCompleteRichResourcesOfFacility(Long facilityId);

    /**
     * Get RichResources of Vo specified by ID.
     * @param voId id of Vo
     * @return List of RichResources, empty list if nothing has been found.
     */
    List<RichResource> getCompleteRichResourcesOfVo(Long voId);

    /**
     * Get RichResources that have specified attributes.
     * (EXACT matching used)
     * @param attrs attributes of RichResources
     * @return List of RichResources, empty list if nothing has been found.
     */
    List<RichResource> getCompleteRichResourcesHavingAttrs(List<InputAttribute> attrs);
    
    /* ATTRIBUTES */
    
    /**
     * Get attributes of Resource specified by ID.
     * @param id id of Resource
     * @param attrs attributes to be fetched
     * @return List of attributes.
     * @throws DatabaseIntegrityException More than one Resource with same ID found.
     */
    List<PerunAttribute> getResourceAttrs(Long id, List<String> attrs) throws DatabaseIntegrityException;
    
}
