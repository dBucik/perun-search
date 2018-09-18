package cz.muni.ics.exceptions;

public class InputParsingException extends Exception {

	public InputParsingException() {
		super();
	}

	public InputParsingException(String s) {
		super(s);
	}

	public InputParsingException(String s, Throwable throwable) {
		super(s, throwable);
	}

	public InputParsingException(Throwable throwable) {
		super(throwable);
	}

	protected InputParsingException(String s, Throwable throwable, boolean b, boolean b1) {
		super(s, throwable, b, b1);
	}
}
