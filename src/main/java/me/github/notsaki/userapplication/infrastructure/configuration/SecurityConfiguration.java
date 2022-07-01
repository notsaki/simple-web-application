package me.github.notsaki.userapplication.infrastructure.configuration;

import me.github.notsaki.userapplication.domain.service.AdminService;
import me.github.notsaki.userapplication.domain.service.SecurityService;
import me.github.notsaki.userapplication.domain.util.PasswordEncoder;
import me.github.notsaki.userapplication.infrastructure.filter.AuthenticationFilter;
import me.github.notsaki.userapplication.util.Routes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	private final SecurityService securityService;

	public SecurityConfiguration(SecurityService securityService) {
		this.securityService = securityService;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.apply(HttpConfigurer.create());

		// App has to support SPAs.
		http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS);

		http.authorizeRequests()
				.antMatchers(HttpMethod.OPTIONS, "/**")
				.permitAll();

		http.authorizeRequests()
				.antMatchers(Routes.login + "/**")
				.permitAll();

		http.authorizeRequests()
				.antMatchers(Routes.user + "/**", Routes.token + "/**", Routes.logout + "/**")
				.authenticated();

		http.logout().disable();

		http.addFilterBefore(
				new AuthenticationFilter(this.securityService),
				UsernamePasswordAuthenticationFilter.class
		);

		return http.build();
	}
}
