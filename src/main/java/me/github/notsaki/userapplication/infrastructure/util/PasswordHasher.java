package me.github.notsaki.userapplication.infrastructure.util;

import me.github.notsaki.userapplication.domain.util.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordHasher implements org.springframework.security.crypto.password.PasswordEncoder, PasswordEncoder {
	private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

	@Override
	public String encode(String raw) {
		return this.bCryptPasswordEncoder.encode(raw);
	}

	@Override
	public boolean verify(String raw, String encoded) {
		return this.bCryptPasswordEncoder.matches(raw, encoded);
	}

	@Override
	public String encode(CharSequence rawPassword) {
		return this.encode(rawPassword.toString());
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return this.verify(rawPassword.toString(), encodedPassword);
	}
}
