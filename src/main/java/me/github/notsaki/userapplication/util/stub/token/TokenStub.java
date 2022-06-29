package me.github.notsaki.userapplication.util.stub.token;

import me.github.notsaki.userapplication.infrastructure.data.response.JwtTokenEntity;

public class TokenStub {
	public static JwtTokenEntity One() {
		return new JwtTokenEntity("token", "refresh");
	}
}
