package me.github.notsaki.userapplication.util.mock.service;

import me.github.notsaki.userapplication.domain.entity.response.UserListItemDto;
import me.github.notsaki.userapplication.domain.model.User;
import me.github.notsaki.userapplication.domain.entity.receive.ReceiveUserDto;
import me.github.notsaki.userapplication.domain.model.AppProfile;
import me.github.notsaki.userapplication.domain.service.UserService;
import me.github.notsaki.userapplication.domain.entity.response.ResponseUserDto;
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
	public void deleteById(int id) {
	}

	@Override
	public List<UserListItemDto> findAll() {
		return UserStub.List()
				.stream()
				.map(User::toFullName)
				.toList();
	}

	@Override
	public ResponseUserDto findById(int id) {
		return UserStub.One().toResponse();
	}
}
