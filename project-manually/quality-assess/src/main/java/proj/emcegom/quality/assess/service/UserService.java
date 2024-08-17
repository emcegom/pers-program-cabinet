package proj.emcegom.quality.assess.service;

import proj.emcegom.quality.assess.annotation.Log;
import proj.emcegom.quality.assess.model.dto.UserDTO;
import proj.emcegom.quality.assess.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author otis
 * @since 2024-08-11
 */
public interface UserService extends IService<User> {

    UserDTO register(UserDTO user);

}
