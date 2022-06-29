package me.github.notsaki.userapplication.domain.service;

import me.github.notsaki.userapplication.domain.model.Admin;
import me.github.notsaki.userapplication.model.AdminModel;

public interface AdminService {
	/**
	 * Save a new admin user.
	 * @param admin the admin information (username and password).
	 * @return the saved admin user.
	 */
	Admin save(Admin admin);
}
