package cz.muni.ics.DAOs;

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
     */
    Host getHost(Long id);

    /**
     * Get host specified by HOSTNAME.
     * @param hostname hostname of host
     * @return Found host or null if not such found.
     */
    Host getHostByHostname(String hostname);

    /**
     * Get all hosts.
     * @return List of hosts, null if nothing has been found.
     */
    List<Host> getHosts();

    /**
     * Get attributes of host specified by ID.
     * @param id id of host
     * @param attrs attributes to be fetched
     * @return List of attributes.
     */
    List<Attribute> getHostAttrs(Long id, List<InputAttribute> attrs);

    /**
     * Get hosts that have specified attributes. (Exact matching used)
     * @param attrs attributes of hosts
     * @return List of hosts found, empty list if nothing has been found.
     */
    List<Host> getHostsWithAttrs(List<InputAttribute> attrs);
}
