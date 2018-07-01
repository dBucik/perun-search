package cz.muni.ics.models;

import com.coxautodev.graphql.tools.GraphQLRootResolver;
import cz.muni.ics.DAOs.*;
import cz.muni.ics.exceptions.DatabaseIntegrityException;

import java.util.List;

/**
 * Query object for GraphQL schema. Contains all available queries.
 *
 * @author Dominik Frantisek Bucik <bucik@ics.muni.cz>
 */
@SuppressWarnings("unused")
public class Query implements GraphQLRootResolver {

    //TODO: write JavaDoc
    private final DestinationDAO destinationDAO;
    private final ExtSourceDAO extSourceDAO;
    private final FacilityDAO facilityDAO;
    private final GroupDAO groupDAO;
    private final HostDAO hostDAO;
    private final MemberDAO memberDAO;
    private final ResourceDAO resourceDAO;
    private final ServiceDAO serviceDAO;
    private final UserDAO userDAO;
    private final UserExtSourceDAO userExtSourceDAO;
    private final VoDAO voDAO;

    public Query(DestinationDAO destinationDAO, ExtSourceDAO extSourceDAO,
                 FacilityDAO facilityDAO, GroupDAO groupDAO,
                 HostDAO hostDAO, MemberDAO memberDAO,
                 ResourceDAO resourceDAO, ServiceDAO serviceDAO,
                 UserDAO userDAO, UserExtSourceDAO userExtSourceDAO,
                 VoDAO voDAO) {
        this.destinationDAO = destinationDAO;
        this.extSourceDAO = extSourceDAO;
        this.facilityDAO = facilityDAO;
        this.groupDAO = groupDAO;
        this.hostDAO = hostDAO;
        this.memberDAO = memberDAO;
        this.resourceDAO = resourceDAO;
        this.serviceDAO = serviceDAO;
        this.userDAO = userDAO;
        this.userExtSourceDAO = userExtSourceDAO;
        this.voDAO = voDAO;
    }

    /* DESTINATION */

    /**
     * Get Destination specified by ID.
     * @param id id of Destination
     * @return Found Destination or null if no such found.
     * @throws DatabaseIntegrityException Thrown when more than one Destinations with the same ID found.
     */
    public Destination getDestination(Long id) throws DatabaseIntegrityException { return destinationDAO.getDestination(id); }

    /**
     * Get all Destinations.
     * @return List of Destinations, empty list if nothing has been found.
     */
    public List<Destination> getAllDestinations() { return destinationDAO.getDestinations(); }

    /**
     * Get Destinations with NAMEs like specified parameter.
     * (LIKE operator used, comparing ignores case)
     * @param name substring in Destination name
     * @return List of Destinations, empty list if nothing has been found.
     */
    public List<Destination> getDestinationsByName(String name) {
        return destinationDAO.getDestinationsByName(name);
    }

    /* EXT_SOURCE */

    /**
     * Get ExtSource specified by ID.
     * @param id id of extSource
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return Found extSource or null if not such found.
     * @throws DatabaseIntegrityException More than one ExtSource with same ID found.
     */
    public ExtSource getExtSource(Long id, boolean withAttrs) throws DatabaseIntegrityException {
        return extSourceDAO.getExtSource(id, withAttrs);
    }


    /**
     * Get all ExtSources.
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return List of ExtSources, empty list if nothing has been found.
     */
    public List<ExtSource> getExtSources(boolean withAttrs) {
        return extSourceDAO.getExtSources(withAttrs);
    }

    /**
     * Get attributes of ExtSource specified by ID.
     * @param id id of ExtSource
     * @param attrs attributes to be fetched
     * @return List of attributes.
     * @throws DatabaseIntegrityException More than one ExtSource with same ID found.
     */
    public List<Attribute> getExtSourceAttrs(Long id, List<String> attrs) throws DatabaseIntegrityException {
        return extSourceDAO.getExtSourceAttrs(id, attrs);
    }

    /**
     * Get ExtSources with NAME like specified parameter.
     * (LIKE operator used, comparing ignores case)
     * @param name name of ExtSource
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return List of ExtSources, empty list if nothing has been found.
     */
    public List<ExtSource> getExtSourcesByName(String name, boolean withAttrs) {
        return extSourceDAO.getExtSourcesByName(name, withAttrs);
    }

