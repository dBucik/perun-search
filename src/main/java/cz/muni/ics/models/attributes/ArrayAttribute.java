package cz.muni.ics.models.attributes;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class representing attribute of type Array in Perun
 *
 * @author Dominik Frantisek Bucik <bucik@ics.muni.cz>
 */
public class ArrayAttribute extends PerunAttribute {

	private ArrayList<String> value;

	public ArrayAttribute(String key, String value) {
		super.setKey(key);
		super.setType(AttributeType.ARRAY);
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
	 * Get value in true type representation (Array)
	 * @return value
	 */
	public ArrayList<String> getTrueValue() {
		return value;
	}


	private ArrayList<String> parseValue(String value) {
		String[] parts = value.split(",");
		return new ArrayList<>(Arrays.asList(parts));
	}
}
