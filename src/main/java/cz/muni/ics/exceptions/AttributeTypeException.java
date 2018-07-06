package cz.muni.ics.exceptions;

/**
 * Exception represents error in type of value provided to the attribute
 */
public class AttributeTypeException extends Exception {

	public AttributeTypeException() {
		super();
	}

	public AttributeTypeException(String s) {
		super(s);
	}

	public AttributeTypeException(String s, Throwable throwable) {
		super(s, throwable);
	}

	public AttributeTypeException(Throwable throwable) {
		super(throwable);
	}

	protected AttributeTypeException(String s, Throwable throwable, boolean b, boolean b1) {
		super(s, throwable, b, b1);
	}
}
