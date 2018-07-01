package cz.muni.ics.exceptions;

public class DatabaseIntegrityException extends Exception {

    public DatabaseIntegrityException() {
        super();
    }

    public DatabaseIntegrityException(String s) {
        super(s);
    }

    public DatabaseIntegrityException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public DatabaseIntegrityException(Throwable throwable) {
        super(throwable);
    }

    protected DatabaseIntegrityException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
