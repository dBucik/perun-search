package cz.muni.ics.DAOs;

import cz.muni.ics.models.Attribute;
import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.Service;

import javax.sql.DataSource;
import java.util.List;

public interface ServiceDAO {

    void setDataSource(DataSource dataSource);

    /**
     * Get service specified by ID.
     * @param id id of service
     * @return Found service or null if not such found.
     */
    Service getService(Long id);

    /**
     * Get service specified by NAME.
     * @param name name of service
     * @return Found service or null if not such found.
     */
    Service getServiceByName(String name);

    /**
     * Get all services.
     * @return List of services, null if nothing has been found.
     */
    List<Service> getServices();

    /**
     * Get attributes of service specified by ID.
     * @param id id of service
     * @param attrs attributes to be fetched
     * @return List of attributes.
     */
    List<Attribute> getServiceAttrs(Long id, List<InputAttribute> attrs);

    /**
     * Get services that have specified attributes. (Exact matching used)
     * @param attrs attributes of services
     * @return List of services found, empty list if nothing has been found.
     */
    List<Service> getServicesWithAttrs(List<InputAttribute> attrs);

    /**
     * Get resources of owner specified by ID.
     * @param ownerId id of facility
     * @return List of services, empty list if nothing has been found.
     */
    List<Service> getServicesOfOwner(Long ownerId);
}
