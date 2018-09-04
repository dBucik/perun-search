package cz.muni.ics.models;


import cz.muni.ics.DAOs.PerunDAO;
import cz.muni.ics.DAOs.RelationsDAO;
import cz.muni.ics.models.entities.ExtSource;
import cz.muni.ics.models.entities.Facility;
import cz.muni.ics.models.entities.Group;
import cz.muni.ics.models.entities.Host;
import cz.muni.ics.models.entities.Member;
import cz.muni.ics.models.entities.Owner;
import cz.muni.ics.models.entities.Resource;
import cz.muni.ics.models.entities.Service;
import cz.muni.ics.models.entities.User;
import cz.muni.ics.models.entities.UserExtSource;
import cz.muni.ics.models.entities.Vo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Query object for GraphQL schema. Contains all available queries.
 *
 * @author Dominik Frantisek Bucik <bucik@ics.muni.cz>
 */
@SuppressWarnings("unused")
public class Query {

    private static final Logger log = LoggerFactory.getLogger(Query.class);

    private final PerunDAO perunDAO;
    private final RelationsDAO relationsDAO;

    public Query(PerunDAO perunDAO, RelationsDAO relationsDAO) {
        this.perunDAO = perunDAO;
        this.relationsDAO = relationsDAO;
    }

    public List<ExtSource> getExtSources(List<String> attrsNames, List<InputAttribute> attributes) {
        log.info("getExtSources (attrsNames: {}, attributes: {})", attrsNames, attributes);
        return perunDAO.getExtSources(attrsNames, attributes);
    }

    public List<Facility> getFacilities(List<String> attrsNames, List<InputAttribute> attributes) {
        log.info("getFacilities (attrsNames: {}, attributes: {})", attrsNames, attributes);
        return perunDAO.getFacilities(attrsNames, attributes);
    }

    public List<Group> getGroups(List<String> attrsNames, List<InputAttribute> attributes) {
        log.info("getGroups (attrsNames: {}, attributes: {})", attrsNames, attributes);
        return perunDAO.getGroups(attrsNames, attributes);
    }

    public List<Host> getHosts(List<String> attrsNames, List<InputAttribute> attributes) {
        log.info("getHosts (attrsNames: {}, attributes: {})", attrsNames, attributes);
        return perunDAO.getHosts(attrsNames, attributes);
    }

    public List<Member> getMembers(List<String> attrsNames, List<InputAttribute> attributes) {
        log.info("getMembers (attrsNames: {}, attributes: {})", attrsNames, attributes);
        return perunDAO.getMembers(attrsNames, attributes);
    }

    public List<Owner> getOwners(List<String> attrsNames, List<InputAttribute> attributes) {
        log.info("getOwners (attrsNames: {}, attributes: {})", attrsNames, attributes);
        return perunDAO.getOwners(attrsNames, attributes);
    }

    public List<Resource> getResources(List<String> attrsNames, List<InputAttribute> attributes) {
        log.info("getResources (attrsNames: {}, attributes: {})", attrsNames, attributes);
        return perunDAO.getResources(attrsNames, attributes);
    }

    public List<Service> getServices(List<String> attrsNames, List<InputAttribute> attributes) {
        log.info("getServices (attrsNames: {}, attributes: {})", attrsNames, attributes);
        return perunDAO.getServices(attrsNames, attributes);
    }

    public List<User> getUsers(List<String> attrsNames, List<InputAttribute> attributes) {
        log.info("getUsers (attrsNames: {}, attributes: {})", attrsNames, attributes);
        return perunDAO.getUsers(attrsNames, attributes);
    }

    public List<UserExtSource> getUserExtSources(List<String> attrsNames, List<InputAttribute> attributes) {
        log.info("getUserExtSources (attrsNames: {}, attributes: {})", attrsNames, attributes);
        return perunDAO.getUserExtSources(attrsNames, attributes);
    }

    public List<Vo> getVos(List<String> attrsNames, List<InputAttribute> attributes) {
        log.info("getExtSources (attrsNames: {}, attributes: {})", attrsNames, attributes);
        return perunDAO.getVos(attrsNames, attributes);
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