    /**
     * Get ExtSources with TYPE like specified parameter.
     * (LIKE operator used, comparing ignores case)
     * @param type type of ExtSource
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return List of ExtSources, empty list if nothing has been found.
     */
    public List<ExtSource> getExtSourcesByType(String type, boolean withAttrs) {
        return extSourceDAO.getExtSourcesByType(type, withAttrs);
    }

    /**
     * Get ExtSources that have specified attributes.
     * (EXACT matching used)
     * @param attrs attributes of ExtSources
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return List of ExtSources, empty list if nothing has been found.
     */
    public List<ExtSource> getExtSourcesWithAttrs(List<InputAttribute> attrs, boolean withAttrs) {
        return extSourceDAO.getExtSourcesWithAttrs(attrs, withAttrs);
    }

    /* FACILITY */

    /**
     * Get Facility specified by ID.
     * @param id id of Facility
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return Found Facility or null if not such found.
     * @throws DatabaseIntegrityException More than one Facility with same ID found.
     */
    public Facility getFacility(Long id, boolean withAttrs) throws DatabaseIntegrityException {
        return facilityDAO.getFacility(id, withAttrs);
    }

    /**
     * Get all Facilities.
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return List of Facilities, empty list if nothing has been found.
     */
    public List<Facility> getFacilities(boolean withAttrs) {
        return facilityDAO.getFacilities(withAttrs);
    }

    /**
     * Get attributes of Facility specified by ID.
     * @param id id of Facility
     * @param attrs attributes to be fetched
     * @return List of attributes.
     * * @throws DatabaseIntegrityException More than one Facility with same ID found.
     */
    public List<Attribute> getFacilityAttrs(Long id, List<String> attrs) throws DatabaseIntegrityException {
        return facilityDAO.getFacilityAttrs(id, attrs);
    }

    /**
     * Get Facilities that have specified attributes.
     * (EXACT matching used)
     * @param attrs attributes of Facilities
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return List of Facilities found, empty list if nothing has been found.
     */
    public List<Facility> getFacilitiesByAttrs(List<InputAttribute> attrs, boolean withAttrs) {
        return facilityDAO.getFacilitiesByAttrs(attrs, withAttrs);
    }

    /**
     * Get Facilities with NAME like specified parameter.
     * (LIKE operator used, comparing ignores case)
     * @param name name of Facility
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return List of Facilities, empty list if nothing has been found.
     */
    public List<Facility> getFacilitiesByName(String name, boolean withAttrs) {
        return facilityDAO.getFacilitiesByName(name, withAttrs);
    }

    /* GROUP */

    /**
     * Get Group specified by ID.
     * @param id id of Group
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return Found Group or null if not such found.
     * @throws DatabaseIntegrityException More than one Group with same ID found.
     */
    public Group getGroup(Long id, boolean withAttrs) throws DatabaseIntegrityException {
        return groupDAO.getGroup(id, withAttrs);
    }

    /**
     * Get Groups withe NAME like specified parameter.
     * (LIKE operator used, comparing ignores case)
     * @param name name of Group
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return List of Groups, empty list if nothing has been found.
     */
    public List<Group> getGroupsByName(String name, boolean withAttrs) {
        return groupDAO.getGroupsByName(name, withAttrs);
    }

    /**
     * Get all Groups.
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return List of Groups, empty list if nothing has been found.
     */
    public List<Group> getGroups(boolean withAttrs) {
        return groupDAO.getGroups(withAttrs);
    }

    /**
     * Get attributes of Group specified by ID.
     * @param id id of Group
     * @param attrs attributes to be fetched
     * @return List of attributes.
     * @throws DatabaseIntegrityException More than one Group with same ID found.
     */
    public List<Attribute> getGroupAttrs(Long id, List<String> attrs) throws DatabaseIntegrityException {
        return groupDAO.getGroupAttrs(id, attrs);
    }

    /**
     * Get Groups that have specified attributes.
     * (EXACT matching used)
     * @param attrs attributes of Groups
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return List of Groups found, empty list if nothing has been found.
     */
    public List<Group> getGroupsWithAttrs(List<InputAttribute> attrs, boolean withAttrs) {
        return groupDAO.getGroupsWithAttrs(attrs, withAttrs);
    }

