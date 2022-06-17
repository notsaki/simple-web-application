package me.github.notsaki.userapplication.service;

import me.github.notsaki.userapplication.domain.dto.receive.ReceiveUserDto;
import me.github.notsaki.userapplication.domain.dto.response.ResponseUserDto;
import me.github.notsaki.userapplication.domain.model.AppProfile;
import me.github.notsaki.userapplication.domain.model.User;
import me.github.notsaki.userapplication.domain.repository.UserRepository;
import me.github.notsaki.userapplication.domain.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier(AppProfile.IMPL)
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public ResponseUserDto save(ReceiveUserDto userDto) {
		var user = new ModelMapper().map(userDto, User.class);
		var createdUser = userRepository.save(user);

		return new ModelMapper().map(createdUser, ResponseUserDto.class);
	}

	@Override
	public void deleteById(int id) {
		this.userRepository.deleteById(id);
	}

	@Override
	public List<ResponseUserDto> findAll() {
		var modelMapper = new ModelMapper();

		return this.userRepository
				.findAll()
				.stream()
				.map(user -> modelMapper.map(user, ResponseUserDto.class))
				.toList();
	}
}
