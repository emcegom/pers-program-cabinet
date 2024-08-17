package proj.emcegom.quality.assess.utils;

import cn.hutool.jwt.JWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import proj.emcegom.quality.assess.model.dto.UserDTO;
import proj.emcegom.quality.assess.model.entity.User;

import javax.swing.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtils {
    private static long expireTimeInSec = 0L;
    private static byte[] secretKey = "".getBytes();

    private static final String KEY_ID = "id";
    private static final String KEY_USER_NAME = "username";

    @Value("${jwt.token.secretKey}")
    public void setSecretKey(String secretKey) {
        JwtUtils.secretKey = secretKey.getBytes();
    }

    @Value("${jwt.token.expireTimeInSec}")
    public void setExpireTime(long expireTimeInSec) {
        JwtUtils.expireTimeInSec = expireTimeInSec;
    }

    public static String generateToken(UserDTO dto) {
        HashMap<String, Object> map = new HashMap<>();
        map.put(KEY_ID, dto.getId());
        map.put(KEY_USER_NAME, dto.getUsername());
        return generateToken(map);
    }

    public static String generateToken(Map<String, Object> map) {
        JWT jwt = JWT.create();
        map.forEach(jwt::setPayload);
        jwt.setKey(secretKey);
        jwt.setExpiresAt(getExpireDate());
        return jwt.sign();
    }

    public static Date getExpireDate() {
        return new Date(System.currentTimeMillis() + expireTimeInSec * 1000L);
    }

    public static boolean verify(String token) {
        if (!StringUtils.hasLength(token)) return false;
        return JWT.of(token).setKey(secretKey).verify();
    }

    public static UserDTO verifyAndGetUser(String token) {
        if (!verify(token)) return null;
        JWT jwt = JWT.of(token);
        Integer id = Integer.valueOf(jwt.getPayload(KEY_ID).toString());
        String username = jwt.getPayload(KEY_USER_NAME).toString();
        return UserDTO.builder().id(id).username(username).build();
    }
}