    /**
     * Get parent group of Group specified by ID.
     * @param childGroupId id of Group whose parent has to be found
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return Parent Group.
     * @throws DatabaseIntegrityException More than one Group with same ID found.
     *                                    No parent group found for Group with specified ID.
     */
    public Group getParentGroup(Long childGroupId, boolean withAttrs) throws DatabaseIntegrityException {
        return groupDAO.getParentGroup(childGroupId, withAttrs);
    }

    /**
     * Get all Groups of VO specified by ID.
     * @param voId id of VO
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return List of Groups, empty list if nothing has been found.
     */
    public List<Group> getGroupsOfVo(Long voId, boolean withAttrs) {
        return groupDAO.getGroupsOfVo(voId, withAttrs);
    }

    /* HOST */

    /**
     * Get Host specified by ID.
     * @param id id of Host
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return Found Host or null if not such found.
     * @throws DatabaseIntegrityException More than one Host with same ID found.
     */
    public Host getHost(Long id, boolean withAttrs) throws DatabaseIntegrityException {
        return hostDAO.getHost(id, withAttrs);
    }

    /**
     * Get Hosts with HOSTNAME like specified parameter.
     * (LIKE operator used, comparing ignores case)
     * @param hostname Hostname of Host
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return List of Hosts, empty list if nothing has been found.
     */
    public List<Host> getHostsByHostname(String hostname, boolean withAttrs) {
        return hostDAO.getHostsByHostname(hostname, withAttrs);
    }

    /**
     * Get all Hosts.
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return List of Hosts, empty list if nothing has been found.
     */
    public List<Host> getHosts(boolean withAttrs) {
        return hostDAO.getHosts(withAttrs);
    }

    /**
     * Get attributes of Host specified by ID.
     * @param id id of Host
     * @param attrs attributes to be fetched
     * @return List of attributes.
     * @throws DatabaseIntegrityException More than one Host with same ID found.
     */
    public List<Attribute> getHostAttrs(Long id, List<String> attrs) throws DatabaseIntegrityException {
        return hostDAO.getHostAttrs(id, attrs);
    }

    /**
     * Get Hosts that have specified attributes.
     * (EXACT matching used)
     * @param attrs attributes of Hosts
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return List of Hosts, empty list if nothing has been found.
     */
    public List<Host> getHostsWithAttrs(List<InputAttribute> attrs, boolean withAttrs) {
        return hostDAO.getHostsWithAttrs(attrs, withAttrs);
    }

    /* MEMBER */

    /**
     * Get Member specified by ID.
     * @param id id of Member
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return Found Member or null if not such found.
     * @throws DatabaseIntegrityException More than one Member with same ID found.
     */
    public Member getMember(Long id, boolean withAttrs) throws DatabaseIntegrityException {
        return memberDAO.getMember(id, withAttrs);
    }

    /**
     * Get all Members.
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return List of Members, null if nothing has been found.
     */
    public List<Member> getMembers(boolean withAttrs) {
        return memberDAO.getMembers(withAttrs);
    }

    /**
     * Get attributes of Member specified by ID.
     * @param id id of Member
     * @param attrs attributes to be fetched
     * @return List of attributes
     * @throws DatabaseIntegrityException More than one Member with same ID found.
     */
    public List<Attribute> getMemberAttrs(Long id, List<String> attrs) throws DatabaseIntegrityException {
        return memberDAO.getMemberAttrs(id, attrs);
    }

    /**
     * Get Members that have specified attributes. (Exact matching used)
     * @param attrs attributes of Members
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return List of Members found, empty list if nothing has been found.
     */
    public List<Member> getMembersWithAttrs(List<InputAttribute> attrs, boolean withAttrs) {
        return memberDAO.getMembersWithAttrs(attrs, withAttrs);
    }

    /**
     * Get Members of user specified by ID.
     * @param userId id of user
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return List of Members, empty list if nothing has been found.
     */
    public List<Member> getMembersOfUser(Long userId, boolean withAttrs) {
        return memberDAO.getMembersOfUser(userId, withAttrs);
    }

