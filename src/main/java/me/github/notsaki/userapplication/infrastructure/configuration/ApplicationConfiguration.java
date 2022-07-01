package me.github.notsaki.userapplication.infrastructure.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import me.github.notsaki.userapplication.domain.service.AdminService;
import me.github.notsaki.userapplication.infrastructure.model.AdminModel;
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
	public ObjectMapper objectMapper() {
		var mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.registerModule(new Jdk8Module());
		return mapper;
	}
}
