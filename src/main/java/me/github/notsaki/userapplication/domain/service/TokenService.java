package me.github.notsaki.userapplication.domain.service;

import me.github.notsaki.userapplication.domain.entity.response.JwtToken;

import java.util.Optional;

public interface TokenService {
	JwtToken generateToken(String username, String issuer);
	Optional<String> validateToken(String prefix, String token);
}
