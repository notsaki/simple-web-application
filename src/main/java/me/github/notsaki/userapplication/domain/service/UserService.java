package me.github.notsaki.userapplication.domain.service;

import me.github.notsaki.userapplication.domain.model.User;
import me.github.notsaki.userapplication.dto.receive.ReceiveUserDto;

import java.util.List;

public interface UserService {
	public User save(ReceiveUserDto userDto);
	public int deleteById(int id);
	public List<User> findAll();
}
