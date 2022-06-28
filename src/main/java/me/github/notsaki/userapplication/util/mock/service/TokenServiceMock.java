package me.github.notsaki.userapplication.util.mock.service;

import me.github.notsaki.userapplication.domain.entity.response.JwtToken;
import me.github.notsaki.userapplication.domain.service.TokenService;
import me.github.notsaki.userapplication.util.stub.admin.AdminStub;
import me.github.notsaki.userapplication.util.stub.token.TokenStub;

import java.util.Optional;

public class TokenServiceMock implements TokenService {
	@Override
	public JwtToken generateToken(String username, String issuer) {
		return TokenStub.One();
	}

	@Override
	public Optional<String> validateToken(String prefix, String token) {
		return Optional.of(AdminStub.One().getUsername());
	}
}
