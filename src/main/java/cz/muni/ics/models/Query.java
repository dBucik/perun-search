package cz.muni.ics.models;

import com.coxautodev.graphql.tools.GraphQLRootResolver;
import cz.muni.ics.JdbcTemplates.*;
import cz.muni.ics.exceptions.DatabaseIntegrityException;

import java.util.List;

public class Query implements GraphQLRootResolver {

    private final DestinationJdbcTemplate destinationJdbcTemplate;
    private final ExtSourceJdbcTemplate extSourceJdbcTemplate;
    private final FacilityJdbcTemplate facilityJdbcTemplate;
    private final GroupJdbcTemplate groupJdbcTemplate;
    private final HostJdbcTemplate hostJdbcTemplate;
    private final MemberJdbcTemplate memberJdbcTemplate;
    private final ResourceJdbcTemplate resourceJdbcTemplate;
    private final ServiceJdbcTemplate serviceJdbcTemplate;
    private final UserJdbcTemplate userJdbcTemplate;
    private final UserExtSourceJdbcTemplate userExtSourceJdbcTemplate;
    private final VoJdbcTemplate voJdbcTemplate;

    public Query(DestinationJdbcTemplate destinationJdbcTemplate, ExtSourceJdbcTemplate extSourceJdbcTemplate,
                 FacilityJdbcTemplate facilityJdbcTemplate, GroupJdbcTemplate groupJdbcTemplate,
                 HostJdbcTemplate hostJdbcTemplate, MemberJdbcTemplate memberJdbcTemplate,
                 ResourceJdbcTemplate resourceJdbcTemplate, ServiceJdbcTemplate serviceJdbcTemplate,
                 UserJdbcTemplate userJdbcTemplate, UserExtSourceJdbcTemplate userExtSourceJdbcTemplate,
                 VoJdbcTemplate voJdbcTemplate) {
        this.destinationJdbcTemplate = destinationJdbcTemplate;
        this.extSourceJdbcTemplate = extSourceJdbcTemplate;
        this.facilityJdbcTemplate = facilityJdbcTemplate;
        this.groupJdbcTemplate = groupJdbcTemplate;
        this.hostJdbcTemplate = hostJdbcTemplate;
        this.memberJdbcTemplate = memberJdbcTemplate;
        this.resourceJdbcTemplate = resourceJdbcTemplate;
        this.serviceJdbcTemplate = serviceJdbcTemplate;
        this.userJdbcTemplate = userJdbcTemplate;
        this.userExtSourceJdbcTemplate = userExtSourceJdbcTemplate;
        this.voJdbcTemplate = voJdbcTemplate;
    }

    public Destination getDestination(Long id) throws DatabaseIntegrityException { return destinationJdbcTemplate.getDestination(id); }

    public List<Destination> getAllDestinations() { return destinationJdbcTemplate.getDestinations(); }

    public ExtSource getExtSource(Long id) { return extSourceJdbcTemplate.getExtSource(id); }

    public List<ExtSource> getAllExtSources() { return extSourceJdbcTemplate.getExtSources(); }

    public Facility getFacility(Long id) { return facilityJdbcTemplate.getFacility(id); }

    public List<Facility> getAllFacilities() { return facilityJdbcTemplate.getFacilities(); }

    public Group getGroup(Long id) { return groupJdbcTemplate.getGroup(id); }

    public List<Group> getAllGroups() { return groupJdbcTemplate.getGroups(); }

    public Host getHost(Long id) { return hostJdbcTemplate.getHost(id); }

    public List<Host> getAllHosts() { return hostJdbcTemplate.getHosts(); }

    public Member getMember(Long id) { return memberJdbcTemplate.getMember(id); }

    public List<Member> getAllMembers() { return memberJdbcTemplate.getMembers();}

    public Resource getResource(Long id) { return resourceJdbcTemplate.getResource(id); }

    public List<Resource> getAllResources() { return resourceJdbcTemplate.getResources(); }

    public Service getService(Long id) { return serviceJdbcTemplate.getService(id); }

    public List<Service> getAllServices() { return serviceJdbcTemplate.getServices(); }

    /* USER */

    /**
     * Get user specified by ID.
     * @param id id of user
     * @return Found user or null if not such found.
     */
    public User getUser(Long id) throws DatabaseIntegrityException {
        return userJdbcTemplate.getUser(id);
    }

