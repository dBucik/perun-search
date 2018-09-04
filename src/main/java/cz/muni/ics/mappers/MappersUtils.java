package cz.muni.ics.mappers;

import cz.muni.ics.models.Relation;
import cz.muni.ics.models.attributes.PerunAttribute;
import cz.muni.ics.models.entities.*;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MappersUtils {

    private static final Logger log = LoggerFactory.getLogger(MappersUtils.class);

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

    public static final RowMapper<ExtSource> EXT_SOURCE_ROW_MAPPER = (resultSet, i) -> {
		JSONObject attrs = new JSONObject(resultSet.getString("attrs"));
		Map<String, PerunAttribute> attributes = getAttributes(attrs);

		return new ExtSource(attributes);
	};

    public static final RowMapper<Facility> FACILITY_ROW_MAPPER = (resultSet, i) -> {
        JSONObject attrs = new JSONObject(resultSet.getString("attrs"));
        Map<String, PerunAttribute> attributes = getAttributes(attrs);

        return new Facility(attributes);
    };

    public static final RowMapper<Group> GROUP_ROW_MAPPER = (resultSet, i) -> {
        JSONObject attrs = new JSONObject(resultSet.getString("attrs"));
        Map<String, PerunAttribute> attributes = getAttributes(attrs);

        return new Group(attributes);
    };

    public static final RowMapper<Host> HOST_ROW_MAPPER = (resultSet, i) -> {
        JSONObject attrs = new JSONObject(resultSet.getString("attrs"));
        Map<String, PerunAttribute> attributes = getAttributes(attrs);

        return new Host(attributes);
    };

    public static final RowMapper<Member> MEMBER_ROW_MAPPER = (resultSet, i) -> {
        JSONObject attrs = new JSONObject(resultSet.getString("attrs"));
        Map<String, PerunAttribute> attributes = getAttributes(attrs);

        return new Member(attributes);
    };

    public static final RowMapper<Owner> OWNER_ROW_MAPPER = (resultSet, i) -> {
        JSONObject attrs = new JSONObject(resultSet.getString("attrs"));
        Map<String, PerunAttribute> attributes = getAttributes(attrs);

        return new Owner(attributes);
    };

    public static final RowMapper<Resource> RESOURCE_ROW_MAPPER = (resultSet, i) -> {
        JSONObject attrs = new JSONObject(resultSet.getString("attrs"));
        Map<String, PerunAttribute> attributes = getAttributes(attrs);

        return new Resource(attributes);
    };

    public static final RowMapper<Service> SERVICE_ROW_MAPPER = (resultSet, i) -> {
        JSONObject attrs = new JSONObject(resultSet.getString("attrs"));
        Map<String, PerunAttribute> attributes = getAttributes(attrs);

        return new Service(attributes);
    };

    public static final RowMapper<User> USER_ROW_MAPPER = (resultSet, i) -> {
        JSONObject attrs = new JSONObject(resultSet.getString("attrs"));
        Map<String, PerunAttribute> attributes = getAttributes(attrs);

        return new User(attributes);
    };

    public static final RowMapper<UserExtSource> USER_EXT_SOURCE_ROW_MAPPER = (resultSet, i) -> {
        JSONObject attrs = new JSONObject(resultSet.getString("attrs"));
        Map<String, PerunAttribute> attributes = getAttributes(attrs);

        return new UserExtSource(attributes);
    };

    public static final RowMapper<Vo> VO_ROW_MAPPER = (resultSet, i) -> {
        JSONObject attrs = new JSONObject(resultSet.getString("attrs"));
        Map<String, PerunAttribute> attributes = getAttributes(attrs);

        return new Vo(attributes);
    };

    public static Map<String, PerunAttribute> getAttributes(JSONObject json) {
        log.debug("Get attributes from json: {}", json);
        Map<String, PerunAttribute> attrs = new HashMap<>();

        for (String friendlyName: json.keySet()) {
            JSONObject attrJson = json.getJSONObject(friendlyName);
            String name = attrJson.getString("name");
            String namespace = attrJson.getString("namespace");
            String type = attrJson.getString("type");
            Object value = attrJson.get("value");
            Object valueText = attrJson.get("value_text");

            PerunAttribute attr;
            if (value != null && !value.equals(JSONObject.NULL)) {
                attr = PerunAttribute.parse(friendlyName, name, namespace, type, value);
            } else {
                attr = PerunAttribute.parse(friendlyName, name, namespace, type, valueText);
            }

            if (attr != null) {
                attrs.put(friendlyName, attr);
            }
        }

        log.debug("Mapped attributes: {}", attrs);
        return attrs;
    }

}
