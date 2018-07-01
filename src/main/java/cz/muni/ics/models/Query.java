package cz.muni.ics.models;

import com.coxautodev.graphql.tools.GraphQLRootResolver;
import cz.muni.ics.DAOs.*;
import cz.muni.ics.exceptions.DatabaseIntegrityException;
import cz.muni.ics.models.entities.*;
import cz.muni.ics.models.richEntities.*;

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
    public List<Destination> getDestinations() { return destinationDAO.getDestinations(); }

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
    public List<ExtSource> getExtSourcesHavingAttrs(List<InputAttribute> attrs) {
        return extSourceDAO.getExtSourcesHavingAttrs(attrs);
    }


    /* RICH_EXT_SOURCE */
    /**
     * Get RichExtSource specified by ID.
     * @param id id of extSource
     * @return Found extSource or null if not such found.
     * @throws DatabaseIntegrityException More than one RichExtSource with same ID found.
     */
    public RichExtSource getRichExtSource(Long id) throws DatabaseIntegrityException {
        return extSourceDAO.getRichExtSource(id);
    }

    /**
     * Get all RichExtSources.
     * @return List of RichExtSources, empty list if nothing has been found.
     */
    public List<RichExtSource> getRichExtSources() {
        return extSourceDAO.getRichExtSources();
    }

    /**
     * Get RichExtSources with NAME like specified parameter.
     * (LIKE operator used, comparing ignores case)
     * @param name name of RichExtSource
     * @return List of RichExtSources, empty list if nothing has been found.
     */
    public List<RichExtSource> getRichExtSourcesByName(String name) {
        return extSourceDAO.getRichExtSourcesByName(name);
    }

    /**
     * Get RichExtSources with TYPE like specified parameter.
     * (LIKE operator used, comparing ignores case)
     * @param type type of RichExtSource
     * @return List of RichExtSources, empty list if nothing has been found.
     */
    public List<RichExtSource> getRichExtSourcesByType(String type) {
        return extSourceDAO.getRichExtSourcesByType(type);
    }

    /**
     * Get RichExtSources that have specified attributes.
     * (EXACT matching used)
     * @param attrs attributes of RichExtSources
     * @return List of RichExtSources, empty list if nothing has been found.
     */
    public List<RichExtSource> getRichExtSourcesHavingAttrs(List<InputAttribute> attrs) {
        return extSourceDAO.getRichExtSourcesHavingAttrs(attrs);
    }

    /* EXT_SOURCE_ATTRS */

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
     * Get Facilities with NAME like specified parameter.
     * (LIKE operator used, comparing ignores case)
     * @param name name of Facility
     * @return List of Facilities, empty list if nothing has been found.
     */
    public List<Facility> getFacilitiesByName(String name) {
        return facilityDAO.getFacilitiesByName(name);
    }

    /**
     * Get Facilities that have specified attributes.
     * (EXACT matching used)
     * @param attrs attributes of Facilities
     * @return List of Facilities found, empty list if nothing has been found.
     */
    public List<Facility> getFacilitiesHavingAttrs(List<InputAttribute> attrs) {
        return facilityDAO.getFacilitiesHavingAttrs(attrs);
    }

    /* RICH_FACILITY */

    /**
     * Get RichFacility specified by ID.
     * @param id id of RichFacility
     * @return Found RichFacility or null if not such found.
     * @throws DatabaseIntegrityException More than one RichFacility with same ID found.
     */
    public RichFacility getRichFacility(Long id) throws DatabaseIntegrityException {
        return facilityDAO.getRichFacility(id);
    }

    /**
     * Get all RichFacilities.
     * @return List of RichFacilities, empty list if nothing has been found.
     */
    public List<RichFacility> getRichFacilities() {
        return facilityDAO.getRichFacilities();
    }

    /**
     * Get RichFacilities that have specified attributes.
     * (EXACT matching used)
     * @param attrs attributes of RichFacilities
     * @return List of RichFacilities found, empty list if nothing has been found.
     */
    public List<RichFacility> getRichFacilitiesHavingAttrs(List<InputAttribute> attrs) {
        return facilityDAO.getRichFacilitiesHavingAttrs(attrs);
    }

    /**
     * Get RichFacilities with NAME like specified parameter.
     * (LIKE operator used, comparing ignores case)
     * @param name name of RichFacility
     * @return List of RichFacilities, empty list if nothing has been found.
     */
    public List<RichFacility> getRichFacilitiesByName(String name) {
        return facilityDAO.getRichFacilitiesByName(name);
    }

    /* FACILITY_ATTRS */

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
     * Get all Groups.
     * @return List of Groups, empty list if nothing has been found.
     */
    public List<Group> getGroups() {
        return groupDAO.getGroups();
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

    /**
     * Get Groups that have specified attributes.
     * (EXACT matching used)
     * @param attrs attributes of Groups
     * @return List of Groups found, empty list if nothing has been found.
     */
    public List<Group> getGroupsHavingAttrs(List<InputAttribute> attrs) {
        return groupDAO.getGroupsHavingAttrs(attrs);
    }

    /* RICH_GROUP */

    /**
     * Get RichGroup specified by ID.
     * @param id id of RichGroup
     * @return Found RichGroup or null if not such found.
     * @throws DatabaseIntegrityException More than one RichGroup with same ID found.
     */
    public RichGroup getRichGroup(Long id) throws DatabaseIntegrityException {
        return groupDAO.getRichGroup(id);
    }

    /**
     * Get all RichGroups.
     * @return List of RichGroups, empty list if nothing has been found.
     */
    public List<RichGroup> getRichGroups() {
        return groupDAO.getRichGroups();
    }

    /**
     * Get RichGroups withe NAME like specified parameter.
     * (LIKE operator used, comparing ignores case)
     * @param name name of RichGroup
     * @return List of RichGroups, empty list if nothing has been found.
     */
    public List<RichGroup> getRichGroupsByName(String name) {
        return groupDAO.getRichGroupsByName(name);
    }

    /**
     * Get parent group of RichGroup specified by ID.
     * @param childRichGroupId id of RichGroup whose parent has to be found
     * @return Parent RichGroup.
     * @throws DatabaseIntegrityException More than one RichGroup with same ID found.
     *                                    No parent group found for RichGroup with specified ID.
     */
    public RichGroup getParentRichGroup(Long childRichGroupId) throws DatabaseIntegrityException {
        return groupDAO.getParentRichGroup(childRichGroupId);
    }

    /**
     * Get all RichGroups of VO specified by ID.
     * @param voId id of VO
     * @return List of RichGroups, empty list if nothing has been found.
     */
    public List<RichGroup> getRichGroupsOfVo(Long voId) {
        return groupDAO.getRichGroupsOfVo(voId);
    }

    /**
     * Get RichGroups that have specified attributes.
     * (EXACT matching used)
     * @param attrs attributes of RichGroups
     * @return List of RichGroups found, empty list if nothing has been found.
     */
    public List<RichGroup> getRichGroupsHavingAttrs(List<InputAttribute> attrs) {
        return groupDAO.getRichGroupsHavingAttrs(attrs);
    }

    /* GROUP_ATTRS */

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
     * Get all Hosts.
     * @return List of Hosts, empty list if nothing has been found.
     */
    public List<Host> getHosts() {
        return hostDAO.getHosts();
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
     * Get Hosts that have specified attributes.
     * (EXACT matching used)
     * @param attrs attributes of Hosts
     * @return List of Hosts, empty list if nothing has been found.
     */
    public List<Host> getHostsHavingAttrs(List<InputAttribute> attrs) {
        return hostDAO.getHostsHavingAttrs(attrs);
    }
    
    /* RICH_HOST */

    /**
     * Get RichHost specified by ID.
     * @param id id of RichHost
     * @return Found RichHost or null if not such found.
     * @throws DatabaseIntegrityException More than one RichHost with same ID found.
     */
    public RichHost getRichHost(Long id) throws DatabaseIntegrityException {
        return hostDAO.getRichHost(id);
    }

    /**
     * Get all RichHosts.
     * @return List of RichHosts, empty list if nothing has been found.
     */
    public List<RichHost> getRichHosts() {
        return hostDAO.getRichHosts();
    }

    /**
     * Get RichHosts with HOSTNAME like specified parameter.
     * (LIKE operator used, comparing ignores case)
     * @param hostname RichHostname of RichHost
     * @return List of RichHosts, empty list if nothing has been found.
     */
    public List<RichHost> getRichHostsByHostname(String hostname) {
        return hostDAO.getRichHostsByHostname(hostname);
    }

    /**
     * Get RichHosts that have specified attributes.
     * (EXACT matching used)
     * @param attrs attributes of RichHosts
     * @return List of RichHosts, empty list if nothing has been found.
     */
    public List<RichHost> getRichHostsHavingAttrs(List<InputAttribute> attrs) {
        return hostDAO.getRichHostsHavingAttrs(attrs);
    }

    /* HOST_ATTRS */

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

    /**
     * Get Members that have specified attributes. (Exact matching used)
     * @param attrs attributes of Members
     * @return List of Members found, empty list if nothing has been found.
     */
    public List<Member> getMembersHavingAttrs(List<InputAttribute> attrs) {
        return memberDAO.getMembersHavingAttrs(attrs);
    }

    /* RICH_MEMBER */

    /**
     * Get RichMember specified by ID.
     * @param id id of RichMember
     * @return Found RichMember or null if not such found.
     * @throws DatabaseIntegrityException More than one RichMember with same ID found.
     */
    public RichMember getRichMember(Long id) throws DatabaseIntegrityException {
        return memberDAO.getRichMember(id);
    }

    /**
     * Get all RichMembers.
     * @return List of RichMembers, null if nothing has been found.
     */
    public List<RichMember> getRichMembers() {
        return memberDAO.getRichMembers();
    }

    /**
     * Get RichMembers of user specified by ID.
     * @param userId id of user
     * @return List of RichMembers, empty list if nothing has been found.
     */
    public List<RichMember> getRichMembersOfUser(Long userId) {
        return memberDAO.getRichMembersOfUser(userId);
    }

    /**
     * Get RichMembers of vo specified by ID.
     * @param voId id of vo.
     * @return List of RichMembers, empty list if nothing has been found.
     */
    public List<RichMember> getRichMembersOfVo(Long voId) {
        return memberDAO.getRichMembersOfVo(voId);
    }

    /**
     * Get RichMembers with specified STATUS.
     * @param status ACTIVE or EXPIRED values are accepted
     * @return List of RichMembers, empty list if nothing has been found.
     */
    public List<RichMember> getRichMembersByStatus(String status) {
        return memberDAO.getRichMembersByStatus(status);
    }

    /**
     * Get RichMembers with specified value for sponsorship.
     * @param isSponsored TRUE if RichMember is sponsored, FALSE otherwise
     * @return List of RichMembers, empty list if nothing has been found.
     */
    public List<RichMember> getRichMembersBySponsored(boolean isSponsored) {
        return memberDAO.getRichMembersBySponsored(isSponsored);
    }

    /**
     * Get RichMembers that have specified attributes. (Exact matching used)
     * @param attrs attributes of RichMembers
     * @return List of RichMembers found, empty list if nothing has been found.
     */
    public List<RichMember> getRichMembersHavingAttrs(List<InputAttribute> attrs) {
        return memberDAO.getRichMembersHavingAttrs(attrs);
    }

    /* MEMBER_ATTRS */

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
     * Get all Resources.
     * @return List of Resources, empty list if nothing has been found.
     */
    public List<Resource> getResources() {
        return resourceDAO.getResources();
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

    /**
     * Get Resources that have specified attributes.
     * (EXACT matching used)
     * @param attrs attributes of Resources
     * @return List of Resources, empty list if nothing has been found.
     */
    public List<Resource> getResourcesHavingAttrs(List<InputAttribute> attrs) {
        return resourceDAO.getResourcesHavingAttrs(attrs);
    }

    /* RICH_RESOURCE */
    
    /**
     * Get RichResource specified by ID.
     * @param id id of RichResource
     * @return Found RichResource or null if not such found.
     * @throws DatabaseIntegrityException More than one RichResource with same ID found.
     */
    public RichResource getRichResource(Long id) throws DatabaseIntegrityException {
        return resourceDAO.getRichResource(id);
    }

    /**
     * Get all RichResources.
     * @return List of RichResources, empty list if nothing has been found.
     */
    public List<RichResource> getRichResources() {
        return resourceDAO.getRichResources();
    }

    /**
     * Get RichResource with NAME like specified parameter.
     * (Operator LIKE used, comparing ignores case)
     * @param name name of RichResource
     * @return List of RichResources, empty list if nothing has been found.
     */
    public List<RichResource> getRichResourcesByName(String name) {
        return resourceDAO.getRichResourcesByName(name);
    }

    /**
     * Get RichResources of Facility specified by ID.
     * @param facilityId id of Facility
     * @return List of RichResources, empty list if nothing has been found.
     */
    public List<RichResource> getRichResourcesOfFacility(Long facilityId) {
        return resourceDAO.getRichResourcesOfFacility(facilityId);
    }

    /**
     * Get RichResources of Vo specified by ID.
     * @param voId id of Vo
     * @return List of RichResources, empty list if nothing has been found.
     */
    public List<RichResource> getRichResourcesOfVo(Long voId) {
        return resourceDAO.getRichResourcesOfVo(voId);
    }

    /**
     * Get RichResources that have specified attributes.
     * (EXACT matching used)
     * @param attrs attributes of RichResources
     * @return List of RichResources, empty list if nothing has been found.
     */
    public List<RichResource> getRichResourcesHavingAttrs(List<InputAttribute> attrs) {
        return resourceDAO.getRichResourcesHavingAttrs(attrs);
    }

    /* RESOURCE_ATTRS */

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
     * Get all Services.
     * @return List of Services, empty list if nothing has been found.
     */
    public List<Service> getServices() {
        return serviceDAO.getServices();
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
     * Get resources of Owner specified by ID.
     * @param ownerId id of Owner
     * @return List of Services, empty list if nothing has been found.
     */
    public List<Service> getServicesOfOwner(Long ownerId) {
        return serviceDAO.getServicesOfOwner(ownerId);
    }

    /**
     * Get Services that have specified attributes.
     * (EXACT matching used)
     * @param attrs attributes of Services
     * @return List of Services, empty list if nothing has been found.
     */
    public List<Service> getServicesHavingAttrs(List<InputAttribute> attrs) {
        return serviceDAO.getServicesHavingAttrs(attrs);
    }

    /* RICH_SERVICE */

    /**
     * Get RichService specified by ID.
     * @param id id of RichService
     * @return Found RichService or null if not such found.
     * @throws DatabaseIntegrityException When more than one RichService with same id found.
     */
    public RichService getRichService(Long id) throws DatabaseIntegrityException {
        return serviceDAO.getRichService(id);
    }

    /**
     * Get all RichServices.
     * @return List of RichServices, empty list if nothing has been found.
     */
    public List<RichService> getRichServices() {
        return serviceDAO.getRichServices();
    }

    /**
     * Get RichServices with NAME like specified parameter.
     * @param name name of RichService
     * @return List of RichServices, empty list if nothing has been found.
     */
    public List<RichService> getRichServicesByName(String name) {
        return serviceDAO.getRichServicesByName(name);
    }

    /**
     * Get resources of Owner specified by ID.
     * @param ownerId id of Owner
     * @return List of RichServices, empty list if nothing has been found.
     */
    public List<RichService> getRichServicesOfOwner(Long ownerId) {
        return serviceDAO.getRichServicesOfOwner(ownerId);
    }

    /**
     * Get RichServices that have specified attributes.
     * (EXACT matching used)
     * @param attrs attributes of RichServices
     * @return List of RichServices, empty list if nothing has been found.
     */
    public List<RichService> getRichServicesHavingAttrs(List<InputAttribute> attrs) {
        return serviceDAO.getRichServicesHavingAttrs(attrs);
    }

    /* SERVICE_ATTRS */

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

    /**
     * Get users that have specified attributes. (Exact matching used)
     * @param attrs attributes of users
     * @return List of users found, empty list if nothing has been found.
     */
    public List<User> getUsersHavingAttrs(List<InputAttribute> attrs) {
        return userDAO.getUsersHavingAttrs(attrs);
    }

    /* RICH_USER */

    /**
     * Get user specified by ID.
     * @param id id of user
     * @return Found user or null if not such found.
     */
    public RichUser getRichUser(Long id) throws DatabaseIntegrityException {
        return userDAO.getRichUser(id);
    }

    /**
     * Get all users.
     * @return List of users, empty list if nothing has been found.
     */
    public List<RichUser> getRichUsers() {
        return userDAO.getRichUsers();
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
    public List<RichUser> getRichUsersByName(String titleBefore, String firstName, String middleName,
                                     String lastName, String titleAfter) {
        return userDAO.getRichUsersByName(titleBefore, firstName, middleName, lastName, titleAfter);
    }

    /**
     * Get users by specifying if their acc is serviceAcc.
     * @param isServiceAcc TRUE for serviceAccounts, FALSE otherwise.
     * @return List of users found, empty list if nothing has been found.
     */
    public List<RichUser> getRichUsersByServiceAcc(boolean isServiceAcc) {
        return userDAO.getRichUsersByServiceAcc(isServiceAcc);
    }

    /**
     * Get users by specifying if their acc is sponsored.
     * @param isSponsoredAcc TRUE for sponsoredAccounts, FALSE otherwise.
     * @return List of users found, empty list if nothing has been found.
     */
    public List<RichUser> getRichUsersBySponsoredAcc(boolean isSponsoredAcc) {
        return userDAO.getRichUsersBySponsoredAcc(isSponsoredAcc);
    }

    /**
     * Get users that have specified attributes. (Exact matching used)
     * @param attrs attributes of users
     * @return List of users found, empty list if nothing has been found.
     */
    public List<RichUser> getRichUsersHavingAttrs(List<InputAttribute> attrs) {
        return userDAO.getRichUsersHavingAttrs(attrs);
    }

    /* USER_ATTRS */

    /**
     * Get attributes of user specified by ID.
     * @param id id of user
     * @param attrs attributes to be fetched
     * @return List of attributes, empty list if nothing has been found.
     */
    public List<Attribute> getUserAttrs(Long id, List<String> attrs) throws DatabaseIntegrityException {
        return userDAO.getUserAttrs(id, attrs);
    }

    /* USER_EXT_SOURCE */
    
    /**
     * Get userExtSource specified by ID.
     * @param id id of userExtSource
     * @return Found userExtSource or null if not such found.
     */
    public UserExtSource getUserExtSource(Long id) throws DatabaseIntegrityException {
        return userExtSourceDAO.getUserExtSource(id);
    }

    /**
     * Get all userExtSources.
     * @return List of userExtSources, null if nothing has been found.
     */
    public List<UserExtSource> getUserExtSources() {
        return userExtSourceDAO.getUserExtSources();
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

    /**
     * Get userExtSources that have specified attributes. (Exact matching used)
     * @param attrs attributes of userExtSources
     * @return List of userExtSources found, empty list if nothing has been found.
     */
    public List<UserExtSource> getUserExtSourcesHavingAttrs(List<InputAttribute> attrs) {
        return userExtSourceDAO.getUserExtSourcesHavingAttrs(attrs);
    }

    /* RICH_USER_EXT_SOURCE */

    /**
     * Get userExtSource specified by ID.
     * @param id id of userExtSource
     * @return Found userExtSource or null if not such found.
     */
    public RichUserExtSource getRichUserExtSource(Long id) throws DatabaseIntegrityException {
        return userExtSourceDAO.getRichUserExtSource(id);
    }

    /**
     * Get all userExtSources.
     * @return List of userExtSources, null if nothing has been found.
     */
    public List<RichUserExtSource> getRichUserExtSources() {
        return userExtSourceDAO.getRichUserExtSources();
    }

    /**
     * Get userExtSources of user specified by ID.
     * @param userId id of user
     * @return List of userExtSources found, empty list if nothing has been found.
     */
    public List<RichUserExtSource> getRichUserExtSourcesOfUser(Long userId) {
        return userExtSourceDAO.getRichUserExtSourcesOfUser(userId);
    }

    /**
     * Get userExtSources of extSource specified by ID.
     * @param extSourceId id of extSource
     * @return List of userExtSources found, empty list if nothing has been found.
     */
    public List<RichUserExtSource> getRichUserExtSourcesOfExtSource(Long extSourceId) {
        return userExtSourceDAO.getRichUserExtSourcesOfExtSource(extSourceId);
    }

    /**
     * Get userExtSources with specified loginExt
     * @param loginExt loginExt of userExtSource
     * @return List of userExtSources found, empty list if nothing has been found.
     */
    public List<RichUserExtSource> getRichUserExtSourcesByLoginExt(String loginExt) {
        return userExtSourceDAO.getRichUserExtSourcesByLoginExt(loginExt);
    }

    /**
     * Get userExtSources that have specified attributes. (Exact matching used)
     * @param attrs attributes of userExtSources
     * @return List of userExtSources found, empty list if nothing has been found.
     */
    public List<RichUserExtSource> getRichUserExtSourcesHavingAttrs(List<InputAttribute> attrs) {
        return userExtSourceDAO.getRichUserExtSourcesHavingAttrs(attrs);
    }

    /* USER_EXT_SOURCE_ATTRS */

    /**
     * Get attributes of userExtSource specified by ID.
     * @param id id of userExtSource
     * @param attrs attributes to be fetched
     * @return List of attributes.
     */
    public List<Attribute> getUserExtSourceAttrs(Long id, List<String> attrs) throws DatabaseIntegrityException {
        return userExtSourceDAO.getUserExtSourceAttrs(id, attrs);
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
     * Get all vos.
     * @return List of vos, empty list if nothing has been found.
     */
    public List<Vo> getVos() {
        return voDAO.getVos();
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
     * Get vos that have specified attributes. (Exact matching used)
     * @param attrs attributes of vos
     * @return List of vos found, empty list if nothing has been found.
     */
    public List<Vo> getVosHavingAttrs(List<InputAttribute> attrs) {
        return voDAO.getVosHavingAttrs(attrs);
    }
    
    /* RICH_VO */

    /**
     * Get vo specified by ID.
     * @param id id of vo
     * @return Found vo or null if not such found.
     */
    public RichVo getRichVo(Long id) throws DatabaseIntegrityException {
        return voDAO.getRichVo(id);
    }

    /**
     * Get all vos.
     * @return List of vos, empty list if nothing has been found.
     */
    public List<RichVo> getRichVos() {
        return voDAO.getRichVos();
    }

    /**
     * Get vos with names like specified parameter. (LIKE operator used, comparing ignores case)
     * @param name substring in name of vo
     * @return List of vos, empty list if nothing has been found.
     */
    public List<RichVo> getRichVosByName(String name) {
        return voDAO.getRichVosByName(name);
    }

    /**
     * Get vo specified by SHORT NAME. (EXACT MATCH, comparing ignores case)
     * @param shortName short name of vo
     * @return Found vo or null if not such found.
     */
    public RichVo getRichVoByShortName(String shortName) throws DatabaseIntegrityException {
        return voDAO.getRichVoByShortName(shortName);
    }

    /**
     * Get vos with short names like specified parameter. (LIKE operator used, comparing ignores case)
     * @param shortName substring in short_name of vo
     * @return List of vos, empty list if nothing has been found.
     */
    public List<RichVo> getRichVosByShortName(String shortName) {
        return voDAO.getRichVosByShortName(shortName);
    }

    /**
     * Get vos that have specified attributes. (Exact matching used)
     * @param attrs attributes of vos
     * @return List of vos found, empty list if nothing has been found.
     */
    public List<RichVo> getRichVosHavingAttrs(List<InputAttribute> attrs) {
        return voDAO.getRichVosHavingAttrs(attrs);
    }

    /* VO_ATTRS */

    /**
     * Get attributes of vo specified by ID. Only attributes with value are returned.
     * @param id id of vo
     * @param attrs attributes to be fetched
     * @return List of attributes, empty list if nothing has been found.
     */
    public List<Attribute> getVoAttrs(Long id, List<String> attrs) throws DatabaseIntegrityException {
        return voDAO.getVoAttrs(id, attrs);
    }

}
