package me.github.notsaki.userapplication.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import me.github.notsaki.userapplication.entity.response.JwtTokenEntity;
import me.github.notsaki.userapplication.domain.service.TokenService;
import me.github.notsaki.userapplication.util.AppProfile;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@Qualifier(AppProfile.IMPL)
@Profile(AppProfile.IMPL)
public class TokenServiceImpl implements TokenService {
	private final String secret;

	public TokenServiceImpl(@Value("${security.jwt.secret}") String secret) {
		this.secret = secret;
	}

	public JwtTokenEntity generateToken(String username, String issuer) {
		var algorithm = Algorithm.HMAC256(this.secret.getBytes());

		var accessToken = JWT.create()
				.withSubject(username)
				.withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
				.withIssuer(issuer)
				.sign(algorithm);


		var refreshToken = JWT.create()
				.withSubject(username)
				.withExpiresAt(new Date(System.currentTimeMillis() + 50 * 60 * 1000))
				.withIssuer(issuer)
				.sign(algorithm);

		return new JwtTokenEntity(accessToken, refreshToken);
	}

	public Optional<String> validateToken(String prefix, String token) {
		try {
			token = token.substring(prefix.length());
			var algorithm = Algorithm.HMAC256(this.secret.getBytes());

			var verifier = JWT.require(algorithm).build();
			var jwt = verifier.verify(token);

			return Optional.of(jwt.getSubject());
		} catch (Exception exception) {
			return Optional.empty();
		}
	}
}
