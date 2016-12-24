package net.kurikagononaka.mapImager.nbt.exception;

/**
 *
 */
public class UnknownTagIdException extends ParseTagIdException {
    public UnknownTagIdException() {
    }

    public UnknownTagIdException(String message) {
        super(message);
    }

    public UnknownTagIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownTagIdException(Throwable cause) {
        super(cause);
    }

    public UnknownTagIdException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
