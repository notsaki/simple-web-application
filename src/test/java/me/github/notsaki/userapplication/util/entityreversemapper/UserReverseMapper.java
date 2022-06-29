package me.github.notsaki.userapplication.util.entityreversemapper;

import me.github.notsaki.userapplication.domain.model.HomeAddress;
import me.github.notsaki.userapplication.domain.model.User;
import me.github.notsaki.userapplication.domain.model.WorkAddress;
import me.github.notsaki.userapplication.domain.entity.receive.ReceiveUserDto;
import me.github.notsaki.userapplication.domain.entity.response.ResponseUserDto;

import java.util.List;

public class UserReverseMapper {
	public static ReceiveUserDto fromUserToReceive(User user) {
		var homeAddress = user.getHomeAddress().orElse(null);
		var workAddress = user.getWorkAddress().orElse(null);
		return new ReceiveUserDto(
				user.getName(),
				user.getSurname(),
				user.getGender(),
				user.getBirthdate(),
				workAddress != null ? workAddress.getAddress() : null,
				homeAddress != null ? homeAddress.getAddress() : null
		);
	}

	public static List<ReceiveUserDto> fromUserListToReceive(List<User> users) {
		return users
				.stream()
				.map(UserReverseMapper::fromUserToReceive)
				.toList();
	}

	public static User fromResponseToUser(ResponseUserDto user) {
		return new User(
				user.id(),
				user.name(),
				user.surname(),
				user.gender(),
				user.birthdate(),
				new WorkAddress(user.workAddress()),
				new HomeAddress(user.homeAddress())
		);
	}

	public static List<User> fromResponseListToUser(List<ResponseUserDto> users) {
		return users
				.stream()
				.map(UserReverseMapper::fromResponseToUser)
				.toList();
	}

	public static ReceiveUserDto fromResponseToReceive(ResponseUserDto user) {
		return new ReceiveUserDto(
				user.name(),
				user.surname(),
				user.gender(),
				user.birthdate(),
				user.workAddress(),
				user.homeAddress()
		);
	}

	public static List<ReceiveUserDto> fromResponseListToReceive(List<ResponseUserDto> users) {
		return users
				.stream()
				.map(UserReverseMapper::fromResponseToReceive)
				.toList();
	}
}
