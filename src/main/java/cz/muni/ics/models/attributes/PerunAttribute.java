package cz.muni.ics.models.attributes;

import cz.muni.ics.models.enums.AttributeType;
import org.json.JSONObject;

import java.util.Objects;

public abstract class PerunAttribute {

	private final static String STRING_TYPE = "java.lang.String";
	private final static String INT_TYPE = "java.lang.Integer";
	private final static String BOOL_TYPE = "java.lang.Boolean";
	private final static String ARRAY_TYPE = "java.util.ArrayList";
	private final static String MAP_TYPE = "java.util.LinkedHashMap";
	private final static String LSTRING_TYPE = "java.lang.LargeString";
	private final static String LARRAY_LIST_TYPE = "java.lang.LargeArrayList";

	private String friendlyName;
	private String name;
	private String namespace;
	private AttributeType type;

	public PerunAttribute(String friendlyName, String name, String namespace, AttributeType type) {
		this.friendlyName = friendlyName;
		this.name = name;
		this.namespace = namespace;
		this.type = type;
	}

	public String getFriendlyName() {
		return friendlyName;
	}

	public void setFriendlyName(String attrName) {
		this.friendlyName = attrName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public AttributeType getType() {
		return type;
	}

	public void setType(AttributeType type) {
		this.type = type;
	}

	public abstract String getValue();

	public static PerunAttribute parse(String friendlyName, String name, String namespace, String type, Object value) {
		if (value == null || value.equals(JSONObject.NULL)) {
			return null;
		}

		String val = (String) value;
		if (val.isEmpty()) {
			return null;
		}

		switch (type) {
			case STRING_TYPE:
				return new StringAttribute(friendlyName, name, namespace, val);
			case INT_TYPE:
				return new IntegerAttribute(friendlyName, name, namespace, val);
			case BOOL_TYPE:
				return new BooleanAttribute(friendlyName, name, namespace, val);
			case LSTRING_TYPE:
				return new LargeStringAttribute(friendlyName, name, namespace, val);
			case ARRAY_TYPE:
				return new ArrayAttribute(friendlyName, name, namespace, val);
			case MAP_TYPE:
				return new MapAttribute(friendlyName, name, namespace, val);
			case LARRAY_LIST_TYPE:
				return new LargeArrayListAttribute(friendlyName, name, namespace, val);
			default:
				return null;
		}
	}

	@Override
	public String toString() {
		return "ArrayAttribute [" +
				"name: " + getName() +
				", friendlyName: " + getFriendlyName() +
				", namespace: " + getNamespace() +
				"]";
	}

	@Override
	public boolean equals(Object o) {
		if (! (o instanceof PerunAttribute)) {
			return false;
		}
		PerunAttribute a = (PerunAttribute) o;
		return Objects.equals(this.name, a.name) && Objects.equals(this.type, a.type);
	}

	@Override
	public int hashCode() {
		int hash = 1;
		if (name != null) hash *= name.hashCode() * 31;
		if (type != null) hash *= type.hashCode() * 31;

		return hash;
	}
}