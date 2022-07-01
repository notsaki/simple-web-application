package me.github.notsaki.userapplication.domain.repository;

import me.github.notsaki.userapplication.domain.model.Admin;

import java.util.Optional;

public interface AdminRepository {
	/**
	 * Find a user matching the username.
	 * @param username The username to match.
	 * @return An Admin entity if a match was found or empty otherwise.
	 */
	Optional<Admin> findByUsername(String username);

	/**
	 * Save a new admin entity.
	 * @param admin The entity to save.
	 * @return The saved entity.
	 */
	Admin save(Admin admin);
}