    /**
     * Get all users.
     * @return List of users, empty list if nothing has been found.
     */
    public List<User> getUsers() {
        return userJdbcTemplate.getUsers();
    }

    /**
     * Get attributes of user specified by ID.
     * @param id id of user
     * @param attrs attributes to be fetched
     * @return List of attributes, empty list if nothing has been found.
     */
    public List<Attribute> getUserAttrs(Long id, List<String> attrs) throws DatabaseIntegrityException {
        return userJdbcTemplate.getUserAttrs(id, attrs);
    }

    /**
     * Get users that have specified attributes. (Exact matching used)
     * @param attrs attributes of users
     * @return List of users found, empty list if nothing has been found.
     */
    public List<User> getUsersWithAttrs(List<InputAttribute> attrs) {
        return userJdbcTemplate.getUsersWithAttrs(attrs);
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
        return userJdbcTemplate.getUsersByName(titleBefore, firstName, middleName, lastName, titleAfter);
    }

    /**
     * Get users by specifying if their acc is serviceAcc.
     * @param isServiceAcc TRUE for serviceAccounts, FALSE otherwise.
     * @return List of users found, empty list if nothing has been found.
     */
    public List<User> getUsersByServiceAcc(boolean isServiceAcc) {
        return userJdbcTemplate.getUsersByServiceAcc(isServiceAcc);
    }

    /**
     * Get users by specifying if their acc is sponsored.
     * @param isSponsoredAcc TRUE for sponsoredAccounts, FALSE otherwise.
     * @return List of users found, empty list if nothing has been found.
     */
    public List<User> getUsersBySponsoredAcc(boolean isSponsoredAcc) {
        return userJdbcTemplate.getUsersBySponsoredAcc(isSponsoredAcc);
    }

    /* USER_EXT_SOURCE */

    public UserExtSource getUserExtSource(Long id) { return userExtSourceJdbcTemplate.getUserExtSource(id); }

    public List<UserExtSource> getAllUserExtSources() { return userExtSourceJdbcTemplate.getUserExtSources(); }


    /* VO */

    /**
     * Get vo specified by ID.
     * @param id id of vo
     * @return Found vo or null if not such found.
     */
    public Vo getVo(Long id) throws DatabaseIntegrityException {
        return voJdbcTemplate.getVo(id);
    }

    /**
     * Get vos with names like specified parameter. (LIKE operator used, comparing ignores case)
     * @param name substring in name of vo
     * @return List of vos, empty list if nothing has been found.
     */
    public List<Vo> getVosByName(String name) {
        return voJdbcTemplate.getVosByName(name);
    }

    /**
     * Get vo specified by SHORT NAME. (EXACT MATCH, comparing ignores case)
     * @param shortName short name of vo
     * @return Found vo or null if not such found.
     */
    public Vo getVoByShortName(String shortName) throws DatabaseIntegrityException {
        return voJdbcTemplate.getVoByShortName(shortName);
    }

    /**
     * Get vos with short names like specified parameter. (LIKE operator used, comparing ignores case)
     * @param shortName substring in short_name of vo
     * @return List of vos, empty list if nothing has been found.
     */
    public List<Vo> getVosByShortName(String shortName) {
        return voJdbcTemplate.getVosByShortName(shortName);
    }

    /**
     * Get all vos.
     * @return List of vos, empty list if nothing has been found.
     */
    public List<Vo> getVos() {
        return voJdbcTemplate.getVos();
    }

    /**
     * Get attributes of vo specified by ID. Only attributes with value are returned.
     * @param id id of vo
     * @param attrs attributes to be fetched
     * @return List of attributes, empty list if nothing has been found.
     */
    public List<Attribute> getVoAttrs(Long id, List<String> attrs) throws DatabaseIntegrityException {
        return voJdbcTemplate.getVoAttrs(id, attrs);
    }

    /**
     * Get vos that have specified attributes. (Exact matching used)
     * @param attrs attributes of vos
     * @return List of vos found, empty list if nothing has been found.
     */
    public List<Vo> getVosWithAttrs(List<InputAttribute> attrs) {
        return voJdbcTemplate.getVosWithAttrs(attrs);
    }
}
