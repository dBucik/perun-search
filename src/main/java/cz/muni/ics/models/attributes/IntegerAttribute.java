package cz.muni.ics.models.attributes;

import cz.muni.ics.exceptions.AttributeTypeException;
import cz.muni.ics.models.attributes.enums.AttributeType;

/**
 * Class representing attribute of type Integer in Perun
 *
 * @author Dominik Frantisek Bucik <bucik@ics.muni.cz>
 */
public class IntegerAttribute extends PerunAttribute {

	private Integer value;

	public IntegerAttribute(String key, String value) {
		super.setKey(key);
		super.setType(AttributeType.INTEGER);
		setValue(value);
	}

	@Override
	public String getValue() {
		return value.toString();
	}

	public void setValue(String value) throws AttributeTypeException {
		try {
			this.value = Integer.parseInt(value);
		} catch (NumberFormatException e) {
			throw new AttributeTypeException("Cannot parse value, expected Integer", e);
		}
	}

	/**
	 * Get value in true type representation (Integer)
	 * @return value
	 */
	public Integer getTrueValue() {
		return value;
	}

}
