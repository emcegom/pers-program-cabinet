package proj.emcegom.quality.assess.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import proj.emcegom.quality.assess.common.Response;
import proj.emcegom.quality.assess.enums.RespStatus;
import proj.emcegom.quality.assess.exception.QABusinessException;
import proj.emcegom.quality.assess.model.dto.UserDTO;
import proj.emcegom.quality.assess.model.entity.User;
import proj.emcegom.quality.assess.service.UserService;
import proj.emcegom.quality.assess.utils.LogUtil;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author otis
 * @since 2024-08-11
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public Response register(@RequestBody(required = true) UserDTO user) {
        try {
            userService.register(user);
        } catch (QABusinessException e) {
            log.error(e.getMessage());
            return Response.fail(e.getStatus());
        } catch (Exception e) {
            log.error(e.getMessage());
            return Response.fail(RespStatus.ERROR);
        }
        return Response.ok(null);
    }
}
