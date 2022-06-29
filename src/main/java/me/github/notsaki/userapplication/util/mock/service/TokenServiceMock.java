package me.github.notsaki.userapplication.util.mock.service;

import me.github.notsaki.userapplication.domain.entity.response.JwtToken;
import me.github.notsaki.userapplication.domain.service.TokenService;
import me.github.notsaki.userapplication.util.AppProfile;
import me.github.notsaki.userapplication.util.stub.admin.AdminStub;
import me.github.notsaki.userapplication.util.stub.token.TokenStub;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@Qualifier(AppProfile.MOCK)
@Profile(AppProfile.MOCK)
public class TokenServiceMock implements TokenService {
	@Override
	public JwtToken generateToken(String username, String issuer) {
		return TokenStub.One();
	}

	@Override
	public Optional<String> validateToken(String prefix, String token) {
		return Optional.of(AdminStub.one().getUsername());
	}
}
