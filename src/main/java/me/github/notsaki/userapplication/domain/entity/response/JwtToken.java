package me.github.notsaki.userapplication.domain.entity.response;

public record JwtToken(
		String access_token,
		String refresh_token
) {
}
