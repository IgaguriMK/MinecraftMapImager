package net.kurikagononaka.mapImager.nbt.exception;

/**
 *
 */
public class SourceEndException extends NbtParseException {
    public SourceEndException() {
    }

    public SourceEndException(String message) {
        super(message);
    }

    public SourceEndException(String message, Throwable cause) {
        super(message, cause);
    }

    public SourceEndException(Throwable cause) {
        super(cause);
    }

    public SourceEndException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
