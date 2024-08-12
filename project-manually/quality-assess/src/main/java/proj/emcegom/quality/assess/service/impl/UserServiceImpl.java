package proj.emcegom.quality.assess.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import proj.emcegom.quality.assess.QualityAssessApplication;
import proj.emcegom.quality.assess.common.Response;
import proj.emcegom.quality.assess.enums.RespStatus;
import proj.emcegom.quality.assess.exception.QABusinessException;
import proj.emcegom.quality.assess.model.dto.UserDTO;
import proj.emcegom.quality.assess.model.entity.User;
import proj.emcegom.quality.assess.mapper.UserMapper;
import proj.emcegom.quality.assess.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import proj.emcegom.quality.assess.utils.LogUtil;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author otis
 * @since 2024-08-11
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(UserDTO user) {
        if (!StringUtils.hasLength(user.getUsername())
                || !StringUtils.hasLength(user.getPassword())
                || !StringUtils.hasLength(user.getEmail())
                || !StringUtils.hasLength(user.getName())) {
            throw new QABusinessException(RespStatus.USER_REGISTER_PARAMS_INCOMPLETE);
        }

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(User::getUsername, user.getUsername())
                .or()
                .eq(User::getEmail, user.getEmail());
        List<User> users = userMapper.selectList(queryWrapper);
        Optional<User> usernameExists = users.stream().filter(e -> e.getUsername().equals(user.getUsername())).findFirst();
        if (usernameExists.isPresent()) {
            throw new QABusinessException(RespStatus.USER_REGISTER_USERNAME_EXISTS);
        }
        Optional<User> emailExists = users.stream().filter(e -> e.getEmail().equals(user.getEmail())).findFirst();
        if (emailExists.isPresent()) {
            throw new QABusinessException(RespStatus.USER_REGISTER_EMAIL_EXISTS);
        }
        User userDO = new User();
        BeanUtils.copyProperties(user, userDO);
        int rows = userMapper.insert(userDO);
        if (rows != 1) {
            throw new QABusinessException(RespStatus.USER_REGISTER_FAIL);
        }

    }
}
