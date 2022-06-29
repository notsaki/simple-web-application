package me.github.notsaki.userapplication.util.mock.service;

import me.github.notsaki.userapplication.domain.model.Admin;
import me.github.notsaki.userapplication.model.AdminModel;
import me.github.notsaki.userapplication.domain.service.AdminService;
import me.github.notsaki.userapplication.util.AppProfile;
import me.github.notsaki.userapplication.util.stub.admin.AdminStub;
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
@Qualifier(AppProfile.MOCK)
@Profile(AppProfile.MOCK)
public class AdminServiceMock implements AdminService, UserDetailsService {
	private final BCryptPasswordEncoder encoder;

	public AdminServiceMock(BCryptPasswordEncoder encoder) {
		this.encoder = encoder;
	}

	@Override
	public Admin save(Admin admin) {
		return AdminStub.one();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		var admin = AdminStub.one();
		return new User(admin.getUsername(), this.encoder.encode(admin.getPassword()), List.of());
	}
}
