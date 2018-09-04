package cz.muni.ics.models;

import cz.muni.ics.models.attributes.PerunAttribute;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Relation {

    private Long primaryEntityId;
    private Long secondaryEntityId;
    private String type;
    private RelationType trueType;
    private Map<String, PerunAttribute> attributes;

    public Long getPrimaryEntityId() {
        return primaryEntityId;
    }

    public void setPrimaryEntityId(Long primaryEntityId) {
        this.primaryEntityId = primaryEntityId;
    }

    public Long getSecondaryEntityId() {
        return secondaryEntityId;
    }

    public void setSecondaryEntityId(Long secondaryEntityId) {
        this.secondaryEntityId = secondaryEntityId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
        this.trueType = resolveType(type);
    }

    public RelationType getTrueType() {
        return trueType;
    }

    public void setTrueType(RelationType trueType) {
        this.trueType = trueType;
        this.type = trueType.toString();
    }

    public Map<String, PerunAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, PerunAttribute> attributes) {
        this.attributes = attributes;
    }

    public PerunAttribute getAttributeByKey(String key) {
        return attributes.get(key);
    }

    public List<PerunAttribute> getAttributesAsList() {
        return new ArrayList<>(attributes.values());
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
                return "group_id";
            case GROUP_MEMBER:
                return "member_id";
            case FACILITY_OWNER:
                return "owner_id";
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        return "Relation [" +
                "primaryEntityId=" + resolvePrimaryEntityKeyFromRelationType(trueType) + ": " + primaryEntityId +
				", secondaryEntityId=" + resolveSecondaryEntityKeyFromRelationType(trueType) + ": " + secondaryEntityId +
				", trueType: " + trueType +
				", type: " + type +
				"]{attributes: " + attributes + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (! (o instanceof Relation)) {
            return false;
        }

        Relation r = (Relation) o;
        return super.equals(o) &&
                Objects.equals(primaryEntityId, r.primaryEntityId) &&
                Objects.equals(secondaryEntityId, r.secondaryEntityId) &&
                Objects.equals(trueType, r.trueType) &&
                Objects.equals(type, r.type) &&
                Objects.equals(attributes, r.attributes);
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        if (primaryEntityId != null) hash *= 31 * primaryEntityId.hashCode();
        if (secondaryEntityId != null) hash *= 31 * secondaryEntityId.hashCode();
        if (type != null) hash *= 31 * type.hashCode();
        if (trueType != null) hash *= 31 * trueType.hashCode();
        if (attributes != null) hash *= 31 * attributes.hashCode();

        return hash;
    }
}
