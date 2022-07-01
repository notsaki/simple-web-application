package me.github.notsaki.userapplication.repository;

import me.github.notsaki.userapplication.domain.data.receive.ReceiveUserDto;
import me.github.notsaki.userapplication.domain.data.response.UserListItemDto;
import me.github.notsaki.userapplication.domain.repository.UserRepository;
import me.github.notsaki.userapplication.util.AppProfile;
import me.github.notsaki.userapplication.util.stub.user.ReceiveUserStub;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
public class UserRepositoryImplTests {

	@Autowired
	@Qualifier(AppProfile.IMPL)
	private UserRepository userRepository;

	@Test
	public void onSaveShouldReturnTheSameUser() {
		var user = ReceiveUserStub.one().toUser();
		var result = this.userRepository.save(user);

		Assert.assertEquals(result, user);
	}

	@Test
	public void onFindAllShouldReturnTheSameAmountUsers() {
		var users = ReceiveUserStub
				.list()
				.stream()
				.map(ReceiveUserDto::toUser)
				.toList();

		var generatedUsers = users
				.stream()
				.map(user -> this.userRepository.save(user))
				.toList();

		var result = this.userRepository.findAll();

		Assert.assertEquals(result.size(), generatedUsers.size());
	}

	@Test
	public void onFindAllShouldReturnTheSameUsers() {
		var users = ReceiveUserStub
				.list()
				.stream()
				.map(ReceiveUserDto::toUser)
				.toList();

		var created = users
				.stream()
				.map(user -> this.userRepository.save(user))
				.toList();

		var saved = users
				.stream()
				.map(user -> new UserListItemDto(user.getId(), user.getName(), user.getSurname()))
				.toList();

		var result = this.userRepository.findAll();

		Assert.assertEquals(saved, result);
	}

	@Test
	public void onDeleteShouldReturnTrue() {
		var user = ReceiveUserStub.one().toUser();
		this.userRepository.save(user);
		var result = this.userRepository.deleteById(user.getId());
		Assert.assertTrue(result);
	}

	@Test
	public void onDeleteNonExistentShouldReturnFalse() {
		var result = this.userRepository.deleteById(1);
		Assert.assertFalse(result);
	}

	@Test
	public void onDeleteUserShouldNotExist() {
		var user = ReceiveUserStub.one().toUser();

		var savedUser = this.userRepository.save(user);
		this.userRepository.deleteById(savedUser.getId());
		var users = this.userRepository.findAll();

		Assert.assertFalse(users.contains(savedUser));
	}
}
