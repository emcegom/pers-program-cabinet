package proj.emcegom.quality.assess.common;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import proj.emcegom.quality.assess.enums.RespStatus;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Response {
    Boolean success;
    Integer code;
    String msg;
    Object data;

    public static Response ok(Object data) {
        RespStatus status = RespStatus.SUCCESS;
        return Response.builder()
                .success(true)
                .code(status.getCode())
                .msg(null)
                .data(data).build();
    }

    public static Response fail(RespStatus status) {
        return Response.builder()
                .success(false)
                .code(status.getCode())
                .msg(status.getMsg())
                .data(null).build();
    }

}
