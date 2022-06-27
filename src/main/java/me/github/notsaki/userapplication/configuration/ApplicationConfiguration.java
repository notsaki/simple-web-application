package me.github.notsaki.userapplication.configuration;

import me.github.notsaki.userapplication.domain.model.Admin;
import me.github.notsaki.userapplication.domain.service.AdminService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
			@Value("${admin.username}") String username,
			@Value("${admin.password}") String password
	) {
		return args -> adminService.save(new Admin(username, password));
	}
}
