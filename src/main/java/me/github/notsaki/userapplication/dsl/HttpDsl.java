package me.github.notsaki.userapplication.dsl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

public class HttpDsl extends AbstractHttpConfigurer<HttpDsl, HttpSecurity> {
	@Override
	public void configure(HttpSecurity http) throws Exception {
		AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
	}

	public static HttpDsl create() {
		return new HttpDsl();
	}
}
