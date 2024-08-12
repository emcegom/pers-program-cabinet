package proj.emcegom.quality.assess.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.util.StringUtils;
import proj.emcegom.quality.assess.enums.RespStatus;
import proj.emcegom.quality.assess.exception.QABusinessException;
import proj.emcegom.quality.assess.model.entity.User;

import java.util.Calendar;

public class JWTUtil {


    public static String getToken(User user) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 7);
        return JWT.create()
                .withClaim("id", user.getId())
                .withClaim("username", user.getUsername())
                .withExpiresAt(calendar.getTime())
                .sign(Algorithm.HMAC256(user.getPassword()));
    }

    public static DecodedJWT verify(String token) throws Exception {
        if (!StringUtils.hasLength(token)){
            throw new QABusinessException(RespStatus.ERROR);
        }
        return null;
    }
}
