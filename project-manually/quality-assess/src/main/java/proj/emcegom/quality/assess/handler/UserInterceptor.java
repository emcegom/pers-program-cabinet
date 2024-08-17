package proj.emcegom.quality.assess.handler;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import proj.emcegom.quality.assess.common.tl.UserHolder;
import proj.emcegom.quality.assess.enums.RespStatus;
import proj.emcegom.quality.assess.exception.QABusinessException;
import proj.emcegom.quality.assess.model.dto.UserDTO;
import proj.emcegom.quality.assess.model.entity.User;
import proj.emcegom.quality.assess.utils.JwtUtils;

@Component
@Slf4j
public class UserInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        String token = request.getHeader("token");
        if (StringUtils.hasLength(token)) {
            UserDTO user = JwtUtils.verifyAndGetUser(token);
            if (user != null) {
                UserHolder.setUserInfo(user);
            } else {
                log.error("token verify fail! token is {}, uri is {}", token, request.getRequestURI());
                throw new QABusinessException(RespStatus.TOKEN_VERIFY_FAIL);
            }
        } else {
            log.error("token verify fail! token is {}, uri is {}", token, request.getRequestURI());
            throw new QABusinessException(RespStatus.TOKEN_IS_EMPTY);
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        UserHolder.remove();
    }

}
