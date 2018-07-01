package cz.muni.ics.DAOs;

import cz.muni.ics.exceptions.DatabaseIntegrityException;
import cz.muni.ics.models.Attribute;
import cz.muni.ics.models.entities.Host;
import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.richEntities.RichHost;

import javax.sql.DataSource;
import java.util.List;

public interface HostDAO {

    void setDataSource(DataSource dataSource);

    /* HOST */

    /**
     * Get Host specified by ID.
     * @param id id of Host
     * @return Found Host or null if not such found.
     * @throws DatabaseIntegrityException More than one Host with same ID found.
     */
    Host getHost(Long id) throws DatabaseIntegrityException;

    /**
     * Get all Hosts.
     * @return List of Hosts, empty list if nothing has been found.
     */
    List<Host> getHosts();

    /**
     * Get Hosts with HOSTNAME like specified parameter.
     * (LIKE operator used, comparing ignores case)
     * @param hostname Hostname of Host
     * @return List of Hosts, empty list if nothing has been found.
     */
    List<Host> getHostsByHostname(String hostname);

    /**
     * Get Hosts that have specified attributes.
     * (EXACT matching used)
     * @param attrs attributes of Hosts
     * @return List of Hosts, empty list if nothing has been found.
     */
    List<Host> getHostsHavingAttrs(List<InputAttribute> attrs);

    /* RICH_HOST */

    /**
     * Get RichHost specified by ID.
     * @param id id of RichHost
     * @return Found RichHost or null if not such found.
     * @throws DatabaseIntegrityException More than one RichHost with same ID found.
     */
    RichHost getRichHost(Long id) throws DatabaseIntegrityException;

    /**
     * Get all RichHosts.
     * @return List of RichHosts, empty list if nothing has been found.
     */
    List<RichHost> getRichHosts();

    /**
     * Get RichHosts with HOSTNAME like specified parameter.
     * (LIKE operator used, comparing ignores case)
     * @param hostname RichHostname of RichHost
     * @return List of RichHosts, empty list if nothing has been found.
     */
    List<RichHost> getRichHostsByHostname(String hostname);

    /**
     * Get RichHosts that have specified attributes.
     * (EXACT matching used)
     * @param attrs attributes of RichHosts
     * @return List of RichHosts, empty list if nothing has been found.
     */
    List<RichHost> getRichHostsHavingAttrs(List<InputAttribute> attrs);

    /* ATTRIBUTES */

    /**
     * Get attributes of Host specified by ID.
     * @param id id of Host
     * @param attrs attributes to be fetched
     * @return List of attributes.
     * @throws DatabaseIntegrityException More than one Host with same ID found.
     */
    List<Attribute> getHostAttrs(Long id, List<String> attrs) throws DatabaseIntegrityException;

}