    /**
     * Get Members of vo specified by ID.
     * @param voId id of vo.
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return List of Members, empty list if nothing has been found.
     */
    public List<Member> getMembersOfVo(Long voId, boolean withAttrs) {
        return memberDAO.getMembersOfVo(voId, withAttrs);
    }

    /**
     * Get Members with specified STATUS.
     * @param status ACTIVE or EXPIRED values are accepted
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return List of Members, empty list if nothing has been found.
     */
    public List<Member> getMembersByStatus(String status, boolean withAttrs) {
        return memberDAO.getMembersByStatus(status, withAttrs);
    }

    /**
     * Get Members with specified value for sponsorship.
     * @param isSponsored TRUE if Member is sponsored, FALSE otherwise
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return List of Members, empty list if nothing has been found.
     */
    public List<Member> getMembersBySponsored(boolean isSponsored, boolean withAttrs) {
        return memberDAO.getMembersBySponsored(isSponsored, withAttrs);
    }

    /* RESOURCE */

    /**
     * Get Resource specified by ID.
     * @param id id of Resource
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return Found Resource or null if not such found.
     * @throws DatabaseIntegrityException More than one Resource with same ID found.
     */
    public Resource getResource(Long id, boolean withAttrs) throws DatabaseIntegrityException {
        return resourceDAO.getResource(id, withAttrs);
    }

    /**
     * Get Resource with NAME like specified parameter.
     * (Operator LIKE used, comparing ignores case)
     * @param name name of Resource
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return List of Resources, empty list if nothing has been found.
     */
    public List<Resource> getResourcesByName(String name, boolean withAttrs) {
        return resourceDAO.getResourcesByName(name, withAttrs);
    }

    /**
     * Get all Resources.
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return List of Resources, empty list if nothing has been found.
     */
    public List<Resource> getResources(boolean withAttrs) {
        return resourceDAO.getResources(withAttrs);
    }

    /**
     * Get attributes of Resource specified by ID.
     * @param id id of Resource
     * @param attrs attributes to be fetched
     * @return List of attributes.
     * @throws DatabaseIntegrityException More than one Resource with same ID found.
     */
    public List<Attribute> getResourceAttrs(Long id, List<String> attrs) throws DatabaseIntegrityException {
        return resourceDAO.getResourceAttrs(id, attrs);
    }

    /**
     * Get Resources that have specified attributes.
     * (EXACT matching used)
     * @param attrs attributes of Resources
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return List of Resources, empty list if nothing has been found.
     */
    public List<Resource> getResourcesWithAttrs(List<InputAttribute> attrs, boolean withAttrs) {
        return resourceDAO.getResourcesWithAttrs(attrs, withAttrs);
    }

    /**
     * Get Resources of Facility specified by ID.
     * @param facilityId id of Facility
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return List of Resources, empty list if nothing has been found.
     */
    public List<Resource> getResourcesOfFacility(Long facilityId, boolean withAttrs) {
        return resourceDAO.getResourcesOfFacility(facilityId, withAttrs);
    }

    /**
     * Get Resources of Vo specified by ID.
     * @param voId id of Vo
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return List of Resources, empty list if nothing has been found.
     */
    public List<Resource> getResourcesOfVo(Long voId, boolean withAttrs) {
        return resourceDAO.getResourcesOfVo(voId, withAttrs);
    }

    /* SERVICE */

    /**
     * Get Service specified by ID.
     * @param id id of Service
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return Found Service or null if not such found.
     * @throws DatabaseIntegrityException When more than one Service with same id found.
     */
    public Service getService(Long id, boolean withAttrs) throws DatabaseIntegrityException {
        return serviceDAO.getService(id, withAttrs);
    }

    /**
     * Get Services with NAME like specified parameter.
     * @param name name of Service
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return List of Services, empty list if nothing has been found.
     */
    public List<Service> getServicesByName(String name, boolean withAttrs) {
        return serviceDAO.getServicesByName(name, withAttrs);
    }

    /**
     * Get all Services.
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return List of Services, empty list if nothing has been found.
     */
    public List<Service> getServices(boolean withAttrs) {
        return serviceDAO.getServices(withAttrs);
    }

