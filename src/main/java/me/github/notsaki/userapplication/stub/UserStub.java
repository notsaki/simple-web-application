package me.github.notsaki.userapplication.stub;

import me.github.notsaki.userapplication.domain.dto.response.ResponseUserDto;
import me.github.notsaki.userapplication.domain.model.Gender;
import me.github.notsaki.userapplication.domain.model.User;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.*;

public class UserStub {
	public static User ForSave() {
		return new User(
				"Anna",
				"Dimitriadou",
				Gender.FEMALE,
				LocalDate.of(1997, Calendar.AUGUST, 3),
				Optional.of("Andreou Dimitriou 3, 67100, Xanthi"),
				Optional.of("Michail Karaoli 33, 67100, Xanthi")
		);
	}

	public static User CreatedUser() {
		var user = ForSave();
		user.setId(1);
		return user;
	}

	public static List<User> ListOfUsers() {
		return new ArrayList(
				Arrays.asList(
						new User(
								1,
								"Pantelis",
								"Anastasiadis",
								Gender.MALE,
								LocalDate.of(1996, Calendar.JULY, 1),
								Optional.of("Skra 23, 67100, Xanthi"),
								Optional.empty()
						),
						new User(
								2,
								"Anna",
								"Palaiokosta",
								Gender.FEMALE,
								LocalDate.of(1998, Calendar.MARCH, 15)
						),
						new User(
								3,
								"Panagiotis",
								"Sardatselis",
								Gender.MALE,
								LocalDate.of(1997, Calendar.APRIL, 12),
								Optional.of("28 of October, 67100, Xanthi"),
								Optional.of("40 Ekklision, 67100, Xanthi")
						)
				)
		);
	}

	public static ResponseUserDto ForResponse() {
		return new ModelMapper().map(CreatedUser(), ResponseUserDto.class);
	}

	public static List<ResponseUserDto> ListForResponse() {
		var modelMapper = new ModelMapper();

		return ListOfUsers()
				.stream()
				.map(user -> modelMapper.map(user, ResponseUserDto.class))
				.toList();
	}
}
