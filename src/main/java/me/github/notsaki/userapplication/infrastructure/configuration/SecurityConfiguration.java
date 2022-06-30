package me.github.notsaki.userapplication.infrastructure.configuration;

import me.github.notsaki.userapplication.domain.service.TokenService;
import me.github.notsaki.userapplication.infrastructure.filter.AuthenticationFilter;
import me.github.notsaki.userapplication.infrastructure.filter.AuthorizationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	private final TokenService tokenService;
	private final AuthenticationConfiguration authenticationConfiguration;

	public SecurityConfiguration(
			TokenService tokenService,
			AuthenticationConfiguration authenticationConfiguration
	) {
		this.tokenService = tokenService;
		this.authenticationConfiguration = authenticationConfiguration;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.apply(HttpConfigurer.create());

		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.authorizeRequests()
				.antMatchers(HttpMethod.OPTIONS, "/**")
				.permitAll();

		http.authorizeRequests()
				.antMatchers("/login/**", "/token/**")
				.permitAll();

		http.authorizeRequests()
				.antMatchers("/user/**")
				.authenticated();

		http.addFilterBefore(
				new AuthenticationFilter(this.authenticationManager()),
				UsernamePasswordAuthenticationFilter.class
		);

		http.addFilterBefore(
				new AuthorizationFilter(this.tokenService),
				UsernamePasswordAuthenticationFilter.class
		);

		return http.build();
	}

	@Bean
	AuthenticationManager authenticationManager() throws Exception {
		return this.authenticationConfiguration.getAuthenticationManager();
	}
}
