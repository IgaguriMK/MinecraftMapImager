package net.kurikagononaka.mapImager.nbt.exception;

/**
 *
 */
public class ParseTagIdException extends NbtParseException {
    public ParseTagIdException() {
    }

    public ParseTagIdException(String message) {
        super(message);
    }

    public ParseTagIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParseTagIdException(Throwable cause) {
        super(cause);
    }

    public ParseTagIdException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
