package me.github.notsaki.userapplication.infrastructure.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import me.github.notsaki.userapplication.domain.util.TokenType;
import me.github.notsaki.userapplication.domain.util.TokenUtil;

import java.util.Date;
import java.util.Optional;

public class TokenUtilImpl implements TokenUtil {
    private static final int HOUR = 60 * 1000;
    private static final int ACCESS_EXPIRATION = HOUR;
    private static final int REFRESH_EXPIRATION = 48 * HOUR;
    private final String secret;

    public TokenUtilImpl(String secret) {
        this.secret = secret;
    }

    @Override
    public String create(TokenType type, String username, String issuer) {
        var algorithm = Algorithm.HMAC256(this.secret.getBytes());

        var expiration = type == TokenType.ACCESS ? ACCESS_EXPIRATION : REFRESH_EXPIRATION;

        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(expiration))
                .withIssuer(issuer)
                .sign(algorithm);
    }

    @Override
    public Optional<String> extractUsername(String token) {
        try {
            var algorithm = Algorithm.HMAC256(this.secret.getBytes());

            var verifier = JWT.require(algorithm).build();
            var jwt = verifier.verify(token);

            return Optional.of(jwt.getSubject());
        } catch (Exception exception) {
            return Optional.empty();
        }
    }
}
