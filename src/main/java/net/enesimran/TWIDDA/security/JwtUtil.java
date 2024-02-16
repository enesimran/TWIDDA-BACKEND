package net.enesimran.TWIDDA.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

@Component
public class JwtUtil {

    @Value("${userbucket.key}")
    private String KEY;

    public String generateToken(String userId) {
        return JWT.create()
                .withSubject(userId)
                .withExpiresAt(new Date(System.currentTimeMillis() + 86400000)) // 1 Tag Gültigkeit
                .sign(Algorithm.HMAC256(KEY));
    }

    public boolean validateToken(String token, String userId) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(KEY))
                    .withSubject(userId)
                    .build();
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException exception) {
            return false;
        }
    }

    public String getUserIdFromToken(String token) {
        DecodedJWT decoded = JWT.decode(token);
        return decoded.getSubject();
    }

}
