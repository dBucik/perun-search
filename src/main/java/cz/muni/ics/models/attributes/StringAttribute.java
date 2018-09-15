package cz.muni.ics.models.attributes;

import cz.muni.ics.models.enums.AttributeType;

import java.util.Objects;

/**
 * Class representing attribute of type String in Perun
 *
 * @author Dominik Frantisek Bucik <bucik@ics.muni.cz>
 */
public class StringAttribute extends PerunAttribute {

	private String value;

	public StringAttribute(String friendlyName, String name, String namespace, String value) {
		super(friendlyName, name, namespace, AttributeType.STRING);
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
	 * Get value in true type representation (String)
	 * @return value
	 */
	public String getTrueValue() {
		return value;
	}

	@Override
	public String toString() {
		return "StringAttribute [" +
				"name: " + getName() +
				", friendlyName: " + getFriendlyName() +
				", namespace: " + getNamespace() +
				", value: " + value +
				"]";
	}

	@Override
	public boolean equals(Object o) {
		if (! (o instanceof StringAttribute)) {
			return false;
		}

		StringAttribute a = (StringAttribute) o;
		return super.equals(o) && Objects.equals(this.getTrueValue(), a.getTrueValue());
	}

	@Override
	public int hashCode() {
		int hash = super.hashCode();
		if (getTrueValue() != null) hash *= 31 * getTrueValue().hashCode();

		return hash;
	}

}
