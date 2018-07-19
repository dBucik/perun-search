package cz.muni.ics.mappers;

import cz.muni.ics.models.attributes.PerunAttribute;
import cz.muni.ics.models.entities.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MappersUtils {

    public static <T extends ExtSource> T mapExtSource(JSONObject json, T extSource) {
        extSource.setId(json.getLong("id"));

        if (!(json.get("name") instanceof String)) {
            extSource.setName(json.get("name").toString());
        } else {
            extSource.setName(json.getString("name"));
        }

        if (!(json.get("type") instanceof String)) {
            extSource.setType(json.get("type").toString());
        } else {
            extSource.setType(json.getString("type"));
        }

        return extSource;
    }

    public static <T extends Facility> T mapFacility(JSONObject json, T facility) {
        facility.setId(json.getLong("id"));

        if (!(json.get("name") instanceof String)) {
            facility.setName(json.get("name").toString());
        } else {
            facility.setName(json.getString("name"));
        }

        if (!(json.get("dsc") instanceof String)) {
            facility.setDescription(json.get("dsc").toString());
        } else {
            facility.setDescription(json.getString("dsc"));
        }

        return facility;
    }

    public static <T extends Group> T  mapGroup(JSONObject json, T group) {
        group.setId(json.getLong("id"));

        if (!(json.get("name") instanceof String)) {
            group.setName(null);
        } else {
            group.setName(json.getString("name"));
        }

        if (!(json.get("dsc") instanceof String)) {
            group.setDescription(null);
        } else {
            group.setDescription(json.getString("dsc"));
        }

        if (!(json.get("vo_id") instanceof Long)) {
            group.setVoId(null);
        } else {
            group.setVoId(json.getLong("vo_id"));
        }

        if (!(json.get("parent_group_id") instanceof Long)) {
            group.setParentGroupId(null);
        } else {
            group.setParentGroupId(json.getLong("parent_group_id"));
        }

        return group;
    }

    public static <T extends Host> T  mapHost(JSONObject json, T host) {

        host.setId(json.getLong("id"));

        if (!(json.get("hostname") instanceof String)) {
            host.setHostname(json.get("hostname").toString());
        } else {
            host.setHostname(json.getString("hostname"));
        }

        if (!(json.get("dsc") instanceof String)) {
            host.setDescription(json.get("dsc").toString());
        } else {
            host.setDescription(json.getString("dsc"));
        }

        if (!(json.get("facility_id") instanceof Long)) {
            host.setFacilityId(null);
        } else {
            host.setFacilityId(json.getLong("facility_id"));
        }

        return host;
    }

    public static <T extends Member> T  mapMember(JSONObject json, T member) {
        member.setId(json.getLong("id"));

        if (!(json.get("status") instanceof String)) {
            member.setStatus(json.get("status").toString());
        } else {
            if (json.get("status").equals('0')) {
                member.setStatus("EXPIRED");
            } else {
                member.setStatus("ACTIVE");
            }
        }

        if (!(json.get("vo_id") instanceof Long)) {
            member.setVoId(null);
        } else {
            member.setVoId(json.getLong("vo_id"));
        }

        if (!(json.get("user_id") instanceof Long)) {
            member.setUserId(null);
        } else {
            member.setUserId(json.getLong("user_id"));
        }

        if (json.getString("sponsored").equals("f")) {
            member.setSponsored(false);
        } else {
            member.setSponsored(true);
        }

        return member;
    }

    public static Owner mapOwner(JSONObject json, Owner owner) {
        owner.setId(json.getLong("id"));
        owner.setName(json.getString("name"));
        owner.setContact(json.getString("contact"));
        owner.setStatus(json.getString("status"));
        owner.setType(json.getString("type"));

        return owner;
    }

    public static <T extends Resource> T  mapResource(JSONObject json, T resource) {
        resource.setId(json.getLong("id"));

        if (!(json.get("name") instanceof String)) {
            resource.setName(json.get("name").toString());
        } else {
            resource.setName(json.getString("name"));
        }

        if (!(json.get("dsc") instanceof String)) {
            resource.setDescription(json.get("dsc").toString());
        } else {
            resource.setDescription(json.getString("dsc"));
        }

        if (!(json.get("facility_id") instanceof Long)) {
            resource.setFacilityId(null);
        } else {
            resource.setFacilityId(json.getLong("facility_id"));
        }

        if (!(json.get("vo_id") instanceof Long)) {
            resource.setVoId(null);
        } else {
            resource.setVoId(json.getLong("vo_id"));
        }

        return resource;
    }

    public static <T extends Service> T  mapService(JSONObject json, T service) {
        service.setId(json.getLong("id"));

        if (!(json.get("name") instanceof String)) {
            service.setName(json.get("name").toString());
        } else {
            service.setName(json.getString("name"));
        }

        if (!(json.get("owner_id") instanceof Long)) {
            service.setOwnerId(null);
        } else {
            service.setOwnerId(json.getLong("owner_id"));
        }

        return service;
    }

    public static <T extends UserExtSource> T  mapUserExtSource(JSONObject json, T ues) {
        ues.setId(json.getLong("id"));

        if (!(json.get("login_ext") instanceof String)) {
            ues.setLoginExt(json.get("login_ext").toString());
        } else {
            ues.setLoginExt(json.getString("login_ext"));
        }

        if (!(json.get("user_id") instanceof Long)) {
            ues.setUserId(null);
        } else {
            ues.setUserId(json.getLong("user_id"));
        }

        if (!(json.get("ext_sources_id") instanceof Long)) {
            ues.setExtSourcesId(null);
        } else {
            ues.setExtSourcesId(json.getLong("ext_sources_id"));
        }

        if (!(json.get("loa") instanceof Short)) {
            ues.setLoa(null);
        } else {
            ues.setLoa(json.getNumber("loa").shortValue());
        }

        return ues;
    }

    public static <T extends User> T  mapUser(JSONObject json, T user) {
        user.setId(json.getLong("id"));

        if (!(json.get("first_name") instanceof String)) {
            user.setFirstName(json.get("first_name").toString());
        } else {
            user.setFirstName(json.getString("first_name"));
        }

        if (!(json.get("last_name") instanceof String)) {
            user.setLastName(json.get("last_name").toString());
        } else {
            user.setLastName(json.getString("last_name"));
        }

        if (!(json.get("middle_name") instanceof String)) {
            user.setMiddleName(json.get("middle_name").toString());
        } else {
            user.setMiddleName(json.getString("middle_name"));
        }

        if (!(json.get("title_before") instanceof String)) {
            user.setTitleBefore(json.get("title_before").toString());
        } else {
            user.setTitleBefore(json.getString("title_before"));
        }

        if (!(json.get("title_after") instanceof String)) {
            user.setTitleAfter(json.get("title_after").toString());
        } else {
            user.setTitleAfter(json.getString("title_after"));
        }

        if (json.getString("service_acc").equals("0")) {
            user.setServiceAcc(false);
        } else {
            user.setServiceAcc(true);
        }

        if (json.getString("sponsored_acc").equals("0")) {
            user.setSponsoredAcc(false);
        } else {
            user.setSponsoredAcc(true);
        }

        return user;
    }

    public static <T extends Vo> T  mapVo(JSONObject json, T vo) {
        vo.setId(json.getLong("id"));

        if (!(json.get("name") instanceof String)) {
            vo.setName(json.get("name").toString());
        } else {
            vo.setName(json.getString("name"));
        }

        if (!(json.get("short_name") instanceof String)) {
            vo.setShortName(json.get("short_name").toString());
        } else {
            vo.setShortName(json.getString("short_name"));
        }

        return vo;
    }

    public static List<PerunAttribute> getAttributes(JSONObject json) {
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

        return attrs;
    }

}
