package me.github.notsaki.userapplication.domain.service;

import me.github.notsaki.userapplication.domain.dto.receive.ReceiveUserDto;
import me.github.notsaki.userapplication.domain.dto.response.ResponseUserDto;

import java.util.List;

public interface UserService {
	public ResponseUserDto save(ReceiveUserDto userDto);
	public int deleteById(int id);
	public List<ResponseUserDto> findAll();
}
