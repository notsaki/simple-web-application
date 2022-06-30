package me.github.notsaki.userapplication.infrastructure.service;

import me.github.notsaki.userapplication.domain.model.Admin;
import me.github.notsaki.userapplication.domain.repository.AdminRepository;
import me.github.notsaki.userapplication.domain.service.AdminService;
import me.github.notsaki.userapplication.util.AppProfile;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier(AppProfile.IMPL)
@Profile(AppProfile.IMPL)
public class UserDetailsServiceImpl implements UserDetailsService {
	private final AdminRepository adminRepository;

	public UserDetailsServiceImpl(AdminRepository adminRepository) {
		this.adminRepository = adminRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		var admin = this.adminRepository
				.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException(username));

		return new User(admin.getUsername(), admin.getPassword(), List.of());
	}
}
