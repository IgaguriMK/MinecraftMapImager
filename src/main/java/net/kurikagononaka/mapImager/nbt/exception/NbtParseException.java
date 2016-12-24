package net.kurikagononaka.mapImager.nbt.exception;

/**
 *
 */
public class NbtParseException extends RuntimeException {
    public NbtParseException() {
    }

    public NbtParseException(String message) {
        super(message);
    }

    public NbtParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public NbtParseException(Throwable cause) {
        super(cause);
    }

    public NbtParseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
