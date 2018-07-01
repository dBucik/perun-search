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
     * Get host specified by ID.
     * @param id id of host
     * @return Found host or null if not such found.
     * @throws DatabaseIntegrityException More than one Host with same ID found.
     */
    Host getHost(Long id) throws DatabaseIntegrityException;

    /**
     * Get Hosts with HOSTNAME like specified parameter.
     * (LIKE operator used, comparing ignores case)
     * @param hostname hostname of host
     * @return List of hosts, empty list if nothing has been found.
     */
    List<Host> getHostsByHostname(String hostname);

    /**
     * Get all hosts.
     * @return List of hosts, empty list if nothing has been found.
     */
    List<Host> getHosts();

    /**
     * Get attributes of host specified by ID.
     * @param id id of host
     * @param attrs attributes to be fetched
     * @return List of attributes.
     * @throws DatabaseIntegrityException More than one Host with same ID found.
     */
    List<Attribute> getHostAttrs(Long id, List<String> attrs) throws DatabaseIntegrityException;

    /**
     * Get hosts that have specified attributes.
     * (EXACT matching used)
     * @param attrs attributes of hosts
     * @return List of hosts found, empty list if nothing has been found.
     */
    List<Host> getHostsWithAttrs(List<InputAttribute> attrs);
}
