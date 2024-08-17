package proj.emcegom.quality.assess.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import proj.emcegom.quality.assess.annotation.Log;
import proj.emcegom.quality.assess.enums.RespStatus;
import proj.emcegom.quality.assess.exception.QABusinessException;
import proj.emcegom.quality.assess.model.dto.UserDTO;
import proj.emcegom.quality.assess.model.entity.User;
import proj.emcegom.quality.assess.mapper.UserMapper;
import proj.emcegom.quality.assess.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import proj.emcegom.quality.assess.utils.JwtUtils;

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
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserMapper userMapper,
                           PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserDTO register(UserDTO userDTO) {
        if (!StringUtils.hasLength(userDTO.getUsername())
                || !StringUtils.hasLength(userDTO.getPassword())
                || !StringUtils.hasLength(userDTO.getEmail())
                || !StringUtils.hasLength(userDTO.getName())) {
            throw new QABusinessException(RespStatus.USER_REGISTER_PARAMS_INCOMPLETE);
        }

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(User::getUsername, userDTO.getUsername())
                .or()
                .eq(User::getEmail, userDTO.getEmail());
        List<User> users = userMapper.selectList(queryWrapper);
        Optional<User> usernameExists = users.stream().filter(e -> e.getUsername().equals(userDTO.getUsername())).findFirst();
        if (usernameExists.isPresent()) {
            throw new QABusinessException(RespStatus.USER_REGISTER_USERNAME_EXISTS);
        }
        Optional<User> emailExists = users.stream().filter(e -> e.getEmail().equals(userDTO.getEmail())).findFirst();
        if (emailExists.isPresent()) {
            throw new QABusinessException(RespStatus.USER_REGISTER_EMAIL_EXISTS);
        }
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setUsername(userDTO.getUsername());
        int rows = userMapper.insert(user);
        if (rows != 1) {
            throw new QABusinessException(RespStatus.USER_REGISTER_FAIL);
        }
        String token = JwtUtils.generateToken(userDTO);
        // TODO set toke to redis
        userDTO.setToken(token);
        log.info("register success : {}", userDTO);
        return userDTO;
    }
}
