package me.github.notsaki.userapplication.util.mock.service;

import me.github.notsaki.userapplication.domain.model.User;
import me.github.notsaki.userapplication.dto.receive.ReceiveUserDto;
import me.github.notsaki.userapplication.domain.model.AppProfile;
import me.github.notsaki.userapplication.domain.service.UserService;
import me.github.notsaki.userapplication.dto.receive.ResponseUserDto;
import me.github.notsaki.userapplication.util.stub.user.UserStub;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile(AppProfile.MOCK)
@Qualifier(AppProfile.MOCK)
public class UserServiceMock implements UserService {
	@Override
	public ResponseUserDto save(ReceiveUserDto userDto) {
		return UserStub.One().toResponse();
	}

	@Override
	public int deleteById(int id) {
		return 1;
	}

	@Override
	public List<ResponseUserDto> findAll() {
		return UserStub.List()
				.stream()
				.map(User::toResponse)
				.toList();
	}
}
