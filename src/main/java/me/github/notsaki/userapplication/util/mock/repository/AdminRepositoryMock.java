package me.github.notsaki.userapplication.util.mock.repository;

import me.github.notsaki.userapplication.domain.model.Admin;
import me.github.notsaki.userapplication.domain.repository.AdminRepository;
import me.github.notsaki.userapplication.util.AppProfile;
import me.github.notsaki.userapplication.util.stub.admin.AdminStub;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Qualifier(AppProfile.MOCK)
@Profile(AppProfile.MOCK)
public class AdminRepositoryMock implements AdminRepository {
	@Override
	public Optional<Admin> findByUsername(String username) {
		return Optional.of(AdminStub.one());
	}

	@Override
	public Admin save(Admin admin) {
		return AdminStub.one();
	}
}
