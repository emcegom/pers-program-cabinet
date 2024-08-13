package proj.emcegom.quality.assess.exception;

import java.io.Serial;

public class JsonCastException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -8122963589829608124L;

    public JsonCastException(String message, Throwable cause) {
        super(message, cause);
    }
}
