package me.github.notsaki.userapplication.infrastructure.configuration;

import me.github.notsaki.userapplication.domain.repository.AdminRepository;
import me.github.notsaki.userapplication.domain.repository.UserRepository;
import me.github.notsaki.userapplication.domain.service.AdminService;
import me.github.notsaki.userapplication.domain.service.SecurityService;
import me.github.notsaki.userapplication.domain.service.UserService;
import me.github.notsaki.userapplication.domain.util.PasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {
	private final AdminRepository adminRepository;
	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;

	public ServiceConfiguration(
			AdminRepository adminRepository,
			PasswordEncoder passwordEncoder,
			UserRepository userRepository
	) {
		this.adminRepository = adminRepository;
		this.passwordEncoder = passwordEncoder;
		this.userRepository = userRepository;
	}

	@Bean
	public AdminService adminService() {
		return new AdminService(this.adminRepository, this.passwordEncoder);
	}

	@Bean
	public UserService userService() {
		return new UserService(this.userRepository);
	}

	@Bean
	public SecurityService securityService() {
		return new SecurityService(this.passwordEncoder, this.adminService());
	}
}