    /**
     * Get attributes of Service specified by ID.
     * @param id id of Service
     * @param attrs attributes to be fetched
     * @return List of attributes
     * @throws DatabaseIntegrityException When more than one Resource with same id found.
     */
    public List<Attribute> getServiceAttrs(Long id, List<String> attrs) throws DatabaseIntegrityException {
        return serviceDAO.getServiceAttrs(id, attrs);
    }

    /**
     * Get Services that have specified attributes.
     * (EXACT matching used)
     * @param attrs attributes of Services
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return List of Services, empty list if nothing has been found.
     */
    public List<Service> getServicesWithAttrs(List<InputAttribute> attrs, boolean withAttrs) {
        return serviceDAO.getServicesWithAttrs(attrs, withAttrs);
    }

    /**
     * Get resources of Owner specified by ID.
     * @param ownerId id of Owner
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return List of Services, empty list if nothing has been found.
     */
    public List<Service> getServicesOfOwner(Long ownerId, boolean withAttrs) {
        return serviceDAO.getServicesOfOwner(ownerId, withAttrs);
    }

    /* USER */

    /**
     * Get user specified by ID.
     * @param id id of user
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return Found user or null if not such found.
     */
    public User getUser(Long id, boolean withAttrs) throws DatabaseIntegrityException {
        return userDAO.getUser(id, withAttrs);
    }

    /**
     * Get all users.
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return List of users, empty list if nothing has been found.
     */
    public List<User> getUsers(boolean withAttrs) {
        return userDAO.getUsers(withAttrs);
    }

    /**
     * Get attributes of user specified by ID.
     * @param id id of user
     * @param attrs attributes to be fetched
     * @return List of attributes, empty list if nothing has been found.
     */
    public List<Attribute> getUserAttrs(Long id, List<String> attrs) throws DatabaseIntegrityException {
        return userDAO.getUserAttrs(id, attrs);
    }

    /**
     * Get users that have specified attributes. (Exact matching used)
     * @param attrs attributes of users
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return List of users found, empty list if nothing has been found.
     */
    public List<User> getUsersWithAttrs(List<InputAttribute> attrs, boolean withAttrs) {
        return userDAO.getUsersWithAttrs(attrs, withAttrs);
    }

    /**
     * Get users with specified NAME. (LIKE operator used)
     * @param titleBefore title before the name
     * @param firstName given name of user
     * @param middleName middle name of user
     * @param lastName family name of user
     * @param titleAfter title after the name
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return List of users found, empty list if nothing has been found.
     */
    public List<User> getUsersByName(String titleBefore, String firstName, String middleName,
                              String lastName, String titleAfter, boolean withAttrs) {
        return userDAO.getUsersByName(titleBefore, firstName, middleName, lastName, titleAfter, withAttrs);
    }

    /**
     * Get users by specifying if their acc is serviceAcc.
     * @param isServiceAcc TRUE for serviceAccounts, FALSE otherwise.
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return List of users found, empty list if nothing has been found.
     */
    public List<User> getUsersByServiceAcc(boolean isServiceAcc, boolean withAttrs) {
        return userDAO.getUsersByServiceAcc(isServiceAcc, withAttrs);
    }

    /**
     * Get users by specifying if their acc is sponsored.
     * @param isSponsoredAcc TRUE for sponsoredAccounts, FALSE otherwise.
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return List of users found, empty list if nothing has been found.
     */
    public List<User> getUsersBySponsoredAcc(boolean isSponsoredAcc, boolean withAttrs) {
        return userDAO.getUsersBySponsoredAcc(isSponsoredAcc, withAttrs);
    }

    /* USER_EXT_SOURCE */
    /**
     * Get userExtSource specified by ID.
     * @param id id of userExtSource
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return Found userExtSource or null if not such found.
     */
    public UserExtSource getUserExtSource(Long id, boolean withAttrs) throws DatabaseIntegrityException {
        return userExtSourceDAO.getUserExtSource(id, withAttrs);
    }

    /**
     * Get all userExtSources.
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise 
     * @return List of userExtSources, null if nothing has been found.
     */
    public List<UserExtSource> getUserExtSources(boolean withAttrs) {
        return userExtSourceDAO.getUserExtSources(withAttrs);
    }

