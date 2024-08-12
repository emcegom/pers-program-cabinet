package proj.emcegom.quality.assess.exception;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import proj.emcegom.quality.assess.enums.RespStatus;

import java.io.Serial;

@Getter
public class QABusinessException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 9171837254645810859L;
    @NotNull
    private final RespStatus status;

    public QABusinessException(RespStatus status) {
        super(status.getMsg());
        this.status = status;
    }
}
