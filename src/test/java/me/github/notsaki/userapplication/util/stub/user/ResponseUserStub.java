package me.github.notsaki.userapplication.util.stub.user;

import me.github.notsaki.userapplication.dto.response.ResponseUserDto;
import me.github.notsaki.userapplication.domain.model.User;

import java.util.List;

public class ResponseUserStub {
	public static ResponseUserDto One() {
		return UserStub.One().toResponse();
	}

	public static List<ResponseUserDto> List() {
		return UserStub.List()
				.stream()
				.map(User::toResponse)
				.toList();
	}
}
