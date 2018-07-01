package cz.muni.ics.DAOs;

import cz.muni.ics.exceptions.DatabaseIntegrityException;
import cz.muni.ics.models.Attribute;
import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.entities.Service;
import cz.muni.ics.models.richEntities.RichService;

import javax.sql.DataSource;
import java.util.List;

public interface ServiceDAO {

    void setDataSource(DataSource dataSource);
    
    /* SERVICE */
    
    /**
     * Get Service specified by ID.
     * @param id id of Service
     * @return Found Service or null if not such found.
     * @throws DatabaseIntegrityException When more than one Service with same id found.
     */
    Service getService(Long id) throws DatabaseIntegrityException;

    /**
     * Get all Services.
     * @return List of Services, empty list if nothing has been found.
     */
    List<Service> getServices();

    /**
     * Get Services with NAME like specified parameter.
     * @param name name of Service
     * @return List of Services, empty list if nothing has been found.
     */
    List<Service> getServicesByName(String name);

    /**
     * Get resources of Owner specified by ID.
     * @param ownerId id of Owner
     * @return List of Services, empty list if nothing has been found.
     */
    List<Service> getServicesOfOwner(Long ownerId);

    /**
     * Get Services that have specified attributes.
     * (EXACT matching used)
     * @param attrs attributes of Services
     * @return List of Services, empty list if nothing has been found.
     */
    List<Service> getServicesHavingAttrs(List<InputAttribute> attrs);

    /* RICH_SERVICE */

    /**
     * Get RichService specified by ID.
     * @param id id of RichService
     * @return Found RichService or null if not such found.
     * @throws DatabaseIntegrityException When more than one RichService with same id found.
     */
    RichService getRichService(Long id) throws DatabaseIntegrityException;

    /**
     * Get all RichServices.
     * @return List of RichServices, empty list if nothing has been found.
     */
    List<RichService> getRichServices();

    /**
     * Get RichServices with NAME like specified parameter.
     * @param name name of RichService
     * @return List of RichServices, empty list if nothing has been found.
     */
    List<RichService> getRichServicesByName(String name);

    /**
     * Get resources of Owner specified by ID.
     * @param ownerId id of Owner
     * @return List of RichServices, empty list if nothing has been found.
     */
    List<RichService> getRichServicesOfOwner(Long ownerId);

    /**
     * Get RichServices that have specified attributes.
     * (EXACT matching used)
     * @param attrs attributes of RichServices
     * @return List of RichServices, empty list if nothing has been found.
     */
    List<RichService> getRichServicesHavingAttrs(List<InputAttribute> attrs);

    /* ATTRIBUTES */

    /**
     * Get attributes of Service specified by ID.
     * @param id id of Service
     * @param attrs attributes to be fetched
     * @return List of attributes
     * @throws DatabaseIntegrityException When more than one Resource with same id found.
     */
    List<Attribute> getServiceAttrs(Long id, List<String> attrs) throws DatabaseIntegrityException;

}
