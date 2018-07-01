package cz.muni.ics.models;

import com.coxautodev.graphql.tools.GraphQLRootResolver;
import cz.muni.ics.JdbcTemplates.*;

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

    public Destination getDestination(Long id) { return destinationJdbcTemplate.getDestination(id); }

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

    public List<Member> getMembers() { return memberJdbcTemplate.getMembers();}

    public Resource getResource(Long id) { return resourceJdbcTemplate.getResource(id); }

    public List<Resource> getAllResources() { return resourceJdbcTemplate.getResources(); }

    public Service getService(Long id) { return serviceJdbcTemplate.getService(id); }

    public List<Service> getAllServices() { return serviceJdbcTemplate.getServices(); }

    public User getUser(Long id) { return userJdbcTemplate.getUser(id); }

    public List<User> getAllUsers() { return userJdbcTemplate.getUsers(); }

    public UserExtSource getUserExtSource(Long id) { return userExtSourceJdbcTemplate.getUserExtSource(id); }

    public List<UserExtSource> getAllUserExtSources() { return userExtSourceJdbcTemplate.getUserExtSources(); }

    public Vo getVo(Long id) { return voJdbcTemplate.getVo(id); }

    public List<Vo> getAllVos() {
        return voJdbcTemplate.getVos();
    }
}
