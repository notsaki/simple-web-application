package me.github.notsaki.userapplication.entity.receive;

import me.github.notsaki.userapplication.domain.entity.receive.Credentials;

public record CredentialsEntity(
		String username,
		String password
) implements Credentials {
}
