package cz.muni.ics.models.attributes;

import java.util.LinkedHashMap;

/**
 * Class representing attribute of type LinkedHashMap in Perun
 *
 * @author Dominik Frantisek Bucik <bucik@ics.muni.cz>
 */
public class MapAttribute extends PerunAttribute {

	private LinkedHashMap<String, String> value;

	public MapAttribute(String key, String value) {
		super.setKey(key);
		super.setType(AttributeType.MAP);
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
}
