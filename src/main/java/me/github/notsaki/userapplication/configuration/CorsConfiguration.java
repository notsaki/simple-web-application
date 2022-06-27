package me.github.notsaki.userapplication.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration {
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/user/**")
						.allowCredentials(true)
						.allowedOrigins("http://localhost:3000/")
						.allowedMethods("POST", "GET", "DELETE", "OPTIONS");

				registry
						.addMapping("/token")
						.allowedOrigins("http://localhost:3000/")
						.allowedMethods("POST");

				registry
						.addMapping("/login")
						.allowedOrigins("http://localhost:3000/")
						.allowedMethods("POST");
			}
		};
	}
}