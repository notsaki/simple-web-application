package me.github.notsaki.userapplication.repository.mock;

import me.github.notsaki.userapplication.domain.model.AppProfile;
import me.github.notsaki.userapplication.domain.model.Gender;
import me.github.notsaki.userapplication.domain.model.User;
import me.github.notsaki.userapplication.domain.repository.UserRepository;
import me.github.notsaki.userapplication.stub.UserStub;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Qualifier(AppProfile.MOCK)
public class UserRepositoryMock implements UserRepository {
	@Override
	public User save(User user) {
		return UserStub.ForSave();
	}

	@Override
	public int deleteById(int id) {
		return 1;
	}

	@Override
	public List<User> findAll() {
		return UserStub.ListOfUsers();
	}
}
