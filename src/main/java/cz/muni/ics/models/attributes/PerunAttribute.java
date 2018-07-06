package cz.muni.ics.models.attributes;

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
		switch (type) {
			case STRING_TYPE:
				return new StringAttribute(key, (String) value);
			case INT_TYPE:
				return new IntegerAttribute(key, (Integer) value);
			case BOOL_TYPE:
				return new BooleanAttribute(key, (Boolean) value);
			case LSTRING_TYPE:
				return new LargeStringAttribute(key, (String) value);
			case ARRAY_TYPE:
				return new ArrayAttribute(key, (String) value);
			case MAP_TYPE:
				return new MapAttribute(key, (String) value);
			case LARRAY_LIST_TYPE:
				return new LargeArrayListAttribute(key, (String) value);
			default:
				return null;
		}
	}
}