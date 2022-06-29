package me.github.notsaki.userapplication.entity.receive;

import me.github.notsaki.userapplication.domain.entity.receive.RefreshToken;

public record RefreshTokenEntity(
		String refreshToken
) implements RefreshToken {
}
