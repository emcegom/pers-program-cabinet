package proj.emcegom.quality.assess.common.tl;

import proj.emcegom.quality.assess.model.dto.UserDTO;
import proj.emcegom.quality.assess.model.entity.User;

public class UserHolder {
    private static final ThreadLocal<UserDTO> THREAD_LOCAL = new ThreadLocal<>();

    private UserHolder() {
    }

    public static UserDTO getUserInfo() {
        return THREAD_LOCAL.get();
    }

    public static void setUserInfo(UserDTO user) {
        THREAD_LOCAL.set(user);
    }

    public static Integer getUserId() {
        UserDTO dto = THREAD_LOCAL.get();
        return null == dto ? 0 : dto.getId();
    }

    public static void remove(){
        THREAD_LOCAL.remove();
    }
}
