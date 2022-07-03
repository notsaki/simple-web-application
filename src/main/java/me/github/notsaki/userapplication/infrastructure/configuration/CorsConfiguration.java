package me.github.notsaki.userapplication.infrastructure.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Optional;

@Configuration
public class CorsConfiguration {
	private final Optional<String> allowedOrigin;

	public CorsConfiguration(@Value("${header.allowed.origin:#{null}}") @Nullable String allowedOrigin) {
		this.allowedOrigin = Optional.ofNullable(allowedOrigin);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				allowedOrigin
						.ifPresent(s -> registry.addMapping("/**")
								.allowedOrigins(s)
								.allowCredentials(true)
								.allowedMethods("POST", "GET", "PATCH", "DELETE", "OPTIONS")
								.allowedHeaders("*")
								.maxAge(1728000));
			}
		};
	}
}