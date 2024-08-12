package proj.emcegom.quality.assess;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Objects;

public class JwtTest {

    @Test
    public void contextLoad(){
        HashMap<String, Object> map = new HashMap<>();

        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND, 20);

        String sign = JWT.create()
                .withHeader(map)
                .withClaim("userId", 21)
                .withClaim("username", "xxxx")
                .withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256("xsdgg"));
        System.out.println(sign);
    }
}
