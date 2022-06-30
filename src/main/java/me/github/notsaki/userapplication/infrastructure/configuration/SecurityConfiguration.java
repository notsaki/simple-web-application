package me.github.notsaki.userapplication.infrastructure.configuration;

import me.github.notsaki.userapplication.infrastructure.filter.AuthenticationFilter;
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
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	private final AuthenticationConfiguration authenticationConfiguration;

	public SecurityConfiguration(AuthenticationConfiguration authenticationConfiguration) {
		this.authenticationConfiguration = authenticationConfiguration;
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
				.antMatchers("/login/**")
				.permitAll();

		http.authorizeRequests()
				.antMatchers("/user/**", "/token/**", "/invalidate/**")
				.authenticated();

		http.logout().disable();

		http.addFilterBefore(
				new AuthenticationFilter(this.authenticationManager()),
				UsernamePasswordAuthenticationFilter.class
		);

		return http.build();
	}

	@Bean
	public AuthenticationManager authenticationManager() throws Exception {
		return this.authenticationConfiguration.getAuthenticationManager();
	}
}
