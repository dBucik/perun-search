package cz.muni.ics.mappers;

import cz.muni.ics.models.Relation;
import cz.muni.ics.models.attributes.PerunAttribute;
import cz.muni.ics.models.entities.*;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MappersUtils {

    private static final Logger log = LoggerFactory.getLogger(MappersUtils.class);

    public static <T extends ExtSource> T mapExtSource(JSONObject json, T extSource) {
        log.debug("Mapping ExtSource from json: {}", json);
        extSource.setId(json.getLong("id"));

        if (JSONObject.NULL.equals(json.get("name"))) {
            extSource.setName(null);
        } else {
            extSource.setName(json.getString("name"));
        }

        if (JSONObject.NULL.equals(json.get("type"))) {
            extSource.setType(null);
        } else {
            extSource.setType(json.getString("type"));
        }

        log.debug("Mapped ExtSource: {}", extSource);
        return extSource;
    }

    public static <T extends Facility> T mapFacility(JSONObject json, T facility) {
        log.debug("Mapping ExtSource from json: {}", json);

        facility.setId(json.getLong("id"));

        if (JSONObject.NULL.equals(json.get("name"))) {
            facility.setName(null);
        } else {
            facility.setName(json.getString("name"));
        }

        if (JSONObject.NULL.equals(json.get("dsc"))) {
            facility.setDescription(null);
        } else {
            facility.setDescription(json.getString("dsc"));
        }

        log.debug("Mapped ExtSource: {}", facility);
        return facility;
    }

    public static <T extends Group> T  mapGroup(JSONObject json, T group) {
        log.debug("Mapping Group from json: {}", json);

        group.setId(json.getLong("id"));

        if (JSONObject.NULL.equals(json.get("name"))) {
            group.setName(null);
        } else {
            group.setName(json.getString("name"));
        }

        if (JSONObject.NULL.equals(json.get("dsc"))) {
            group.setDescription(null);
        } else {
            group.setDescription(json.getString("dsc"));
        }

        if (JSONObject.NULL.equals(json.get("vo_id"))) {
            group.setVoId(null);
        } else {
            group.setVoId(json.getLong("vo_id"));
        }

        if (JSONObject.NULL.equals(json.get("parent_group_id"))) {
            group.setParentGroupId(null);
        } else {
            group.setParentGroupId(json.getLong("parent_group_id"));
        }

        log.debug("Mapped Group: {}", group);
        return group;
    }

    public static <T extends Host> T  mapHost(JSONObject json, T host) {
        log.debug("Mapping Host from json: {}", json);

        host.setId(json.getLong("id"));

        if (JSONObject.NULL.equals(json.get("hostname"))) {
            host.setHostname(null);
        } else {
            host.setHostname(json.getString("hostname"));
        }

        if (JSONObject.NULL.equals(json.get("dsc"))) {
            host.setDescription(null);
        } else {
            host.setDescription(json.getString("dsc"));
        }

        if (JSONObject.NULL.equals(json.get("facility_id"))) {
            host.setFacilityId(null);
        } else {
            host.setFacilityId(json.getLong("facility_id"));
        }

        log.debug("Mapped Host: {}", host);
        return host;
    }

    public static <T extends Member> T  mapMember(JSONObject json, T member) {
        log.debug("Mapping Member from json: {}", json);

        member.setId(json.getLong("id"));

        if (JSONObject.NULL.equals(json.get("status"))) {
            member.setStatus(null);
        } else {
            if (json.get("status").equals('0')) {
                member.setStatus("EXPIRED");
            } else {
                member.setStatus("ACTIVE");
            }
        }

        if (JSONObject.NULL.equals(json.get("vo_id"))) {
            member.setVoId(null);
        } else {
            member.setVoId(json.getLong("vo_id"));
        }

        if (JSONObject.NULL.equals(json.get("user_id"))) {
            member.setUserId(null);
        } else {
            member.setUserId(json.getLong("user_id"));
        }

        if (JSONObject.NULL.equals(json.getString("sponsored")) || json.getString("sponsored").equals("f")) {
            member.setSponsored(false);
        } else {
            member.setSponsored(true);
        }

        log.debug("Mapped Member: {}", member);
        return member;
    }

    public static Owner mapOwner(JSONObject json, Owner owner) {
        log.debug("Mapping Owner from json: {}", json);

        owner.setId(json.getLong("id"));
        owner.setName(json.getString("name"));
        owner.setContact(json.getString("contact"));
        owner.setStatus(json.getString("status"));
        owner.setType(json.getString("type"));

        log.debug("Mapped Owner: {}", owner);
        return owner;
    }

    public static <T extends Resource> T  mapResource(JSONObject json, T resource) {
        log.debug("Mapping Resource from json: {}", json);

        resource.setId(json.getLong("id"));

        if (JSONObject.NULL.equals(json.get("name"))) {
            resource.setName(null);
        } else {
            resource.setName(json.getString("name"));
        }

        if (JSONObject.NULL.equals(json.get("dsc"))) {
            resource.setDescription(null);
        } else {
            resource.setDescription(json.getString("dsc"));
        }

        if (JSONObject.NULL.equals(json.get("facility_id"))) {
            resource.setFacilityId(null);
        } else {
            resource.setFacilityId(json.getLong("facility_id"));
        }

        if (JSONObject.NULL.equals(json.get("vo_id"))) {
            resource.setVoId(null);
        } else {
            resource.setVoId(json.getLong("vo_id"));
        }

        log.debug("Mapped Resource: {}", resource);
        return resource;
    }

    public static <T extends Service> T  mapService(JSONObject json, T service) {
        log.debug("Mapping Service from json: {}", json);

        service.setId(json.getLong("id"));

        if (JSONObject.NULL.equals(json.get("name"))) {
            service.setName(null);
        } else {
            service.setName(json.getString("name"));
        }

        if (JSONObject.NULL.equals(json.get("owner_id"))) {
            service.setOwnerId(null);
        } else {
            service.setOwnerId(json.getLong("owner_id"));
        }

        log.debug("Mapped Service: {}", service);
        return service;
    }

    public static <T extends UserExtSource> T  mapUserExtSource(JSONObject json, T ues) {
        log.debug("Mapping UserExtSource from json: {}", json);

        ues.setId(json.getLong("id"));

        if (JSONObject.NULL.equals(json.get("login_ext"))) {
            ues.setLoginExt(null);
        } else {
            ues.setLoginExt(json.getString("login_ext"));
        }

        if (JSONObject.NULL.equals(json.get("user_id"))) {
            ues.setUserId(null);
        } else {
            ues.setUserId(json.getLong("user_id"));
        }

        if (JSONObject.NULL.equals(json.get("ext_sources_id"))) {
            ues.setExtSourcesId(null);
        } else {
            ues.setExtSourcesId(json.getLong("ext_sources_id"));
        }

        if (JSONObject.NULL.equals(json.get("loa"))) {
            ues.setLoa(null);
        } else {
            ues.setLoa(json.getNumber("loa").shortValue());
        }

        log.debug("Mapped UserExtSource: {}", ues);
        return ues;
    }

    public static <T extends User> T  mapUser(JSONObject json, T user) {
        log.debug("Mapping User from json: {}", json);

        user.setId(json.getLong("id"));

        if (JSONObject.NULL.equals(json.get("first_name"))) {
            user.setFirstName(null);
        } else {
            user.setFirstName(json.getString("first_name"));
        }

        if (JSONObject.NULL.equals(json.get("last_name"))) {
            user.setLastName(null);
        } else {
            user.setLastName(json.getString("last_name"));
        }

        if (JSONObject.NULL.equals(json.get("middle_name"))) {
            user.setMiddleName(null);
        } else {
            user.setMiddleName(json.getString("middle_name"));
        }

        if (JSONObject.NULL.equals(json.get("title_before"))) {
            user.setTitleBefore(null);
        } else {
            user.setTitleBefore(json.getString("title_before"));
        }

        if (JSONObject.NULL.equals(json.get("title_after"))) {
            user.setTitleAfter(null);
        } else {
            user.setTitleAfter(json.getString("title_after"));
        }

        if (JSONObject.NULL.equals(json.getString("service_acc")) || json.getString("service_acc").equals("0")) {
            user.setServiceAcc(false);
        } else {
            user.setServiceAcc(true);
        }

        if (JSONObject.NULL.equals(json.getString("sponsored_acc")) || json.getString("sponsored_acc").equals("0")) {
            user.setSponsoredAcc(false);
        } else {
            user.setSponsoredAcc(true);
        }

        log.debug("Mapped User: {}", user);
        return user;
    }

    public static <T extends Vo> T  mapVo(JSONObject json, T vo) {
        log.debug("Mapping Vo from json: {}", json);

        vo.setId(json.getLong("id"));

        if (JSONObject.NULL.equals(json.get("name"))) {
            vo.setName(null);
        } else {
            vo.setName(json.getString("name"));
        }

        if (JSONObject.NULL.equals(json.get("short_name"))) {
            vo.setShortName(null);
        } else {
            vo.setShortName(json.getString("short_name"));
        }

        log.debug("Mapped Vo: {}", vo);
        return vo;
    }

    public static Relation mapRelation(ResultSet rs, Relation relation) throws SQLException {
        log.debug("Mapping Relation from resultSet: {}", rs);

        relation.setType(rs.getString("rel_type"));
        String priEntityKey = Relation.resolvePrimaryEntityKeyFromRelationType(relation.getTrueType());
        String secEntityKey = Relation.resolveSecondaryEntityKeyFromRelationType(relation.getTrueType());

        relation.setPrimaryEntityId(rs.getLong(priEntityKey));
        relation.setSecondaryEntityId(rs.getLong(secEntityKey));

        log.debug("Mapped Relation: {}", relation);
        return relation;
    }

    public static List<PerunAttribute> getAttributes(JSONObject json) {
        log.debug("Get attributes from json: {}", json);
        List<PerunAttribute> attrs = new ArrayList<>();

        for (String key: json.keySet()) {
            JSONObject attrJson = json.getJSONObject(key);
            String type = attrJson.getString("type");
            Object value = attrJson.get("value");
            Object valueText = attrJson.get("value_text");
            
            PerunAttribute attr;
            if (value != null && !value.equals(JSONObject.NULL)) {
                attr = PerunAttribute.parse(key, type, value);
            } else {
                attr = PerunAttribute.parse(key, type, valueText);
            }
            
            if (attr != null) {
                attrs.add(attr);
            }
        }

        log.debug("Mapped attributes: {}", attrs);
        return attrs;
    }

}
