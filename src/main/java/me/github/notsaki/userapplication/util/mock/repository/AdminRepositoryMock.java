package me.github.notsaki.userapplication.util.mock.repository;

import me.github.notsaki.userapplication.domain.model.Admin;
import me.github.notsaki.userapplication.domain.repository.AdminRepository;
import me.github.notsaki.userapplication.util.stub.admin.AdminStub;

import java.util.Optional;

public class AdminRepositoryMock implements AdminRepository {
	@Override
	public Optional<Admin> findByUsername(String username) {
		return Optional.of(AdminStub.One());
	}

	@Override
	public Admin save(Admin admin) {
		return AdminStub.One();
	}
}
