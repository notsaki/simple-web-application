package me.github.notsaki.userapplication.integration.usercontroller;

import me.github.notsaki.userapplication.controller.UserController;
import me.github.notsaki.userapplication.util.AppProfile;
import me.github.notsaki.userapplication.domain.repository.UserRepository;
import me.github.notsaki.userapplication.domain.service.UserService;
import me.github.notsaki.userapplication.domain.entity.receive.ReceiveUserDto;
import me.github.notsaki.userapplication.util.modelmapper.UserMapper;
import me.github.notsaki.userapplication.util.stub.user.ReceiveUserStub;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
public class UserControllerOnFindAllTests {
	@Autowired
	@Qualifier(AppProfile.IMPL)
	private UserRepository userRepository;

	@Autowired
	@Qualifier(AppProfile.IMPL)
	private UserService userService;

	@Autowired
	private UserController userController;

	private final List<ReceiveUserDto> receiveUsers = ReceiveUserStub.List();
	private List<ReceiveUserDto> returnedUsersAsReceived;

	@Before
	public void init() {
		var users = this.receiveUsers
				.stream()
				.map(user -> this.userController.create(user))
				.toList();

		this.returnedUsersAsReceived = UserMapper.fromResponseListToReceive(users);
	}

	@Test
	public void shouldReturnTheSameUsers() {
		Assert.assertEquals(this.receiveUsers, this.returnedUsersAsReceived);
	}
}
