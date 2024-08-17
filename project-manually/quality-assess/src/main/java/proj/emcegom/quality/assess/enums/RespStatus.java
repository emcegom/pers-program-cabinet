package proj.emcegom.quality.assess.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RespStatus {


    USER_REGISTER_PARAMS_INCOMPLETE(40001, "user register params [username || password || email || name] are incomplete"),
    USER_REGISTER_USERNAME_EXISTS(40002, "user register param [username] exists"),
    USER_REGISTER_EMAIL_EXISTS(40003, "user register param [email] exists"),
    USER_REGISTER_FAIL(40004, "user register fail, please try again"),
    TOKEN_VERIFY_FAIL(40010, "token verify fail"),
    TOKEN_IS_EMPTY(40011, "token is empty"),
    SUCCESS(200, "success"),
    ERROR(500, "system error");


    private final Integer code;
    private final String msg;


}
