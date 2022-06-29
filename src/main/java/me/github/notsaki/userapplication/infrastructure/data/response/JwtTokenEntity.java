package me.github.notsaki.userapplication.infrastructure.data.response;

import me.github.notsaki.userapplication.domain.data.response.JwtToken;

public record JwtTokenEntity(
		String access_token,
		String refresh_token
) implements JwtToken {
}
