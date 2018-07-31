package cz.muni.ics.models.attributes;

import cz.muni.ics.models.attributes.enums.AttributeType;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class representing attribute of type LargeArrayList in Perun
 *
 * @author Dominik Frantisek Bucik <bucik@ics.muni.cz>
 */
public class LargeArrayListAttribute extends PerunAttribute {

	private ArrayList<String> value;

	public LargeArrayListAttribute(String friendlyName, String name, String namespace, String value) {
		super(friendlyName, name, namespace, AttributeType.LARGE_LIST);
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
	 * Get value in true type representation (LargeArrayList)
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
