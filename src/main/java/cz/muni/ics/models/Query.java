package cz.muni.ics.models;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import cz.muni.ics.DAOs.*;
import cz.muni.ics.models.entities.*;
import cz.muni.ics.models.richEntities.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Query object for GraphQL schema. Contains all available queries.
 *
 * @author Dominik Frantisek Bucik <bucik@ics.muni.cz>
 */
@SuppressWarnings("unused")
public class Query implements GraphQLQueryResolver {

    private static final Logger log = LoggerFactory.getLogger(Query.class);

    private final ExtSourceDAO extSourceDAO;
    private final FacilityDAO facilityDAO;
    private final GroupDAO groupDAO;
    private final HostDAO hostDAO;
    private final MemberDAO memberDAO;
    private final OwnerDAO ownerDAO;
    private final ResourceDAO resourceDAO;
    private final RelationsDAO relationsDAO;
    private final ServiceDAO serviceDAO;
    private final UserDAO userDAO;
    private final UserExtSourceDAO userExtSourceDAO;
    private final VoDAO voDAO;

    public Query(ExtSourceDAO extSourceDAO, GroupDAO groupDAO, FacilityDAO facilityDAO,
                 HostDAO hostDAO, MemberDAO memberDAO,
                 OwnerDAO ownerDAO, ResourceDAO resourceDAO, RelationsDAO relationsDAO,
                 ServiceDAO serviceDAO, UserDAO userDAO,
                 UserExtSourceDAO userExtSourceDAO, VoDAO voDAO) {

        this.extSourceDAO = extSourceDAO;
        this.facilityDAO = facilityDAO;
        this.groupDAO = groupDAO;
        this.hostDAO = hostDAO;
        this.memberDAO = memberDAO;
        this.ownerDAO = ownerDAO;
        this.relationsDAO = relationsDAO;
        this.resourceDAO = resourceDAO;
        this.serviceDAO = serviceDAO;
        this.userDAO = userDAO;
        this.userExtSourceDAO = userExtSourceDAO;
        this.voDAO = voDAO;
    }

    /* EXT_SOURCE */
    public List<ExtSource> getExtSources(List<InputAttribute> core) {
        log.info("getExtSources (core: {})", core);
        return extSourceDAO.getExtSources(core);
    }

    public List<RichExtSource> getRichExtSources(List<InputAttribute> core, List<InputAttribute> attrs,
                                          List<String> attrsNames) {
        log.info("getRichExtSources (core: {}, attrs: {}, attrsNames: {})", core, attrs, attrsNames);
        return extSourceDAO.getRichExtSources(core, attrs, attrsNames);
    }

    public List<RichExtSource> getCompleteRichExtSources(List<InputAttribute> core, List<InputAttribute> attrs) {
        log.info("getCompleteRichExtSources (core: {}, attrs: {})");
        return extSourceDAO.getCompleteRichExtSources(core, attrs);
    }

    /* FACILITY */

    public List<Facility> getFacilities(List<InputAttribute> core) {
        log.info("getFacilities (core: {})", core);
        return facilityDAO.getFacilities(core);
    }

    public List<RichFacility> getRichFacilities(List<InputAttribute> core, List<InputAttribute> attrs,
                                                List<String> attrsNames) {
        log.info("getRichFacilities (core: {}, attrs: {}, attrsNames: {})", core, attrs, attrsNames);
        return facilityDAO.getRichFacilities(core, attrs, attrsNames);
    }

    public List<RichFacility> getCompleteRichFacilities(List<InputAttribute> core, List<InputAttribute> attrs) {
        log.info("getCompleteRichFacilities (core: {}, attrs: {})", core, attrs);
        return facilityDAO.getCompleteRichFacilities(core, attrs);
    }

    /* GROUP */

    public List<Group> getGroups(List<InputAttribute> core) {
        log.info("getGroups (core: {})", core);
        return groupDAO.getGroups(core);
    }

    public List<RichGroup> getRichGroups(List<InputAttribute> core, List<InputAttribute> attrs,
                                         List<String> attrsNames) {
        log.info("getRichGroups (core: {}, attrs: {}, attrsNames: {})", core, attrs, attrsNames);
        return groupDAO.getRichGroups(core, attrs, attrsNames);
    }

    public List<RichGroup> getCompleteRichGroups(List<InputAttribute> core, List<InputAttribute> attrs) {
        log.info("getCompleteRichGroups (core: {}, attrs: {})", core, attrs);
        return groupDAO.getCompleteRichGroups(core, attrs);
    }

    /* HOST */

    public List<Host> getHosts(List<InputAttribute> core) {
        log.info("getHosts (core: {})", core);
        return hostDAO.getHosts(core);
    }

    public List<RichHost> getRichHosts(List<InputAttribute> core, List<InputAttribute> attrs,
                                       List<String> attrsNames) {
        log.info("getRichHosts (core: {}, attrs: {}, attrsNames: {})", core, attrs, attrsNames);
        return hostDAO.getRichHosts(core, attrs, attrsNames);
    }

    public List<RichHost> getCompleteRichHosts(List<InputAttribute> core, List<InputAttribute> attrs) {
        log.info("getCompleteRichHosts (core: {}, attrs: {})", core, attrs);
        return hostDAO.getCompleteRichHosts(core, attrs);
    }

    /* MEMBER */

    public List<Member> getMembers(List<InputAttribute> core) {
        log.info("getMembers (core: {})", core);
        return memberDAO.getMembers(core);
    }

    public List<RichMember> getRichMembers(List<InputAttribute> core, List<InputAttribute> attrs,
                                           List<String> attrsNames) {
        log.info("getRichMembers (core: {}, attrs: {}, attrsNames: {})", core, attrs, attrsNames);
        return memberDAO.getRichMembers(core, attrs, attrsNames);
    }

