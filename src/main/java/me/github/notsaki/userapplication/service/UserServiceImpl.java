package me.github.notsaki.userapplication.service;

import me.github.notsaki.userapplication.domain.dto.receive.ReceiveUserDto;
import me.github.notsaki.userapplication.domain.dto.response.ResponseUserDto;
import me.github.notsaki.userapplication.domain.model.AppProfile;
import me.github.notsaki.userapplication.domain.model.User;
import me.github.notsaki.userapplication.domain.repository.UserRepository;
import me.github.notsaki.userapplication.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier(AppProfile.IMPL)
@Primary
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public ResponseUserDto save(ReceiveUserDto userDto) {
		var createdUser = userRepository.save(userDto.toUser());
		return createdUser.toResponse();
	}

	@Override
	public int deleteById(int id) {
		return this.userRepository.deleteById(id);
	}

	@Override
	public List<ResponseUserDto> findAll() {
		var users = this.userRepository.findAll();
		return users
				.stream()
				.map(User::toResponse)
				.toList();
	}
}
