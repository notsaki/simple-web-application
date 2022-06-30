package me.github.notsaki.userapplication.infrastructure.configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

public class HttpConfigurer extends AbstractHttpConfigurer<HttpConfigurer, HttpSecurity> {
	public static HttpConfigurer create() {
		return new HttpConfigurer();
	}
}
