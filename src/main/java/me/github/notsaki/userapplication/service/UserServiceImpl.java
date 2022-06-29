package me.github.notsaki.userapplication.service;

import me.github.notsaki.userapplication.domain.entity.receive.ReceiveUserDto;
import me.github.notsaki.userapplication.domain.entity.response.ResponseUserDto;
import me.github.notsaki.userapplication.domain.entity.response.UserListItemDto;
import me.github.notsaki.userapplication.domain.model.User;
import me.github.notsaki.userapplication.util.AppProfile;
import me.github.notsaki.userapplication.domain.repository.UserRepository;
import me.github.notsaki.userapplication.domain.service.UserService;
import me.github.notsaki.userapplication.entity.response.ResponseUserDtoEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Profile(AppProfile.IMPL)
@Qualifier(AppProfile.IMPL)
@Primary
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public ResponseUserDto save(ReceiveUserDto userDto) {
		return userRepository.save(userDto.toUser()).toResponse();
	}

	@Override
	public boolean deleteById(int id) {
		return this.userRepository.deleteById(id);
	}

	@Override
	public Optional<ResponseUserDto> updateById(int id, ReceiveUserDto user) {
		var newUser = user.toUser();
		newUser.setId(id);
		return this.userRepository
				.update(newUser)
				.map(User::toResponse);
	}

	@Override
	public List<UserListItemDto> findAll() {
		return this.userRepository.findAll();
	}

	@Override
	public Optional<ResponseUserDto> findById(int id) {
		return this.userRepository
				.findById(id)
				.map(User::toResponse);
	}
}
