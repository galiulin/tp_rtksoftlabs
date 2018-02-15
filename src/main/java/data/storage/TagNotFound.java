package data.storage;

public class TagNotFound extends RuntimeException {
    public TagNotFound() {
    }

    public TagNotFound(String message) {
        super(message);
    }

    public TagNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public TagNotFound(Throwable cause) {
        super(cause);
    }

    public TagNotFound(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
