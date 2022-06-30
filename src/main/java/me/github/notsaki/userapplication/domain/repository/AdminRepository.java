package me.github.notsaki.userapplication.domain.repository;

import me.github.notsaki.userapplication.domain.model.Admin;

import java.util.Optional;

public interface AdminRepository {
	Optional<Admin> findByUsername(String username);
	Admin save(Admin admin);
}
