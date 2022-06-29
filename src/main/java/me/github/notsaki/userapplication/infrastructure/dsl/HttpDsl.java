package me.github.notsaki.userapplication.infrastructure.dsl;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

public class HttpDsl extends AbstractHttpConfigurer<HttpDsl, HttpSecurity> {
	public static HttpDsl create() {
		return new HttpDsl();
	}
}
