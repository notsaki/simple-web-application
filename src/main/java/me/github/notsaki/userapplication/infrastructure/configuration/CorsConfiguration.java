//package me.github.notsaki.userapplication.infrastructure.configuration;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class CorsConfiguration {
//	private final String allowedOrigin;
//
//	public CorsConfiguration(@Value("${header.allowed.origin}") String allowedOrigin) {
//		this.allowedOrigin = allowedOrigin;
//	}
//
//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//		return new WebMvcConfigurer() {
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				if(allowedOrigin != null) {
//					registry.addMapping("/**")
//							.allowedOrigins(allowedOrigin)
//							.allowCredentials(true)
//							.allowedMethods("POST", "GET", "PATCH", "DELETE", "OPTIONS")
//							.allowedHeaders("*")
//							.maxAge(1728000);
//				}
//			}
//		};
//	}
//}