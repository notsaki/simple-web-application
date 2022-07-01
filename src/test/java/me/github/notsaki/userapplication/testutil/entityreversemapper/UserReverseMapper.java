package me.github.notsaki.userapplication.testutil.entityreversemapper;

import me.github.notsaki.userapplication.domain.data.receive.ReceiveUserDto;
import me.github.notsaki.userapplication.domain.data.response.ResponseUserDto;
import me.github.notsaki.userapplication.domain.model.User;
import me.github.notsaki.userapplication.infrastructure.model.HomeAddressModel;
import me.github.notsaki.userapplication.infrastructure.model.UserModel;
import me.github.notsaki.userapplication.infrastructure.model.WorkAddressModel;
import me.github.notsaki.userapplication.infrastructure.data.receive.ReceiveUserDtoEntity;

import java.util.List;

public class UserReverseMapper {
	public static ReceiveUserDto fromUserToReceive(User user) {
		var homeAddress = user.getHomeAddress().orElse(null);
		var workAddress = user.getWorkAddress().orElse(null);
		return new ReceiveUserDtoEntity(
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
		return new UserModel(
				user.id(),
				user.name(),
				user.surname(),
				user.gender(),
				user.birthdate(),
				new WorkAddressModel(user.workAddress()),
				new HomeAddressModel(user.homeAddress())
		);
	}

	public static List<User> fromResponseListToUser(List<ResponseUserDto> users) {
		return users
				.stream()
				.map(UserReverseMapper::fromResponseToUser)
				.toList();
	}

	public static ReceiveUserDto fromResponseToReceive(ResponseUserDto user) {
		return new ReceiveUserDtoEntity(
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
