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
     * @return Found extSource or null if not such found.
     * @throws DatabaseIntegrityException More than one ExtSource with same ID found.
     */
    public ExtSource getExtSource(Long id) throws DatabaseIntegrityException {
        return extSourceDAO.getExtSource(id);
    }

    /**
     * Get all ExtSources.
     * @return List of ExtSources, empty list if nothing has been found.
     */
    public List<ExtSource> getExtSources() {
        return extSourceDAO.getExtSources();
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
     * @return List of ExtSources, empty list if nothing has been found.
     */
    public List<ExtSource> getExtSourcesByName(String name) {
        return extSourceDAO.getExtSourcesByName(name);
    }

    /**
     * Get ExtSources with TYPE like specified parameter.
     * (LIKE operator used, comparing ignores case)
     * @param type type of ExtSource
     * @return List of ExtSources, empty list if nothing has been found.
     */
    public List<ExtSource> getExtSourcesByType(String type) {
        return extSourceDAO.getExtSourcesByType(type);
    }

    /**
     * Get ExtSources that have specified attributes.
     * (EXACT matching used)
     * @param attrs attributes of ExtSources
     * @return List of ExtSources, empty list if nothing has been found.
     */
    public List<ExtSource> getExtSourcesWithAttrs(List<InputAttribute> attrs) {
        return extSourceDAO.getExtSourcesWithAttrs(attrs);
    }

    /* FACILITY */

    /**
     * Get Facility specified by ID.
     * @param id id of Facility
     * @return Found Facility or null if not such found.
     * @throws DatabaseIntegrityException More than one Facility with same ID found.
     */
    public Facility getFacility(Long id) throws DatabaseIntegrityException {
        return facilityDAO.getFacility(id);
    }

    /**
     * Get all Facilities.
     * @return List of Facilities, empty list if nothing has been found.
     */
    public List<Facility> getFacilities() {
        return facilityDAO.getFacilities();
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
     * @return List of Facilities found, empty list if nothing has been found.
     */
    public List<Facility> getFacilitiesByAttrs(List<InputAttribute> attrs) {
        return facilityDAO.getFacilitiesByAttrs(attrs);
    }

    /**
     * Get Facilities with NAME like specified parameter.
     * (LIKE operator used, comparing ignores case)
     * @param name name of Facility
     * @return List of Facilities, empty list if nothing has been found.
     */
    public List<Facility> getFacilitiesByName(String name) {
        return facilityDAO.getFacilitiesByName(name);
    }

    /* GROUP */

    /**
     * Get Group specified by ID.
     * @param id id of Group
     * @return Found Group or null if not such found.
     * @throws DatabaseIntegrityException More than one Group with same ID found.
     */
    public Group getGroup(Long id) throws DatabaseIntegrityException {
        return groupDAO.getGroup(id);
    }

    /**
     * Get Groups withe NAME like specified parameter.
     * (LIKE operator used, comparing ignores case)
     * @param name name of Group
     * @return List of Groups, empty list if nothing has been found.
     */
    public List<Group> getGroupsByName(String name) {
        return groupDAO.getGroupsByName(name);
    }

    /**
     * Get all Groups.
     * @return List of Groups, empty list if nothing has been found.
     */
    public List<Group> getGroups() {
        return groupDAO.getGroups();
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
     * @return List of Groups found, empty list if nothing has been found.
     */
    public List<Group> getGroupsWithAttrs(List<InputAttribute> attrs) {
        return groupDAO.getGroupsWithAttrs(attrs);
    }

    /**
     * Get parent group of Group specified by ID.
     * @param childGroupId id of Group whose parent has to be found
     * @return Parent Group.
     * @throws DatabaseIntegrityException More than one Group with same ID found.
     *                                    No parent group found for Group with specified ID.
     */
    public Group getParentGroup(Long childGroupId) throws DatabaseIntegrityException {
        return groupDAO.getParentGroup(childGroupId);
    }

    /**
     * Get all Groups of VO specified by ID.
     * @param voId id of VO
     * @return List of Groups, empty list if nothing has been found.
     */
    public List<Group> getGroupsOfVo(Long voId) {
        return groupDAO.getGroupsOfVo(voId);
    }

    /* HOST */

    /**
     * Get Host specified by ID.
     * @param id id of Host
     * @return Found Host or null if not such found.
     * @throws DatabaseIntegrityException More than one Host with same ID found.
     */
    public Host getHost(Long id) throws DatabaseIntegrityException {
        return hostDAO.getHost(id);
    }

    /**
     * Get Hosts with HOSTNAME like specified parameter.
     * (LIKE operator used, comparing ignores case)
     * @param hostname Hostname of Host
     * @return List of Hosts, empty list if nothing has been found.
     */
    public List<Host> getHostsByHostname(String hostname) {
        return hostDAO.getHostsByHostname(hostname);
    }

    /**
     * Get all Hosts.
     * @return List of Hosts, empty list if nothing has been found.
     */
    public List<Host> getHosts() {
        return hostDAO.getHosts();
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
     * @return List of Hosts, empty list if nothing has been found.
     */
    public List<Host> getHostsWithAttrs(List<InputAttribute> attrs) {
        return hostDAO.getHostsWithAttrs(attrs);
    }

    /* MEMBER */

    /**
     * Get Member specified by ID.
     * @param id id of Member
     * @return Found Member or null if not such found.
     * @throws DatabaseIntegrityException More than one Member with same ID found.
     */
    public Member getMember(Long id) throws DatabaseIntegrityException {
        return memberDAO.getMember(id);
    }

    /**
     * Get all Members.
     * @return List of Members, null if nothing has been found.
     */
    public List<Member> getMembers() {
        return memberDAO.getMembers();
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
     * @return List of Members found, empty list if nothing has been found.
     */
    public List<Member> getMembersWithAttrs(List<InputAttribute> attrs) {
        return memberDAO.getMembersWithAttrs(attrs);
    }

    /**
     * Get Members of user specified by ID.
     * @param userId id of user
     * @return List of Members, empty list if nothing has been found.
     */
    public List<Member> getMembersOfUser(Long userId) {
        return memberDAO.getMembersOfUser(userId);
    }

    /**
     * Get Members of vo specified by ID.
     * @param voId id of vo.
     * @return List of Members, empty list if nothing has been found.
     */
    public List<Member> getMembersOfVo(Long voId) {
        return memberDAO.getMembersOfVo(voId);
    }

    /**
     * Get Members with specified STATUS.
     * @param status ACTIVE or EXPIRED values are accepted
     * @return List of Members, empty list if nothing has been found.
     */
    public List<Member> getMembersByStatus(String status) {
        return memberDAO.getMembersByStatus(status);
    }

    /**
     * Get Members with specified value for sponsorship.
     * @param isSponsored TRUE if Member is sponsored, FALSE otherwise
     * @return List of Members, empty list if nothing has been found.
     */
    public List<Member> getMembersBySponsored(boolean isSponsored) {
        return memberDAO.getMembersBySponsored(isSponsored);
    }

    /* RESOURCE */

    /**
     * Get Resource specified by ID.
     * @param id id of Resource
     * @return Found Resource or null if not such found.
     * @throws DatabaseIntegrityException More than one Resource with same ID found.
     */
    public Resource getResource(Long id) throws DatabaseIntegrityException {
        return resourceDAO.getResource(id);
    }

    /**
     * Get Resource with NAME like specified parameter.
     * (Operator LIKE used, comparing ignores case)
     * @param name name of Resource
     * @return List of Resources, empty list if nothing has been found.
     */
    public List<Resource> getResourcesByName(String name) {
        return resourceDAO.getResourcesByName(name);
    }

    /**
     * Get all Resources.
     * @return List of Resources, empty list if nothing has been found.
     */
    public List<Resource> getResources() {
        return resourceDAO.getResources();
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
     * @return List of Resources, empty list if nothing has been found.
     */
    public List<Resource> getResourcesWithAttrs(List<InputAttribute> attrs) {
        return resourceDAO.getResourcesWithAttrs(attrs);
    }

    /**
     * Get Resources of Facility specified by ID.
     * @param facilityId id of Facility
     * @return List of Resources, empty list if nothing has been found.
     */
    public List<Resource> getResourcesOfFacility(Long facilityId) {
        return resourceDAO.getResourcesOfFacility(facilityId);
    }

    /**
     * Get Resources of Vo specified by ID.
     * @param voId id of Vo
     * @return List of Resources, empty list if nothing has been found.
     */
    public List<Resource> getResourcesOfVo(Long voId) {
        return resourceDAO.getResourcesOfVo(voId);
    }

    /* SERVICE */

    /**
     * Get Service specified by ID.
     * @param id id of Service
     * @return Found Service or null if not such found.
     * @throws DatabaseIntegrityException When more than one Service with same id found.
     */
    public Service getService(Long id) throws DatabaseIntegrityException {
        return serviceDAO.getService(id);
    }

    /**
     * Get Services with NAME like specified parameter.
     * @param name name of Service
     * @return List of Services, empty list if nothing has been found.
     */
    public List<Service> getServicesByName(String name) {
        return serviceDAO.getServicesByName(name);
    }

    /**
     * Get all Services.
     * @return List of Services, empty list if nothing has been found.
     */
    public List<Service> getServices() {
        return serviceDAO.getServices();
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
     * @return List of Services, empty list if nothing has been found.
     */
    public List<Service> getServicesWithAttrs(List<InputAttribute> attrs) {
        return serviceDAO.getServicesWithAttrs(attrs);
    }

    /**
     * Get resources of Owner specified by ID.
     * @param ownerId id of Owner
     * @return List of Services, empty list if nothing has been found.
     */
    public List<Service> getServicesOfOwner(Long ownerId) {
        return serviceDAO.getServicesOfOwner(ownerId);
    }

    /* USER */

    /**
     * Get user specified by ID.
     * @param id id of user
     * @return Found user or null if not such found.
     */
    public User getUser(Long id) throws DatabaseIntegrityException {
        return userDAO.getUser(id);
    }

    /**
     * Get all users.
     * @return List of users, empty list if nothing has been found.
     */
    public List<User> getUsers() {
        return userDAO.getUsers();
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
     * @return List of users found, empty list if nothing has been found.
     */
    public List<User> getUsersWithAttrs(List<InputAttribute> attrs) {
        return userDAO.getUsersWithAttrs(attrs);
    }

    /**
     * Get users with specified NAME. (LIKE operator used)
     * @param titleBefore title before the name
     * @param firstName given name of user
     * @param middleName middle name of user
     * @param lastName family name of user
     * @param titleAfter title after the name
     * @return List of users found, empty list if nothing has been found.
     */
    public List<User> getUsersByName(String titleBefore, String firstName, String middleName,
                              String lastName, String titleAfter) {
        return userDAO.getUsersByName(titleBefore, firstName, middleName, lastName, titleAfter);
    }

    /**
     * Get users by specifying if their acc is serviceAcc.
     * @param isServiceAcc TRUE for serviceAccounts, FALSE otherwise.
     * @return List of users found, empty list if nothing has been found.
     */
    public List<User> getUsersByServiceAcc(boolean isServiceAcc) {
        return userDAO.getUsersByServiceAcc(isServiceAcc);
    }

    /**
     * Get users by specifying if their acc is sponsored.
     * @param isSponsoredAcc TRUE for sponsoredAccounts, FALSE otherwise.
     * @return List of users found, empty list if nothing has been found.
     */
    public List<User> getUsersBySponsoredAcc(boolean isSponsoredAcc) {
        return userDAO.getUsersBySponsoredAcc(isSponsoredAcc);
    }

    /* USER_EXT_SOURCE */
    /**
     * Get userExtSource specified by ID.
     * @param id id of userExtSource
     * @return Found userExtSource or null if not such found.
     */
    public UserExtSource getUserExtSource(Long id) {
        return userExtSourceDAO.getUserExtSource(id);
    }

    /**
     * Get all userExtSources.
     * @return List of userExtSources, null if nothing has been found.
     */
    public List<UserExtSource> getUserExtSources() { return userExtSourceDAO.getUserExtSources(); }

    /**
     * Get attributes of userExtSource specified by ID.
     * @param id id of userExtSource
     * @param attrs attributes to be fetched
     * @return List of attributes.
     */
    public List<Attribute> getUserExtSourceAttrs(Long id, List<String> attrs) {
        return userExtSourceDAO.getUserExtSourceAttrs(id, attrs);
    }

    /**
     * Get userExtSources that have specified attributes. (Exact matching used)
     * @param attrs attributes of userExtSources
     * @return List of userExtSources found, empty list if nothing has been found.
     */
    public List<UserExtSource> getUserExtSourcesWithAttrs(List<InputAttribute> attrs) {
        return userExtSourceDAO.getUserExtSourcesWithAttrs(attrs);
    }

    /**
     * Get userExtSources of user specified by ID.
     * @param userId id of user
     * @return List of userExtSources found, empty list if nothing has been found.
     */
    public List<UserExtSource> getUserExtSourcesOfUser(Long userId) {
        return userExtSourceDAO.getUserExtSourcesOfUser(userId);
    }

    /**
     * Get userExtSources of extSource specified by ID.
     * @param extSourceId id of extSource
     * @return List of userExtSources found, empty list if nothing has been found.
     */
    public List<UserExtSource> getUserExtSourcesOfExtSource(Long extSourceId) {
        return userExtSourceDAO.getUserExtSourcesOfExtSource(extSourceId);
    }

    /**
     * Get userExtSources with specified loginExt
     * @param loginExt loginExt of userExtSource
     * @return List of userExtSources found, empty list if nothing has been found.
     */
    public List<UserExtSource> getUserExtSourcesByLoginExt(String loginExt) {
        return userExtSourceDAO.getUserExtSourcesByLoginExt(loginExt);
    }

    /* VO */

    /**
     * Get vo specified by ID.
     * @param id id of vo
     * @return Found vo or null if not such found.
     */
    public Vo getVo(Long id) throws DatabaseIntegrityException {
        return voDAO.getVo(id);
    }

    /**
     * Get vos with names like specified parameter. (LIKE operator used, comparing ignores case)
     * @param name substring in name of vo
     * @return List of vos, empty list if nothing has been found.
     */
    public List<Vo> getVosByName(String name) {
        return voDAO.getVosByName(name);
    }

    /**
     * Get vo specified by SHORT NAME. (EXACT MATCH, comparing ignores case)
     * @param shortName short name of vo
     * @return Found vo or null if not such found.
     */
    public Vo getVoByShortName(String shortName) throws DatabaseIntegrityException {
        return voDAO.getVoByShortName(shortName);
    }

    /**
     * Get vos with short names like specified parameter. (LIKE operator used, comparing ignores case)
     * @param shortName substring in short_name of vo
     * @return List of vos, empty list if nothing has been found.
     */
    public List<Vo> getVosByShortName(String shortName) {
        return voDAO.getVosByShortName(shortName);
    }

    /**
     * Get all vos.
     * @return List of vos, empty list if nothing has been found.
     */
    public List<Vo> getVos() {
        return voDAO.getVos();
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
     * @return List of vos found, empty list if nothing has been found.
     */
    public List<Vo> getVosWithAttrs(List<InputAttribute> attrs) {
        return voDAO.getVosWithAttrs(attrs);
    }
}
