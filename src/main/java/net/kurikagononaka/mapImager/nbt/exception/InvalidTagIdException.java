package net.kurikagononaka.mapImager.nbt.exception;

/**
 *
 */
public class InvalidTagIdException extends ParseTagIdException {
    public InvalidTagIdException() {
    }

    public InvalidTagIdException(String message) {
        super(message);
    }

    public InvalidTagIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidTagIdException(Throwable cause) {
        super(cause);
    }

    public InvalidTagIdException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
