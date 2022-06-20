package me.github.notsaki.userapplication.util.mock.repository;

import me.github.notsaki.userapplication.domain.model.AppProfile;
import me.github.notsaki.userapplication.domain.model.User;
import me.github.notsaki.userapplication.domain.repository.UserRepository;
import me.github.notsaki.userapplication.util.stub.user.UserStub;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Profile(AppProfile.MOCK)
@Qualifier(AppProfile.MOCK)
public class UserRepositoryMock implements UserRepository {
	@Override
	public User save(User user) {
		user.setId(1);
		return user;
	}

	@Override
	public int deleteById(int id) {
		return 1;
	}

	@Override
	public List<User> findAll() {
		return UserStub.List();
	}
}
