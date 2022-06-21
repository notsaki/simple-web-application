package me.github.notsaki.userapplication.integration.usercontroller;

import me.github.notsaki.userapplication.controller.UserController;
import me.github.notsaki.userapplication.domain.model.AppProfile;
import me.github.notsaki.userapplication.domain.model.User;
import me.github.notsaki.userapplication.domain.repository.UserRepository;
import me.github.notsaki.userapplication.domain.service.UserService;
import me.github.notsaki.userapplication.dto.receive.ReceiveUserDto;
import me.github.notsaki.userapplication.dto.receive.ResponseUserDto;
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

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
public class UserControllerOnCreateTests {

	@Autowired
	@Qualifier(AppProfile.IMPL)
	private UserRepository userRepository;

	@Autowired
	@Qualifier(AppProfile.IMPL)
	private UserService userService;

	@Autowired
	private UserController userController;

	private final ReceiveUserDto receiveUser = ReceiveUserStub.One();
	private ResponseUserDto user;

	@Before
	public void createUser() {
		this.user = userController.create(this.receiveUser);
	}

	@Test
	public void shouldReturnTheSameUser() {
		var receiveUser = this.receiveUser.toUser();
		receiveUser.setId(this.user.id());
		Assert.assertEquals(this.user, receiveUser.toResponse());
	}
}
