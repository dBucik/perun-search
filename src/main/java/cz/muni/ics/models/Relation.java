package cz.muni.ics.models;

import cz.muni.ics.models.attributes.PerunAttribute;
import cz.muni.ics.models.entities.ExtSource;
import cz.muni.ics.models.entities.Facility;
import cz.muni.ics.models.entities.Group;
import cz.muni.ics.models.entities.Member;
import cz.muni.ics.models.entities.PerunEntity;
import cz.muni.ics.models.entities.Resource;
import cz.muni.ics.models.entities.Service;
import cz.muni.ics.models.entities.User;
import cz.muni.ics.models.entities.Vo;
import cz.muni.ics.models.richEntities.RichExtSource;
import cz.muni.ics.models.richEntities.RichFacility;
import cz.muni.ics.models.richEntities.RichGroup;
import cz.muni.ics.models.richEntities.RichMember;
import cz.muni.ics.models.richEntities.RichResource;
import cz.muni.ics.models.richEntities.RichService;
import cz.muni.ics.models.richEntities.RichUser;
import cz.muni.ics.models.richEntities.RichVo;

import java.security.acl.Owner;
import java.util.List;

public class Relation {

    private Long primaryEntityId;
    private Long secondaryEntity;
    private RelationType type;
    private List<PerunAttribute> attributes;

    public Long getPrimaryEntityId() {
        return primaryEntityId;
    }

    public void setPrimaryEntityId(Long primaryEntityId) {
        this.primaryEntityId = primaryEntityId;
    }

    public Long getSecondaryEntity() {
        return secondaryEntity;
    }

    public void setSecondaryEntity(Long secondaryEntity) {
        this.secondaryEntity = secondaryEntity;
    }

    public RelationType getType() {
        return type;
    }

    public void setType(RelationType type) {
        this.type = type;
    }

    public List<PerunAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<PerunAttribute> attributes) {
        this.attributes = attributes;
    }

    public static RelationType resolveType(String type) {
        switch (type.toUpperCase()) {
            case "VO_EXT_SOURCE":
                return RelationType.VO_EXT_SOURCE;
            case "GROUP_EXT_SOURCE":
                return RelationType.GROUP_EXT_SOURCE;
            case "USER_FACILITY":
                return RelationType.USER_FACILITY;
            case "RESOURCE_SERVICE":
                return RelationType.RESOURCE_SERVICE;
            case "MEMBER_RESOURCE":
                return RelationType.MEMBER_RESOURCE;
            case "GROUP_RESOURCE":
                return RelationType.GROUP_RESOURCE;
            case "MEMBER_GROUP":
                return RelationType.MEMBER_GROUP;
            case "GROUP_GROUP":
                return RelationType.GROUP_GROUP;
            case "GROUP_MEMBER":
                return RelationType.GROUP_MEMBER;
            case "FACILITY_OWNER":
                return RelationType.FACILITY_OWNER;
            default:
                return null;
        }
    }

    public static String resolvePrimaryEntityKeyFromRelationType(RelationType type) {
        switch (type) {
            case VO_EXT_SOURCE:
                return "vo_id";
            case USER_FACILITY:
                return "user_id";
            case RESOURCE_SERVICE:
                return "resource_id";
            case MEMBER_GROUP:
            case MEMBER_RESOURCE:
                return "member_id";
            case GROUP_EXT_SOURCE:
            case GROUP_GROUP:
            case GROUP_MEMBER:
            case GROUP_RESOURCE:
                return "group_id";
            case FACILITY_OWNER:
                return "facility_id";
            default:
                return null;
        }
    }

    public static String resolveSecondaryEntityKeyFromRelationType(RelationType type) {
        switch (type) {
            case VO_EXT_SOURCE:
            case GROUP_EXT_SOURCE:
                return "ext_source_id";
            case USER_FACILITY:
                return "facility_id";
            case RESOURCE_SERVICE:
                return "service_id";
            case MEMBER_RESOURCE:
            case GROUP_RESOURCE:
                return "resource_id";
            case MEMBER_GROUP:
            case GROUP_GROUP:
                return "group_id";
            case GROUP_MEMBER:
                return "member_id";
            case FACILITY_OWNER:
                return "owner_id";
            default:
                return null;
        }
    }
}
