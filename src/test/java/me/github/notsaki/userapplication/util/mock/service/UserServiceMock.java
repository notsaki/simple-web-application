package me.github.notsaki.userapplication.util.mock.service;

import me.github.notsaki.userapplication.domain.model.User;
import me.github.notsaki.userapplication.dto.receive.ReceiveUserDto;
import me.github.notsaki.userapplication.domain.model.AppProfile;
import me.github.notsaki.userapplication.domain.service.UserService;
import me.github.notsaki.userapplication.util.stub.user.UserStub;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier(AppProfile.MOCK)
public class UserServiceMock implements UserService {
	@Override
	public User save(ReceiveUserDto userDto) {
		return UserStub.One();
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