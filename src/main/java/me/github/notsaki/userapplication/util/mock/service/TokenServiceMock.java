package me.github.notsaki.userapplication.util.mock.service;

import me.github.notsaki.userapplication.domain.entity.response.JwtToken;
import me.github.notsaki.userapplication.domain.service.TokenService;
import me.github.notsaki.userapplication.util.stub.admin.AdminStub;
import me.github.notsaki.userapplication.util.stub.token.TokenStub;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

public class TokenServiceMock implements TokenService {
	@Override
	public JwtToken generate(User principal, String issuer) {
		return TokenStub.One();
	}

	@Override
	public Optional<String> validate(String prefix, String token) {
		return Optional.of(AdminStub.One().getUsername());
	}
}