    /**
     * Get attributes of userExtSource specified by ID.
     * @param id id of userExtSource
     * @param attrs attributes to be fetched
     * @return List of attributes.
     */
    public List<Attribute> getUserExtSourceAttrs(Long id, List<String> attrs) throws DatabaseIntegrityException {
        return userExtSourceDAO.getUserExtSourceAttrs(id, attrs);
    }

    /**
     * Get userExtSources that have specified attributes. (Exact matching used)
     * @param attrs attributes of userExtSources
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return List of userExtSources found, empty list if nothing has been found.
     */
    public List<UserExtSource> getUserExtSourcesWithAttrs(List<InputAttribute> attrs, boolean withAttrs) {
        return userExtSourceDAO.getUserExtSourcesWithAttrs(attrs, withAttrs);
    }

    /**
     * Get userExtSources of user specified by ID.
     * @param userId id of user
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return List of userExtSources found, empty list if nothing has been found.
     */
    public List<UserExtSource> getUserExtSourcesOfUser(Long userId, boolean withAttrs) {
        return userExtSourceDAO.getUserExtSourcesOfUser(userId, withAttrs);
    }

    /**
     * Get userExtSources of extSource specified by ID.
     * @param extSourceId id of extSource
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return List of userExtSources found, empty list if nothing has been found.
     */
    public List<UserExtSource> getUserExtSourcesOfExtSource(Long extSourceId, boolean withAttrs) {
        return userExtSourceDAO.getUserExtSourcesOfExtSource(extSourceId, withAttrs);
    }

    /**
     * Get userExtSources with specified loginExt
     * @param loginExt loginExt of userExtSource
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return List of userExtSources found, empty list if nothing has been found.
     */
    public List<UserExtSource> getUserExtSourcesByLoginExt(String loginExt, boolean withAttrs) {
        return userExtSourceDAO.getUserExtSourcesByLoginExt(loginExt, withAttrs);
    }

    /* VO */

    /**
     * Get vo specified by ID.
     * @param id id of vo
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return Found vo or null if not such found.
     */
    public Vo getVo(Long id, boolean withAttrs) throws DatabaseIntegrityException {
        return voDAO.getVo(id, withAttrs);
    }

    /**
     * Get vos with names like specified parameter. (LIKE operator used, comparing ignores case)
     * @param name substring in name of vo
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return List of vos, empty list if nothing has been found.
     */
    public List<Vo> getVosByName(String name, boolean withAttrs) {
        return voDAO.getVosByName(name, withAttrs);
    }

    /**
     * Get vo specified by SHORT NAME. (EXACT MATCH, comparing ignores case)
     * @param shortName short name of vo
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return Found vo or null if not such found.
     */
    public Vo getVoByShortName(String shortName, boolean withAttrs) throws DatabaseIntegrityException {
        return voDAO.getVoByShortName(shortName, withAttrs);
    }

    /**
     * Get vos with short names like specified parameter. (LIKE operator used, comparing ignores case)
     * @param shortName substring in short_name of vo
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return List of vos, empty list if nothing has been found.
     */
    public List<Vo> getVosByShortName(String shortName, boolean withAttrs) {
        return voDAO.getVosByShortName(shortName, withAttrs);
    }

    /**
     * Get all vos.
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return List of vos, empty list if nothing has been found.
     */
    public List<Vo> getVos(boolean withAttrs) {
        return voDAO.getVos(withAttrs);
    }

    /**
     * Get attributes of vo specified by ID. Only attributes with value are returned.
     * @param id id of vo
     * @param attrs attributes to be fetched
     * @return List of attributes, empty list if nothing has been found.
     */
    public List<Attribute> getVoAttrs(Long id, List<String> attrs) throws DatabaseIntegrityException {
        return voDAO.getVoAttrs(id, attrs);
    }

    /**
     * Get vos that have specified attributes. (Exact matching used)
     * @param attrs attributes of vos
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return List of vos found, empty list if nothing has been found.
     */
    public List<Vo> getVosWithAttrs(List<InputAttribute> attrs, boolean withAttrs) {
        return voDAO.getVosWithAttrs(attrs, withAttrs);
    }
}
