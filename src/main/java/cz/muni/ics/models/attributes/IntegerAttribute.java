package cz.muni.ics.models.attributes;

import cz.muni.ics.exceptions.AttributeTypeException;
import cz.muni.ics.models.enums.AttributeType;

import java.util.Objects;

/**
 * Class representing attribute of type Integer in Perun
 *
 * @author Dominik Frantisek Bucik <bucik@ics.muni.cz>
 */
public class IntegerAttribute extends PerunAttribute {

	private Integer value;

	public IntegerAttribute(String friendlyName, String name, String namespace, String value) {
		super(friendlyName, name, namespace, AttributeType.INTEGER);
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

	@Override
	public String toString() {
		return "IntegerAttribute [" +
				"name: " + getName() +
				", friendlyName: " + getFriendlyName() +
				", namespace: " + getNamespace() +
				", value: " + value +
				"]";
	}

	@Override
	public boolean equals(Object o) {
		if (! (o instanceof IntegerAttribute)) {
			return false;
		}

		IntegerAttribute a = (IntegerAttribute) o;
		return super.equals(o) && Objects.equals(this.getTrueValue(), a.getTrueValue());
	}

	@Override
	public int hashCode() {
		int hash = super.hashCode();
		if (getTrueValue() != null) hash *= 31 * getTrueValue().hashCode();

		return hash;
	}

}
