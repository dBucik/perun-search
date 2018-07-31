package cz.muni.ics.models;

import cz.muni.ics.models.attributes.enums.InputAttributeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Attribute read from input for GraphQL.
 *
 * @author Dominik Frantisek Bucik <bucik@ics.muni.cz>
 */
public class InputAttribute {
    
    private static final Logger log = LoggerFactory.getLogger(InputAttribute.class);

    private String key;
    private String type;
    private InputAttributeType attrType;
    private String value;

    public InputAttribute() {}

    public InputAttribute(String key, String type, String value) {
        this.key = key;
        this.type = type;
        this.attrType = resolveType(type);
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
        this.attrType = resolveType(type);
    }

    public InputAttributeType getAttributeType() {
        return attrType;
    }

    public void setAttributeType(String type) {
        this.type = type;
        this.attrType = resolveType(type);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private InputAttributeType resolveType(String type) {
        log.debug("Resolving InputAttributeType from String: {}", type);
        if (type == null || type.isEmpty()) {
            log.debug("Resolved type: {}" , InputAttributeType.STRING);
            return InputAttributeType.STRING;
        }

        InputAttributeType res;

        switch (type.toUpperCase()) {
            case "STRING": res = InputAttributeType.STRING;
                break;
            case "EXACT_STRING": res = InputAttributeType.EXACT_STRING;
                break;
            case "INTEGER": res = InputAttributeType.INTEGER;
                break;
            case "BOOLEAN": res = InputAttributeType.BOOLEAN;
                break;
            case "ARRAY": res = InputAttributeType.ARRAY;
                break;
            case "MAP": res = InputAttributeType.MAP;
                break;
            case "LARGE_STRING": res = InputAttributeType.LARGE_STRING;
                break;
            case "EXACT_LARGE_STRING": res = InputAttributeType.EXACT_LARGE_STRING;
                break;
            case "LARGE_LIST": res = InputAttributeType.LARGE_LIST;
                break;
            default: res = InputAttributeType.STRING;
        }

        log.debug("Resolved type: {}" , res);

        return res;
    }

}
