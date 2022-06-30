package me.github.notsaki.userapplication.infrastructure.configuration;

import me.github.notsaki.userapplication.infrastructure.model.AdminModel;
import me.github.notsaki.userapplication.domain.service.AdminService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class ApplicationConfiguration {

	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(
			AdminService adminService,
			UserDetailsService userDetailsService,
			@Value("${admin.username}") String username,
			@Value("${admin.password}") String password
	) {
		return args -> {
			try {
				userDetailsService.loadUserByUsername(username);
			} catch (UsernameNotFoundException exception) {
				adminService.save(new AdminModel(username, password));
			}
		};
	}
}
