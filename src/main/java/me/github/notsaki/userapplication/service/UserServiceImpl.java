package me.github.notsaki.userapplication.service;

import me.github.notsaki.userapplication.dto.receive.ReceiveUserDto;
import me.github.notsaki.userapplication.domain.model.AppProfile;
import me.github.notsaki.userapplication.domain.model.User;
import me.github.notsaki.userapplication.domain.repository.UserRepository;
import me.github.notsaki.userapplication.domain.service.UserService;
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
	public User save(ReceiveUserDto userDto) {
		var createdUser = userRepository.save(userDto.toUser());
		return createdUser;
	}

	@Override
	public int deleteById(int id) {
		return this.userRepository.deleteById(id);
	}

	@Override
	public List<User> findAll() {
		return this.userRepository.findAll();
	}
}
