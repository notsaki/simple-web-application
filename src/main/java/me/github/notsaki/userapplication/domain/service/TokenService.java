package me.github.notsaki.userapplication.domain.service;

import me.github.notsaki.userapplication.domain.entity.response.JwtToken;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

public interface TokenService {
	JwtToken generate(User principal, String issuer);
	Optional<String> validate(String prefix, String token);
}
