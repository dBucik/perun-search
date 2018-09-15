package cz.muni.ics.models.attributes;

import cz.muni.ics.models.enums.AttributeType;

import java.util.LinkedHashMap;
import java.util.Objects;

/**
 * Class representing attribute of type LinkedHashMap in Perun
 *
 * @author Dominik Frantisek Bucik <bucik@ics.muni.cz>
 */
public class MapAttribute extends PerunAttribute {

	private LinkedHashMap<String, String> value;

	public MapAttribute(String friendlyName, String name, String namespace, String value) {
		super(friendlyName, name, namespace, AttributeType.MAP);
		this.value = parseValue(value);
	}

	@Override
	public String getValue() {
		return value.toString();
	}

	public void setValue(String value) {
		this.value = parseValue(value);
	}

	/**
	 * Get value in true type representation (LinkedHashMap)
	 * @return value
	 */
	public LinkedHashMap<String,String> getTrueValue() {
		return value;
	}

	private LinkedHashMap<String,String> parseValue(String value) {
		String pairs[] = value.split("(?<!\\\\),");
		LinkedHashMap<String, String> val = new LinkedHashMap<>();
		for (String pair: pairs) {
			pair = pair.replaceAll("\\\\", "");
			String[] keyVal = pair.split(":", 2);
			val.put(keyVal[0], keyVal[1]);
		}

		return val;
	}

	@Override
	public String toString() {
		return "MapAttribute [" +
				"name: " + getName() +
				", friendlyName: " + getFriendlyName() +
				", namespace: " + getNamespace() +
				", value: " + value +
				"]";
	}

	@Override
	public boolean equals(Object o) {
		if (! (o instanceof MapAttribute)) {
			return false;
		}

		MapAttribute a = (MapAttribute) o;
		return super.equals(o) && Objects.equals(this.getTrueValue(), a.getTrueValue());
	}

	@Override
	public int hashCode() {
		int hash = super.hashCode();
		if (getTrueValue() != null) hash *= 31 * getTrueValue().hashCode();

		return hash;
	}

}
