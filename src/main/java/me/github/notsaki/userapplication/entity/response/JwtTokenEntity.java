package me.github.notsaki.userapplication.entity.response;

import me.github.notsaki.userapplication.domain.entity.response.JwtToken;

public record JwtTokenEntity(
		String access_token,
		String refresh_token
) implements JwtToken {
}
