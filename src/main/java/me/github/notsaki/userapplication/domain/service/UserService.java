package me.github.notsaki.userapplication.domain.service;

import me.github.notsaki.userapplication.dto.receive.ReceiveUserDto;
import me.github.notsaki.userapplication.dto.receive.ResponseUserDto;

import java.util.List;

public interface UserService {
	public ResponseUserDto save(ReceiveUserDto userDto);
	public void deleteById(int id);
	public List<ResponseUserDto> findAll();
}
