package cz.muni.ics.DAOs;

import cz.muni.ics.exceptions.DatabaseIntegrityException;
import cz.muni.ics.models.Attribute;
import cz.muni.ics.models.Host;
import cz.muni.ics.models.InputAttribute;

import javax.sql.DataSource;
import java.util.List;

public interface HostDAO {

    void setDataSource(DataSource dataSource);

    /**
     * Get Host specified by ID.
     * @param id id of Host
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return Found Host or null if not such found.
     * @throws DatabaseIntegrityException More than one Host with same ID found.
     */
    Host getHost(Long id, boolean withAttrs) throws DatabaseIntegrityException;

    /**
     * Get Hosts with HOSTNAME like specified parameter.
     * (LIKE operator used, comparing ignores case)
     * @param hostname Hostname of Host
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return List of Hosts, empty list if nothing has been found.
     */
    List<Host> getHostsByHostname(String hostname, boolean withAttrs);

    /**
     * Get all Hosts.
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return List of Hosts, empty list if nothing has been found.
     */
    List<Host> getHosts(boolean withAttrs);

    /**
     * Get attributes of Host specified by ID.
     * @param id id of Host
     * @param attrs attributes to be fetched
     * @return List of attributes.
     * @throws DatabaseIntegrityException More than one Host with same ID found.
     */
    List<Attribute> getHostAttrs(Long id, List<String> attrs) throws DatabaseIntegrityException;

    /**
     * Get Hosts that have specified attributes.
     * (EXACT matching used)
     * @param attrs attributes of Hosts
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return List of Hosts, empty list if nothing has been found.
     */
    List<Host> getHostsWithAttrs(List<InputAttribute> attrs, boolean withAttrs);
}
