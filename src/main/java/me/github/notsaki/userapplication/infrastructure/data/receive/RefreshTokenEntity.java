package me.github.notsaki.userapplication.infrastructure.data.receive;

import me.github.notsaki.userapplication.domain.data.receive.RefreshToken;

public record RefreshTokenEntity(
		String refreshToken
) implements RefreshToken {
}
