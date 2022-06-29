package me.github.notsaki.userapplication.infrastructure.repository;

import me.github.notsaki.userapplication.domain.model.Admin;
import me.github.notsaki.userapplication.domain.repository.AdminRepository;
import me.github.notsaki.userapplication.util.AppProfile;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Qualifier(AppProfile.IMPL)
@Profile(AppProfile.IMPL)
public class AdminRepositoryImpl implements AdminRepository {

	private final EntityManager entityManager;

	public AdminRepositoryImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Optional<Admin> findByUsername(String username) {
		return this.entityManager
				.createQuery("SELECT a FROM AdminModel a WHERE a.username = :username", Admin.class)
				.setParameter("username", username)
				.getResultList()
				.stream()
				.findFirst();
	}

	@Override
	@Transactional
	public Admin save(Admin admin) {
		this.entityManager.persist(admin);
		return admin;
	}
}
