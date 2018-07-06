package cz.muni.ics.models.attributes;

import cz.muni.ics.exceptions.AttributeTypeException;

/**
 * Class representing attribute of type Boolean in Perun
 *
 * @author Dominik Frantisek Bucik <bucik@ics.muni.cz>
 */
public class BooleanAttribute extends PerunAttribute {

	private Boolean value;

	public BooleanAttribute(String key, Boolean value) {
		super.setKey(key);
		super.setType(AttributeType.BOOLEAN);
		this.value = value;
	}

	@Override
	public String getValue() {
		return value.toString();
	}

	public void setValue(String value) throws AttributeTypeException {
		try {
			this.value = Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			throw new AttributeTypeException("Cannot parse value, expected Boolean", e);
		}
	}

	/**
	 * Get value in true type representation (Boolean)
	 * @return value
	 */
	public Boolean getTrueValue() {
		return value;
	}

}
