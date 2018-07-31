package cz.muni.ics.models.attributes;

import cz.muni.ics.models.attributes.enums.AttributeType;
import org.json.JSONObject;

public abstract class PerunAttribute {

	private final static String STRING_TYPE = "java.lang.String";
	private final static String INT_TYPE = "java.lang.Integer";
	private final static String BOOL_TYPE = "java.lang.Boolean";
	private final static String ARRAY_TYPE = "java.util.ArrayList";
	private final static String MAP_TYPE = "java.util.LinkedHashMap";
	private final static String LSTRING_TYPE = "java.lang.LargeString";
	private final static String LARRAY_LIST_TYPE = "java.lang.LargeArrayList";

	private String key;
	private AttributeType type;

	public String getKey() {
		return key;
	}

	public void setKey(String attrName) {
		this.key = attrName;
	}

	public AttributeType getType() {
		return type;
	}

	public void setType(AttributeType type) {
		this.type = type;
	}

	public abstract String getValue();

	public static PerunAttribute parse(String key, String type, Object value) {
		if (value == null || value.equals(JSONObject.NULL)) {
			return null;
		}
		String val = (String) value;
		if (val.isEmpty()) {
			return null;
		}
		switch (type) {
			case STRING_TYPE:
				return new StringAttribute(key, val);
			case INT_TYPE:
				return new IntegerAttribute(key, val);
			case BOOL_TYPE:
				return new BooleanAttribute(key, val);
			case LSTRING_TYPE:
				return new LargeStringAttribute(key, val);
			case ARRAY_TYPE:
				return new ArrayAttribute(key, val);
			case MAP_TYPE:
				return new MapAttribute(key, val);
			case LARRAY_LIST_TYPE:
				return new LargeArrayListAttribute(key, val);
			default:
				return null;
		}
	}
}