package me.github.notsaki.userapplication.infrastructure.configuration;

import me.github.notsaki.userapplication.domain.service.AdminService;
import me.github.notsaki.userapplication.domain.util.PasswordEncoder;
import me.github.notsaki.userapplication.infrastructure.model.AdminModel;
import me.github.notsaki.userapplication.infrastructure.util.PasswordHasher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

	@Bean
	CommandLineRunner run(
			AdminService adminService,
			@Value("${admin.username}") String username,
			@Value("${admin.password}") String password
	) {
		return args -> adminService
				.findByUsername(username)
				.orElseGet(() -> adminService.save(new AdminModel(username, password)));
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new PasswordHasher();
	}
}
