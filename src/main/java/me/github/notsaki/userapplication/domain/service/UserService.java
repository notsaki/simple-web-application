package me.github.notsaki.userapplication.domain.service;

import me.github.notsaki.userapplication.domain.entity.receive.ReceiveUserDto;
import me.github.notsaki.userapplication.domain.entity.response.ResponseUserDto;

import java.util.List;

public interface UserService {
	public ResponseUserDto save(ReceiveUserDto userDto);
	public void deleteById(int id);
	public List<ResponseUserDto> findAll();
}
