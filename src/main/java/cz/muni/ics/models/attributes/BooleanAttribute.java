package cz.muni.ics.models.attributes;

import cz.muni.ics.exceptions.AttributeTypeException;
import cz.muni.ics.models.attributes.enums.AttributeType;

/**
 * Class representing attribute of type Boolean in Perun
 *
 * @author Dominik Frantisek Bucik <bucik@ics.muni.cz>
 */
public class BooleanAttribute extends PerunAttribute {

	private Boolean value;

	public BooleanAttribute(String friendlyName, String name, String namespace, String value) {
		super(friendlyName, name, namespace, AttributeType.BOOLEAN);
		setValue(value);
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
