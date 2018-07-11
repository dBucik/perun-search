package cz.muni.ics.models;

import com.coxautodev.graphql.tools.GraphQLRootResolver;
import cz.muni.ics.DAOs.*;
import cz.muni.ics.exceptions.DatabaseIntegrityException;
import cz.muni.ics.models.attributes.PerunAttribute;
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
    private final ExtSourceDAO extSourceDAO;
    private final FacilityDAO facilityDAO;
    private final GroupDAO groupDAO;
    private final HostDAO hostDAO;
    private final MemberDAO memberDAO;
    private final OwnerDAO ownerDAO;
    private final ResourceDAO resourceDAO;
    private final ServiceDAO serviceDAO;
    private final UserDAO userDAO;
    private final UserExtSourceDAO userExtSourceDAO;
    private final VoDAO voDAO;

    public Query(ExtSourceDAO extSourceDAO,
                 FacilityDAO facilityDAO, GroupDAO groupDAO,
                 HostDAO hostDAO, MemberDAO memberDAO, OwnerDAO ownerDAO,
                 ResourceDAO resourceDAO, ServiceDAO serviceDAO,
                 UserDAO userDAO, UserExtSourceDAO userExtSourceDAO,
                 VoDAO voDAO) {
        this.extSourceDAO = extSourceDAO;
        this.facilityDAO = facilityDAO;
        this.groupDAO = groupDAO;
        this.hostDAO = hostDAO;
        this.memberDAO = memberDAO;
        this.ownerDAO = ownerDAO;
        this.resourceDAO = resourceDAO;
        this.serviceDAO = serviceDAO;
        this.userDAO = userDAO;
        this.userExtSourceDAO = userExtSourceDAO;
        this.voDAO = voDAO;
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
    public RichExtSource getCompleteRichExtSource(Long id) throws DatabaseIntegrityException {
        return extSourceDAO.getCompleteRichExtSource(id);
    }

    /**
     * Get all RichExtSources.
     * @return List of RichExtSources, empty list if nothing has been found.
     */
    public List<RichExtSource> getCompleteRichExtSources() {
        return extSourceDAO.getCompleteRichExtSources();
    }

    /**
     * Get RichExtSources with NAME like specified parameter.
     * (LIKE operator used, comparing ignores case)
     * @param name name of RichExtSource
     * @return List of RichExtSources, empty list if nothing has been found.
     */
    public List<RichExtSource> getCompleteRichExtSourcesByName(String name) {
        return extSourceDAO.getCompleteRichExtSourcesByName(name);
    }

    /**
     * Get RichExtSources with TYPE like specified parameter.
     * (LIKE operator used, comparing ignores case)
     * @param type type of RichExtSource
     * @return List of RichExtSources, empty list if nothing has been found.
     */
    public List<RichExtSource> getCompleteRichExtSourcesByType(String type) {
        return extSourceDAO.getCompleteRichExtSourcesByType(type);
    }

    /**
     * Get RichExtSources that have specified attributes.
     * (EXACT matching used)
     * @param attrs attributes of RichExtSources
     * @return List of RichExtSources, empty list if nothing has been found.
     */
    public List<RichExtSource> getCompleteRichExtSourcesHavingAttrs(List<InputAttribute> attrs) {
        return extSourceDAO.getCompleteRichExtSourcesHavingAttrs(attrs);
    }

    /* EXT_SOURCE_ATTRS */

    /**
     * Get attributes of ExtSource specified by ID.
     * @param id id of ExtSource
     * @param attrs attributes to be fetched
     * @return List of attributes.
     * @throws DatabaseIntegrityException More than one ExtSource with same ID found.
     */
    public List<PerunAttribute> getExtSourceAttrs(Long id, List<String> attrs) throws DatabaseIntegrityException {
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
    public RichFacility getCompleteRichFacility(Long id) throws DatabaseIntegrityException {
        return facilityDAO.getCompleteRichFacility(id);
    }

    /**
     * Get all RichFacilities.
     * @return List of RichFacilities, empty list if nothing has been found.
     */
    public List<RichFacility> getCompleteRichFacilities() {
        return facilityDAO.getCompleteRichFacilities();
    }

    /**
     * Get RichFacilities that have specified attributes.
     * (EXACT matching used)
     * @param attrs attributes of RichFacilities
     * @return List of RichFacilities found, empty list if nothing has been found.
     */
    public List<RichFacility> getCompleteRichFacilitiesHavingAttrs(List<InputAttribute> attrs) {
        return facilityDAO.getCompleteRichFacilitiesHavingAttrs(attrs);
    }

    /**
     * Get RichFacilities with NAME like specified parameter.
     * (LIKE operator used, comparing ignores case)
     * @param name name of RichFacility
     * @return List of RichFacilities, empty list if nothing has been found.
     */
    public List<RichFacility> getCompleteRichFacilitiesByName(String name) {
        return facilityDAO.getCompleteRichFacilitiesByName(name);
    }

    /* FACILITY_ATTRS */

    /**
     * Get attributes of Facility specified by ID.
     * @param id id of Facility
     * @param attrs attributes to be fetched
     * @return List of attributes.
     * * @throws DatabaseIntegrityException More than one Facility with same ID found.
     */
    public List<PerunAttribute> getFacilityAttrs(Long id, List<String> attrs) throws DatabaseIntegrityException {
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
    public RichGroup getCompleteRichGroup(Long id) throws DatabaseIntegrityException {
        return groupDAO.getCompleteRichGroup(id);
    }

    /**
     * Get all RichGroups.
     * @return List of RichGroups, empty list if nothing has been found.
     */
    public List<RichGroup> getCompleteRichGroups() {
        return groupDAO.getCompleteRichGroups();
    }

    /**
     * Get RichGroups withe NAME like specified parameter.
     * (LIKE operator used, comparing ignores case)
     * @param name name of RichGroup
     * @return List of RichGroups, empty list if nothing has been found.
     */
    public List<RichGroup> getCompleteRichGroupsByName(String name) {
        return groupDAO.getCompleteRichGroupsByName(name);
    }

    /**
     * Get parent group of RichGroup specified by ID.
     * @param childRichGroupId id of RichGroup whose parent has to be found
     * @return Parent RichGroup.
     * @throws DatabaseIntegrityException More than one RichGroup with same ID found.
     *                                    No parent group found for RichGroup with specified ID.
     */
    public RichGroup getParentRichGroup(Long childRichGroupId) throws DatabaseIntegrityException {
        return groupDAO.getCompleteParentRichGroup(childRichGroupId);
    }

    /**
     * Get all RichGroups of VO specified by ID.
     * @param voId id of VO
     * @return List of RichGroups, empty list if nothing has been found.
     */
    public List<RichGroup> getCompleteRichGroupsOfVo(Long voId) {
        return groupDAO.getCompleteRichGroupsOfVo(voId);
    }

    /**
     * Get RichGroups that have specified attributes.
     * (EXACT matching used)
     * @param attrs attributes of RichGroups
     * @return List of RichGroups found, empty list if nothing has been found.
     */
    public List<RichGroup> getCompleteRichGroupsHavingAttrs(List<InputAttribute> attrs) {
        return groupDAO.getCompleteRichGroupsHavingAttrs(attrs);
    }

    /* GROUP_ATTRS */

    /**
     * Get attributes of Group specified by ID.
     * @param id id of Group
     * @param attrs attributes to be fetched
     * @return List of attributes.
     * @throws DatabaseIntegrityException More than one Group with same ID found.
     */
    public List<PerunAttribute> getGroupAttrs(Long id, List<String> attrs) throws DatabaseIntegrityException {
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
    public RichHost getCompleteRichHost(Long id) throws DatabaseIntegrityException {
        return hostDAO.getCompleteRichHost(id);
    }

    /**
     * Get all RichHosts.
     * @return List of RichHosts, empty list if nothing has been found.
     */
    public List<RichHost> getCompleteRichHosts() {
        return hostDAO.getCompleteRichHosts();
    }

    /**
     * Get RichHosts with HOSTNAME like specified parameter.
     * (LIKE operator used, comparing ignores case)
     * @param hostname RichHostname of RichHost
     * @return List of RichHosts, empty list if nothing has been found.
     */
    public List<RichHost> getCompleteRichHostsByHostname(String hostname) {
        return hostDAO.getCompleteRichHostsByHostname(hostname);
    }

    /**
     * Get RichHosts that have specified attributes.
     * (EXACT matching used)
     * @param attrs attributes of RichHosts
     * @return List of RichHosts, empty list if nothing has been found.
     */
    public List<RichHost> getCompleteRichHostsHavingAttrs(List<InputAttribute> attrs) {
        return hostDAO.getCompleteRichHostsHavingAttrs(attrs);
    }

    /* HOST_ATTRS */

    /**
     * Get attributes of Host specified by ID.
     * @param id id of Host
     * @param attrs attributes to be fetched
     * @return List of attributes.
     * @throws DatabaseIntegrityException More than one Host with same ID found.
     */
    public List<PerunAttribute> getHostAttrs(Long id, List<String> attrs) throws DatabaseIntegrityException {
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
    public RichMember getCompleteRichMember(Long id) throws DatabaseIntegrityException {
        return memberDAO.getCompleteRichMember(id);
    }

    /**
     * Get all RichMembers.
     * @return List of RichMembers, null if nothing has been found.
     */
    public List<RichMember> getCompleteRichMembers() {
        return memberDAO.getCompleteRichMembers();
    }

    /**
     * Get RichMembers of user specified by ID.
     * @param userId id of user
     * @return List of RichMembers, empty list if nothing has been found.
     */
    public List<RichMember> getCompleteRichMembersOfUser(Long userId) {
        return memberDAO.getCompleteRichMembersOfUser(userId);
    }

    /**
     * Get RichMembers of vo specified by ID.
     * @param voId id of vo.
     * @return List of RichMembers, empty list if nothing has been found.
     */
    public List<RichMember> getCompleteRichMembersOfVo(Long voId) {
        return memberDAO.getCompleteRichMembersOfVo(voId);
    }

    /**
     * Get RichMembers with specified STATUS.
     * @param status ACTIVE or EXPIRED values are accepted
     * @return List of RichMembers, empty list if nothing has been found.
     */
    public List<RichMember> getCompleteRichMembersByStatus(String status) {
        return memberDAO.getCompleteRichMembersByStatus(status);
    }

    /**
     * Get RichMembers with specified value for sponsorship.
     * @param isSponsored TRUE if RichMember is sponsored, FALSE otherwise
     * @return List of RichMembers, empty list if nothing has been found.
     */
    public List<RichMember> getCompleteRichMembersBySponsored(boolean isSponsored) {
        return memberDAO.getCompleteRichMembersBySponsored(isSponsored);
    }

    /**
     * Get RichMembers that have specified attributes. (Exact matching used)
     * @param attrs attributes of RichMembers
     * @return List of RichMembers found, empty list if nothing has been found.
     */
    public List<RichMember> getCompleteRichMembersHavingAttrs(List<InputAttribute> attrs) {
        return memberDAO.getCompleteRichMembersHavingAttrs(attrs);
    }

    /* MEMBER_ATTRS */

    /**
     * Get attributes of Member specified by ID.
     * @param id id of Member
     * @param attrs attributes to be fetched
     * @return List of attributes
     * @throws DatabaseIntegrityException More than one Member with same ID found.
     */
    public List<PerunAttribute> getMemberAttrs(Long id, List<String> attrs) throws DatabaseIntegrityException {
        return memberDAO.getMemberAttrs(id, attrs);
    }

    /* OWNER */

    /**
     * Get Owner specified by ID.
     * @param id id of Owner
     * @return Found Owner or null if not such found.
     * @throws DatabaseIntegrityException More than one Owner with same ID found.
     */
    public Owner getOwner(Long id) throws DatabaseIntegrityException {
        return ownerDAO.getOwner(id);
    }

    /**
     * Get Owner with NAME like specified parameter.
     * (Operator LIKE used, comparing ignores case)
     * @param name name of Owner
     * @return List of Owners, empty list if nothing has been found.
     */
    public List<Owner> getOwnersByName(String name) {
        return ownerDAO.getOwnersByName(name);
    }

    /**
     * Get Owner with TYPE like specified parameter.
     * (Operator LIKE used, comparing ignores case)
     * @param type type of Owner
     * @return List of Owners, empty list if nothing has been found.
     */
    public List<Owner> getOwnersByType(String type) {
        return ownerDAO.getOwnersByType(type);
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
    public RichResource getCompleteRichResource(Long id) throws DatabaseIntegrityException {
        return resourceDAO.getCompleteRichResource(id);
    }

    /**
     * Get all RichResources.
     * @return List of RichResources, empty list if nothing has been found.
     */
    public List<RichResource> getCompleteRichResources() {
        return resourceDAO.getCompleteRichResources();
    }

    /**
     * Get RichResource with NAME like specified parameter.
     * (Operator LIKE used, comparing ignores case)
     * @param name name of RichResource
     * @return List of RichResources, empty list if nothing has been found.
     */
    public List<RichResource> getCompleteRichResourcesByName(String name) {
        return resourceDAO.getCompleteRichResourcesByName(name);
    }

    /**
     * Get RichResources of Facility specified by ID.
     * @param facilityId id of Facility
     * @return List of RichResources, empty list if nothing has been found.
     */
    public List<RichResource> getCompleteRichResourcesOfFacility(Long facilityId) {
        return resourceDAO.getCompleteRichResourcesOfFacility(facilityId);
    }

    /**
     * Get RichResources of Vo specified by ID.
     * @param voId id of Vo
     * @return List of RichResources, empty list if nothing has been found.
     */
    public List<RichResource> getCompleteRichResourcesOfVo(Long voId) {
        return resourceDAO.getCompleteRichResourcesOfVo(voId);
    }

    /**
     * Get RichResources that have specified attributes.
     * (EXACT matching used)
     * @param attrs attributes of RichResources
     * @return List of RichResources, empty list if nothing has been found.
     */
    public List<RichResource> getCompleteRichResourcesHavingAttrs(List<InputAttribute> attrs) {
        return resourceDAO.getCompleteRichResourcesHavingAttrs(attrs);
    }

    /* RESOURCE_ATTRS */

    /**
     * Get attributes of Resource specified by ID.
     * @param id id of Resource
     * @param attrs attributes to be fetched
     * @return List of attributes.
     * @throws DatabaseIntegrityException More than one Resource with same ID found.
     */
    public List<PerunAttribute> getResourceAttrs(Long id, List<String> attrs) throws DatabaseIntegrityException {
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
    public RichService getCompleteRichService(Long id) throws DatabaseIntegrityException {
        return serviceDAO.getCompleteRichService(id);
    }

    /**
     * Get all RichServices.
     * @return List of RichServices, empty list if nothing has been found.
     */
    public List<RichService> getCompleteRichServices() {
        return serviceDAO.getCompleteRichServices();
    }

    /**
     * Get RichServices with NAME like specified parameter.
     * @param name name of RichService
     * @return List of RichServices, empty list if nothing has been found.
     */
    public List<RichService> getCompleteRichServicesByName(String name) {
        return serviceDAO.getCompleteRichServicesByName(name);
    }

    /**
     * Get resources of Owner specified by ID.
     * @param ownerId id of Owner
     * @return List of RichServices, empty list if nothing has been found.
     */
    public List<RichService> getCompleteRichServicesOfOwner(Long ownerId) {
        return serviceDAO.getCompleteRichServicesOfOwner(ownerId);
    }

    /**
     * Get RichServices that have specified attributes.
     * (EXACT matching used)
     * @param attrs attributes of RichServices
     * @return List of RichServices, empty list if nothing has been found.
     */
    public List<RichService> getCompleteRichServicesHavingAttrs(List<InputAttribute> attrs) {
        return serviceDAO.getCompleteRichServicesHavingAttrs(attrs);
    }

    /* SERVICE_ATTRS */

    /**
     * Get attributes of Service specified by ID.
     * @param id id of Service
     * @param attrs attributes to be fetched
     * @return List of attributes
     * @throws DatabaseIntegrityException When more than one Resource with same id found.
     */
    public List<PerunAttribute> getServiceAttrs(Long id, List<String> attrs) throws DatabaseIntegrityException {
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
    public RichUser getCompleteRichUser(Long id) throws DatabaseIntegrityException {
        return userDAO.getCompleteRichUser(id);
    }

    /**
     * Get all users.
     * @return List of users, empty list if nothing has been found.
     */
    public List<RichUser> getCompleteRichUsers() {
        return userDAO.getCompleteRichUsers();
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
    public List<RichUser> getCompleteRichUsersByName(String titleBefore, String firstName, String middleName,
                                     String lastName, String titleAfter) {
        return userDAO.getCompleteRichUsersByName(titleBefore, firstName, middleName, lastName, titleAfter);
    }

    /**
     * Get users by specifying if their acc is serviceAcc.
     * @param isServiceAcc TRUE for serviceAccounts, FALSE otherwise.
     * @return List of users found, empty list if nothing has been found.
     */
    public List<RichUser> getCompleteRichUsersByServiceAcc(boolean isServiceAcc) {
        return userDAO.getCompleteRichUsersByServiceAcc(isServiceAcc);
    }

    /**
     * Get users by specifying if their acc is sponsored.
     * @param isSponsoredAcc TRUE for sponsoredAccounts, FALSE otherwise.
     * @return List of users found, empty list if nothing has been found.
     */
    public List<RichUser> getCompleteRichUsersBySponsoredAcc(boolean isSponsoredAcc) {
        return userDAO.getCompleteRichUsersBySponsoredAcc(isSponsoredAcc);
    }

    /**
     * Get users that have specified attributes. (Exact matching used)
     * @param attrs attributes of users
     * @return List of users found, empty list if nothing has been found.
     */
    public List<RichUser> getCompleteRichUsersHavingAttrs(List<InputAttribute> attrs) {
        return userDAO.getCompleteRichUsersHavingAttrs(attrs);
    }

    /* USER_ATTRS */

    /**
     * Get attributes of user specified by ID.
     * @param id id of user
     * @param attrs attributes to be fetched
     * @return List of attributes, empty list if nothing has been found.
     */
    public List<PerunAttribute> getUserAttrs(Long id, List<String> attrs) throws DatabaseIntegrityException {
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
    public RichUserExtSource getCompleteRichUserExtSource(Long id) throws DatabaseIntegrityException {
        return userExtSourceDAO.getCompleteRichUserExtSource(id);
    }

    /**
     * Get all userExtSources.
     * @return List of userExtSources, null if nothing has been found.
     */
    public List<RichUserExtSource> getCompleteRichUserExtSources() {
        return userExtSourceDAO.getCompleteRichUserExtSources();
    }

    /**
     * Get userExtSources of user specified by ID.
     * @param userId id of user
     * @return List of userExtSources found, empty list if nothing has been found.
     */
    public List<RichUserExtSource> getCompleteRichUserExtSourcesOfUser(Long userId) {
        return userExtSourceDAO.getCompleteRichUserExtSourcesOfUser(userId);
    }

    /**
     * Get userExtSources of extSource specified by ID.
     * @param extSourceId id of extSource
     * @return List of userExtSources found, empty list if nothing has been found.
     */
    public List<RichUserExtSource> getCompleteRichUserExtSourcesOfExtSource(Long extSourceId) {
        return userExtSourceDAO.getCompleteRichUserExtSourcesOfExtSource(extSourceId);
    }

    /**
     * Get userExtSources with specified loginExt
     * @param loginExt loginExt of userExtSource
     * @return List of userExtSources found, empty list if nothing has been found.
     */
    public List<RichUserExtSource> getCompleteRichUserExtSourcesByLoginExt(String loginExt) {
        return userExtSourceDAO.getCompleteRichUserExtSourcesByLoginExt(loginExt);
    }

    /**
     * Get userExtSources that have specified attributes. (Exact matching used)
     * @param attrs attributes of userExtSources
     * @return List of userExtSources found, empty list if nothing has been found.
     */
    public List<RichUserExtSource> getCompleteRichUserExtSourcesHavingAttrs(List<InputAttribute> attrs) {
        return userExtSourceDAO.getCompleteRichUserExtSourcesHavingAttrs(attrs);
    }

    /* USER_EXT_SOURCE_ATTRS */

    /**
     * Get attributes of userExtSource specified by ID.
     * @param id id of userExtSource
     * @param attrs attributes to be fetched
     * @return List of attributes.
     */
    public List<PerunAttribute> getUserExtSourceAttrs(Long id, List<String> attrs) throws DatabaseIntegrityException {
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
    public RichVo getCompleteRichVo(Long id) throws DatabaseIntegrityException {
        return voDAO.getCompleteRichVo(id);
    }

    /**
     * Get all vos.
     * @return List of vos, empty list if nothing has been found.
     */
    public List<RichVo> getCompleteRichVos() {
        return voDAO.getCompleteRichVos();
    }

    /**
     * Get vos with names like specified parameter. (LIKE operator used, comparing ignores case)
     * @param name substring in name of vo
     * @return List of vos, empty list if nothing has been found.
     */
    public List<RichVo> getCompleteRichVosByName(String name) {
        return voDAO.getCompleteRichVosByName(name);
    }

    /**
     * Get vo specified by SHORT NAME. (EXACT MATCH, comparing ignores case)
     * @param shortName short name of vo
     * @return Found vo or null if not such found.
     */
    public RichVo getCompleteRichVoByShortName(String shortName) throws DatabaseIntegrityException {
        return voDAO.getCompleteRichVoByShortName(shortName);
    }

    /**
     * Get vos with short names like specified parameter. (LIKE operator used, comparing ignores case)
     * @param shortName substring in short_name of vo
     * @return List of vos, empty list if nothing has been found.
     */
    public List<RichVo> getCompleteRichVosByShortName(String shortName) {
        return voDAO.getCompleteRichVosByShortName(shortName);
    }

    /**
     * Get vos that have specified attributes. (Exact matching used)
     * @param attrs attributes of vos
     * @return List of vos found, empty list if nothing has been found.
     */
    public List<RichVo> getCompleteRichVosHavingAttrs(List<InputAttribute> attrs) {
        return voDAO.getCompleteRichVosHavingAttrs(attrs);
    }

    /* VO_ATTRS */

    /**
     * Get attributes of vo specified by ID. Only attributes with value are returned.
     * @param id id of vo
     * @param attrs attributes to be fetched
     * @return List of attributes, empty list if nothing has been found.
     */
    public List<PerunAttribute> getVoAttrs(Long id, List<String> attrs) throws DatabaseIntegrityException {
        return voDAO.getVoAttrs(id, attrs);
    }

}
