package me.github.notsaki.userapplication.util.mock.service;

import me.github.notsaki.userapplication.domain.data.receive.ReceiveUserDto;
import me.github.notsaki.userapplication.domain.data.response.ResponseUserDto;
import me.github.notsaki.userapplication.domain.data.response.UserListItemDto;
import me.github.notsaki.userapplication.domain.model.User;
import me.github.notsaki.userapplication.util.AppProfile;
import me.github.notsaki.userapplication.domain.service.UserService;
import me.github.notsaki.userapplication.util.stub.user.UserStub;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Profile(AppProfile.MOCK)
@Qualifier(AppProfile.MOCK)
public class UserServiceMock implements UserService {
	@Override
	public ResponseUserDto save(ReceiveUserDto userDto) {
		return UserStub.One().toResponse();
	}

	@Override
	public boolean deleteById(int id) {
		return true;
	}

	@Override
	public Optional<ResponseUserDto> updateById(int id, ReceiveUserDto user) {
		return Optional.of(UserStub.One()).map(User::toResponse);
	}

	@Override
	public List<UserListItemDto> findAll() {
		return UserStub.list()
				.stream()
				.map(User::toFullName)
				.toList();
	}

	@Override
	public Optional<ResponseUserDto> findById(int id) {
		return Optional.of(UserStub.One().toResponse());
	}
}
