package me.github.notsaki.userapplication.service;

import me.github.notsaki.userapplication.dto.receive.ReceiveUserDto;
import me.github.notsaki.userapplication.domain.model.AppProfile;
import me.github.notsaki.userapplication.domain.model.User;
import me.github.notsaki.userapplication.domain.repository.UserRepository;
import me.github.notsaki.userapplication.domain.service.UserService;
import me.github.notsaki.userapplication.dto.receive.ResponseUserDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

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
	public int deleteById(int id) {
		return this.userRepository.deleteById(id);
	}

	@Override
	public List<ResponseUserDto> findAll() {
		return this.userRepository.findAll()
				.stream()
				.map(User::toResponse)
				.toList();
	}
}
