package me.github.notsaki.userapplication.repository;

import me.github.notsaki.userapplication.domain.repository.UserRepository;
import me.github.notsaki.userapplication.stub.UserStub;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
public class UserRepositoryImplTests {

	@Autowired
	@Qualifier("impl")
	private UserRepository userRepository;

	@Test
	public void onSaveShouldReturnTheSameUser() {
		var user = UserStub.ForSave();
		var result = this.userRepository.save(user);

		Assert.assertEquals(result, user);
	}

	@Test
	public void onSaveShouldGenerateId() {
		var user = UserStub.ForSave();
		var result = this.userRepository.save(user);

		Assert.assertTrue(result.getId().isPresent());
	}

	@Test
	public void onFindAllShouldReturnTheSameAmountUsers() {
		var users = UserStub.ListOfUsers();

		var generatedUsers = users
				.stream()
				.map(user -> this.userRepository.save(user))
				.toList();

		var result = this.userRepository.findAll();

		Assert.assertEquals(result.size(), generatedUsers.size());
	}

	@Test
	public void onFindAllShouldReturnTheSameUsersAsInserted() {
		var users = UserStub.ListOfUsers();

		var generatedUsers = users
				.stream()
				.map(user -> this.userRepository.save(user))
				.toList();

		var result = this.userRepository.findAll();

		Assert.assertEquals(result, generatedUsers);
	}

	@Test
	public void onDeleteShouldReturnOneRowAffected() {
		var users = UserStub.ListOfUsers();

		this.userRepository.save(users.get(0));
		var rowsAffected = this.userRepository.deleteById(users.get(0).getId().orElseThrow());

		Assert.assertEquals(1, rowsAffected);
	}

	@Test
	public void onDeleteUserShouldNotExist() {
		var user = UserStub.ForSave();

		var savedUser = this.userRepository.save(user);
		this.userRepository.deleteById(user.getId().orElseThrow());
		var users = this.userRepository.findAll();

		Assert.assertFalse(users.contains(savedUser));
	}
}
