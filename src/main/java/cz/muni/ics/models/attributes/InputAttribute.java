package cz.muni.ics.models.attributes;

import cz.muni.ics.exceptions.InputParsingException;
import cz.muni.ics.models.enums.InputAttributeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

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

    public InputAttribute(String key, String type, String value) throws InputParsingException {
        if (key == null || key.trim().length() <= 0) {
            throw new InputParsingException("Attribute key undefined.");
        } else if (type == null || type.trim().length() <= 0) {
            throw new InputParsingException("Attribute type undefined");
        } else if (value == null || value.trim().length() <= 0) {
            throw new InputParsingException("Attribute value undefined");
        }

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

    public void setType(String type) throws InputParsingException {
        this.type = type;
        this.attrType = resolveType(type);
    }

    public InputAttributeType getAttributeType() {
        return attrType;
    }

    public void setAttributeType(String type) throws InputParsingException {
        this.type = type;
        this.attrType = resolveType(type);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private InputAttributeType resolveType(String type) throws InputParsingException {
        log.debug("Resolving InputAttributeType from String: {}", type);
        if (type == null || type.isEmpty()) {
            throw new InputParsingException("Attribute type cannot be decided.");
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

    @Override
    public String toString() {
        return "InputAttribute [" +
				"key: " + key +
				", type: " + type +
				", attrType: " + attrType +
				", value: " + value +
				"]";
    }

    @Override
    public boolean equals(Object o) {
        if (! (o instanceof InputAttribute)) {
            return false;
        }

        InputAttribute a = (InputAttribute) o;
        return super.equals(o) &&
                Objects.equals(key, a.key) &&
                Objects.equals(type, a.type) &&
                Objects.equals(attrType, a.attrType) &&
                Objects.equals(value, a.value);
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        if (key != null) hash *= 31 * key.hashCode();
        if (type != null) hash *= 31 * type.hashCode();
        if (attrType != null) hash *= 31 * attrType.hashCode();
        if (value != null) hash *= 31 * value.hashCode();

        return hash;
    }
    
}
