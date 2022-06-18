package me.github.notsaki.userapplication.service;

import me.github.notsaki.userapplication.domain.model.AppProfile;
import me.github.notsaki.userapplication.domain.repository.UserRepository;
import me.github.notsaki.userapplication.domain.service.UserService;
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
public class UserServiceImplTests {

	@Autowired
	@Qualifier(AppProfile.MOCK)
	private UserRepository userRepository;

	@Autowired
	@Qualifier(AppProfile.IMPL)
	private UserService userService;

	@Test
	public void onSaveShouldReturnTheSameUser() {
		var user = ReceiveUserStub.One();
		var result = this.userService.save(user);

		var receive = user.toUser();
		receive.setId(result.id());
		Assert.assertEquals(result, receive.toResponse());
	}
}