    public List<RichMember> getCompleteRichMembers(List<InputAttribute> core, List<InputAttribute> attrs) {
        log.info("getCompleteRichMembers (core: {}, attrs: {})", core, attrs);
        return memberDAO.getCompleteRichMembers(core, attrs);
    }

    /* OWNER */

    public List<Owner> getOwners(List<InputAttribute> core) {
        log.info("getOwners (core: {})", core);
        return ownerDAO.getOwners(core);
    }

    /* RESOURCE */

    public List<Resource> getResources(List<InputAttribute> core) {
        log.info("getResources (core: {})", core);
        return resourceDAO.getResources(core);
    }

    public List<RichResource> getRichResources(List<InputAttribute> core, List<InputAttribute> attrs,
                                               List<String> attrsNames) {
        log.info("getRichResources (core: {}, attrs: {}, attrsNames: {})", core, attrs, attrsNames);
        return resourceDAO.getRichResources(core, attrs, attrsNames);
    }

    public List<RichResource> getCompleteRichResources(List<InputAttribute> core, List<InputAttribute> attrs) {
        log.info("getCompleteRichResources (core: {}, attrs: {})", core, attrs);
        return resourceDAO.getCompleteRichResources(core, attrs);
    }

    /* SERVICE */

    public List<Service> getServices(List<InputAttribute> core) {
        log.info("getServices (core: {})", core);
        return serviceDAO.getServices(core);
    }

    public List<RichService> getRichServices(List<InputAttribute> core, List<InputAttribute> attrs,
                                             List<String> attrsNames) {
        log.info("getRichServices (core: {}, attrs: {}, attrsNames: {})", core, attrs, attrsNames);
        return serviceDAO.getRichServices(core, attrs, attrsNames);
    }

    public List<RichService> getCompleteRichServices(List<InputAttribute> core, List<InputAttribute> attrs) {
        log.info("getCompleteRichServices (core: {}, attrs: {})", core, attrs);
        return serviceDAO.getCompleteRichServices(core, attrs);
    }

    /* USER */

    public List<User> getUsers(List<InputAttribute> core) {
        log.info("getUsers (core: {})", core);
        return userDAO.getUsers(core);
    }

    public List<RichUser> getRichUsers(List<InputAttribute> core, List<InputAttribute> attrs,
                                       List<String> attrsNames) {
        log.info("getRichUsers (core: {}, attrs: {}, attrsNames: {})", core, attrs, attrsNames);
        return userDAO.getRichUsers(core, attrs, attrsNames);
    }

    public List<RichUser> getCompleteRichUsers(List<InputAttribute> core, List<InputAttribute> attrs) {
        log.info("getCompleteRichUsers (core: {}, attrs: {})", core, attrs);
        return userDAO.getCompleteRichUsers(core, attrs);
    }

    /* USER_EXT_SOURCE */

    public List<UserExtSource> getUserExtSources(List<InputAttribute> core) {
        log.info("getUserExtSources (core: {})", core);
        return userExtSourceDAO.getUserExtSources(core);
    }

    public List<RichUserExtSource> getRichUserExtSources(List<InputAttribute> core, List<InputAttribute> attrs,
                                                         List<String> attrsNames) {
        log.info("getRichUserExtSources (core: {}, attrs: {}, attrsNames: {})", core, attrs, attrsNames);
        return userExtSourceDAO.getRichUserExtSources(core, attrs, attrsNames);
    }

    public List<RichUserExtSource> getCompleteRichUserExtSources(List<InputAttribute> core, List<InputAttribute> attrs) {
        log.info("getCompleteRichUserExtSources (core: {}, attrs: {})", core, attrs);
        return userExtSourceDAO.getCompleteRichUserExtSources(core, attrs);
    }

    /* VO */

    public List<Vo> getVos(List<InputAttribute> core) {
        log.info("getVos (core: {})", core);
        return voDAO.getVos(core);
    }

    public List<RichVo> getRichVos(List<InputAttribute> core, List<InputAttribute> attrs,
                                   List<String> attrsNames) {
        log.info("getRichVos (core: {}, attrs: {}, attrsNames: {})", core, attrs, attrsNames);
        return voDAO.getRichVos(core, attrs, attrsNames);
    }

    public List<RichVo> getCompleteRichVos(List<InputAttribute> core, List<InputAttribute> attrs) {
        log.info("getCompleteRichVos (core: {}, attrs: {})", core, attrs);
        return voDAO.getCompleteRichVos(core, attrs);
    }

    /* RELATIONS */

    public List<Relation> getRelations(String type, Long primary, Long secondary) {
        log.info("getRelations (type: {}, primary: {}, secondary: {})", type, primary, secondary);
        return relationsDAO.getRelations(type, primary, secondary);
    }

    public List<Relation> getRichRelations(String type, Long primary, Long secondary,
                                           List<String> attrsNames, List<InputAttribute> attrs) {
        log.info("getRichRelations (type: {}, primary: {}, secondary: {}, attrsNames: {}, attrs: {})",
                type, primary, secondary, attrsNames, attrs);
        return relationsDAO.getRichRelations(type, primary, secondary, attrsNames, attrs);
    }

    public List<Relation> getCompleteRichRelations(String type, Long primary, Long secondary,
                                                   List<InputAttribute> attrs) {
        log.info("getCompleteRichRelations (type: {}, primary: {}, secondary: {}, attrs: {})",
                type, primary, secondary, attrs);
        return relationsDAO.getCompleteRichRelations(type, primary, secondary, attrs);
    }

}
