package me.github.notsaki.userapplication.infrastructure.data.receive;

import me.github.notsaki.userapplication.domain.data.receive.Credentials;

public record CredentialsEntity(
		String username,
		String password
) implements Credentials {
}
