package cz.muni.ics.models.attributes;

import cz.muni.ics.models.attributes.enums.AttributeType;

/**
 * Class representing attribute of type LargeString in Perun
 *
 * @author Dominik Frantisek Bucik <bucik@ics.muni.cz>
 */
public class LargeStringAttribute extends PerunAttribute {

	private String value;

	public LargeStringAttribute(String key, String value) {
		super.setKey(key);
		super.setType(AttributeType.LARGE_STRING);
		this.value = value;
	}

	@Override
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * Get value in true type representation (LargeString)
	 * @return value
	 */
	public String getTrueValue() {
		return value;
	}

}
