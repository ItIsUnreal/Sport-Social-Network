package dev.vlad.sport.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class JwtUtil {

    private final String SECRET_KEY = "vlad";
    private final Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
    private final JWTVerifier verifier = JWT.require(algorithm).build();

    public User extractUser(String token) {
        try {
            DecodedJWT decodedJWT = verifier.verify(token);
            return new User(
                decodedJWT.getSubject(),
                decodedJWT.getClaim("isAdmin").asBoolean()
            );
        } catch (Exception ex) {
            return null;
        }

    }

    public Date extractExpiration(String token) {
        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT.getExpiresAt();
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(String username, Boolean isAdmin) {
        return JWT.create()
            .withSubject(username)
            .withIssuedAt(new Date())
            .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours validity
            .withClaim("isAdmin", isAdmin)
            .sign(algorithm);
    }

    public boolean validateToken(String token) {
        try {
            verifier.verify(token);
            return !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }
}
