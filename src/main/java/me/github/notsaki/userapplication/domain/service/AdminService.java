package me.github.notsaki.userapplication.domain.service;

import me.github.notsaki.userapplication.domain.model.Admin;
import me.github.notsaki.userapplication.domain.repository.AdminRepository;
import me.github.notsaki.userapplication.domain.util.PasswordEncoder;

public class AdminService {
	private final AdminRepository adminRepository;
	private final PasswordEncoder encoder;

	public AdminService(AdminRepository adminRepository, PasswordEncoder encoder) {
		this.adminRepository = adminRepository;
		this.encoder = encoder;
	}

	/**
	 * Save a new admin user.
	 * @param admin the admin information (username and password).
	 * @return the saved admin user.
	 */
	public Admin save(Admin admin) {
		admin.setPassword(this.encoder.encode(admin.getPassword()));
		return this.adminRepository.save(admin);
	}
}
