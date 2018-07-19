package cz.muni.ics.models;

import cz.muni.ics.models.attributes.InputAttributeType;

/**
 * Attribute read from input for GraphQL.
 *
 * @author Dominik Frantisek Bucik <bucik@ics.muni.cz>
 */
public class InputAttribute {

    private String key;
    private String type;
    private InputAttributeType type1;
    private String value;

    public InputAttribute() {}

    public InputAttribute(String key, String type, String value) {
        this.key = key;
        this.type = type;
        this.type1 = resolveType(type);
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
        this.type1 = resolveType(type);
    }

    public InputAttributeType getType1() {
        return type1;
    }

    public void setType1(String type) {
        this.type = type;
        this.type1 = resolveType(type);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private InputAttributeType resolveType(String type) {
    	if (type == null || type.isEmpty()) {
    		return InputAttributeType.STRING;
		}

        switch (type.toUpperCase()) {
            case "STRING": return InputAttributeType.STRING;
            case "EXACT_STRING": return InputAttributeType.EXACT_STRING;
            case "INTEGER": return InputAttributeType.INTEGER;
            case "BOOLEAN": return InputAttributeType.BOOLEAN;
            case "ARRAY": return InputAttributeType.ARRAY;
            case "MAP": return InputAttributeType.MAP;
            case "LARGE_STRING": return InputAttributeType.LARGE_STRING;
            case "EXACT_LARGE_STRING": return InputAttributeType.EXACT_LARGE_STRING;
            case "LARGE_LIST": return InputAttributeType.LARGE_LIST;
            default: return InputAttributeType.STRING;
        }
    }

}
