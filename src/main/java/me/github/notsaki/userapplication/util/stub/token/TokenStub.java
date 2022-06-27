package me.github.notsaki.userapplication.util.stub.token;

import me.github.notsaki.userapplication.domain.entity.response.JwtToken;

public class TokenStub {
	public static JwtToken One() {
		return new JwtToken("token", "refresh");
	}
}
