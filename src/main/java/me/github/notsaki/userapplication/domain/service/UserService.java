package me.github.notsaki.userapplication.domain.service;

import me.github.notsaki.userapplication.domain.entity.receive.ReceiveUserDto;
import me.github.notsaki.userapplication.domain.entity.response.ResponseUserDto;
import me.github.notsaki.userapplication.domain.entity.response.UserListItemDto;
import me.github.notsaki.userapplication.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
	public ResponseUserDto save(ReceiveUserDto userDto);
	public boolean deleteById(int id);
	Optional<ResponseUserDto> updateById(int id, ReceiveUserDto user);
	public List<UserListItemDto> findAll();
	public Optional<ResponseUserDto> findById(int id);
}
