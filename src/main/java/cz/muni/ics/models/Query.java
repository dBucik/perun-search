package cz.muni.ics.models;

import com.coxautodev.graphql.tools.GraphQLRootResolver;
import cz.muni.ics.DAOs.*;
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

    public Query(ExtSourceDAO extSourceDAO, GroupDAO groupDAO, FacilityDAO facilityDAO,
                 HostDAO hostDAO, MemberDAO memberDAO,
                 OwnerDAO ownerDAO, ResourceDAO resourceDAO,
                 ServiceDAO serviceDAO, UserDAO userDAO,
                 UserExtSourceDAO userExtSourceDAO, VoDAO voDAO) {

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
    public List<ExtSource> getExtSources(List<InputAttribute> core) {
        return extSourceDAO.getExtSources(core);
    }

    public List<RichExtSource> getRichExtSources(List<InputAttribute> core, List<InputAttribute> attrs,
                                          List<String> attrsNames) {
        return extSourceDAO.getRichExtSources(core, attrs, attrsNames);
    }

    public List<RichExtSource> getCompleteRichExtSources(List<InputAttribute> core, List<InputAttribute> attrs) {
        return extSourceDAO.getCompleteRichExtSources(core, attrs);
    }

    /* FACILITY */

    public List<Facility> getFacilities(List<InputAttribute> core) {
        return facilityDAO.getFacilities(core);
    }

    public List<RichFacility> getRichFacilities(List<InputAttribute> core, List<InputAttribute> attrs,
                                                List<String> attrsNames) {
        return facilityDAO.getRichFacilities(core, attrs, attrsNames);
    }

    public List<RichFacility> getCompleteRichFacilities(List<InputAttribute> core, List<InputAttribute> attrs) {
        return facilityDAO.getCompleteRichFacilities(core, attrs);
    }

    /* GROUP */

    public List<Group> getGroups(List<InputAttribute> core) {
        return groupDAO.getGroups(core);
    }

    public List<RichGroup> getRichGroups(List<InputAttribute> core, List<InputAttribute> attrs,
                                         List<String> attrsNames) {
        return groupDAO.getRichGroups(core, attrs, attrsNames);
    }

    public List<RichGroup> getCompleteRichGroups(List<InputAttribute> core, List<InputAttribute> attrs) {
        return groupDAO.getCompleteRichGroups(core, attrs);
    }

    /* HOST */

    public List<Host> getHosts(List<InputAttribute> core) {
        return hostDAO.getHosts(core);
    }

    public List<RichHost> getRichHosts(List<InputAttribute> core, List<InputAttribute> attrs,
                                       List<String> attrsNames) {
        return hostDAO.getRichHosts(core, attrs, attrsNames);
    }

    public List<RichHost> getCompleteRichHosts(List<InputAttribute> core, List<InputAttribute> attrs) {
        return hostDAO.getCompleteRichHosts(core, attrs);
    }

    /* MEMBER */

    public List<Member> getMembers(List<InputAttribute> core) {
        return memberDAO.getMembers(core);
    }

    public List<RichMember> getRichMembers(List<InputAttribute> core, List<InputAttribute> attrs,
                                           List<String> attrsNames) {
        return memberDAO.getRichMembers(core, attrs, attrsNames);
    }

    public List<RichMember> getCompleteRichMembers(List<InputAttribute> core, List<InputAttribute> attrs) {
        return memberDAO.getCompleteRichMembers(core, attrs);
    }

    /* OWNER */

    public List<Owner> getOwners(List<InputAttribute> core) {
        return ownerDAO.getOwners(core);
    }

    /* RESOURCE */

    public List<Resource> getResources(List<InputAttribute> core) {
        return resourceDAO.getResources(core);
    }

    public List<RichResource> getRichResources(List<InputAttribute> core, List<InputAttribute> attrs,
                                               List<String> attrsNames) {
        return resourceDAO.getRichResources(core, attrs, attrsNames);
    }

    public List<RichResource> getCompleteRichResources(List<InputAttribute> core, List<InputAttribute> attrs) {
        return resourceDAO.getCompleteRichResources(core, attrs);
    }

    /* SERVICE */

    public List<Service> getServices(List<InputAttribute> core) {
        return serviceDAO.getServices(core);
    }

    public List<RichService> getRichServices(List<InputAttribute> core, List<InputAttribute> attrs,
                                             List<String> attrsNames) {
        return serviceDAO.getRichServices(core, attrs, attrsNames);
    }

    public List<RichService> getCompleteRichServices(List<InputAttribute> core, List<InputAttribute> attrs) {
        return serviceDAO.getCompleteRichServices(core, attrs);
    }

    /* USER */

    public List<User> getUsers(List<InputAttribute> core) {
        return userDAO.getUsers(core);
    }

    public List<RichUser> getRichUsers(List<InputAttribute> core, List<InputAttribute> attrs,
                                       List<String> attrsNames) {
        return userDAO.getRichUsers(core, attrs, attrsNames);
    }

    public List<RichUser> getCompleteRichUsers(List<InputAttribute> core, List<InputAttribute> attrs) {
        return userDAO.getCompleteRichUsers(core, attrs);
    }

    /* USER_EXT_SOURCE */

    public List<UserExtSource> getUserExtSources(List<InputAttribute> core) {
        return userExtSourceDAO.getUserExtSources(core);
    }

    public List<RichUserExtSource> getRichUserExtSources(List<InputAttribute> core, List<InputAttribute> attrs,
                                                         List<String> attrsNames) {
        return userExtSourceDAO.getRichUserExtSources(core, attrs, attrsNames);
    }

    public List<RichUserExtSource> getCompleteRichUserExtSources(List<InputAttribute> core, List<InputAttribute> attrs) {
        return userExtSourceDAO.getCompleteRichUserExtSources(core, attrs);
    }

    /* VO */

    public List<Vo> getVos(List<InputAttribute> core) {
        return voDAO.getVos(core);
    }

    public List<RichVo> getRichVos(List<InputAttribute> core, List<InputAttribute> attrs,
                                   List<String> attrsNames) {
        return voDAO.getRichVos(core, attrs, attrsNames);
    }

    public List<RichVo> getCompleteRichVos(List<InputAttribute> core, List<InputAttribute> attrs) {
        return voDAO.getCompleteRichVos(core, attrs);
    }

}
