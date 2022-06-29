package me.github.notsaki.userapplication.domain.service;

import me.github.notsaki.userapplication.domain.entity.response.JwtToken;

import java.util.Optional;

public interface TokenService {
	JwtToken generateToken(String username, String issuer);

	/**
	 * Decode and validate JWT token.
	 * @param prefix The header prefix that is going to be trimmed.
	 * @param token the Authorization header value.
	 * @return Either the subject's username if token is valid or empty if token is not validated.
	 */
	Optional<String> validateToken(String prefix, String token);
}
