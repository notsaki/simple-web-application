package me.github.notsaki.userapplication.util.modelmapper;

import me.github.notsaki.userapplication.domain.model.User;
import me.github.notsaki.userapplication.dto.receive.ReceiveUserDto;

import java.util.List;

public class UserMapper {
	public static ReceiveUserDto fromUser(User user) {
		return new ReceiveUserDto(
				user.getName(),
				user.getSurname(),
				user.getGender(),
				user.getBirthdate(),
				user.getWorkAddress(),
				user.getHomeAddress()
		);
	}

	public static List<ReceiveUserDto> fromUserList(List<User> users) {
		return users
				.stream()
				.map(UserMapper::fromUser)
				.toList();
	}
}
