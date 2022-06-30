package me.github.notsaki.userapplication.infrastructure.data.response;

import me.github.notsaki.userapplication.domain.data.response.ResponseUserDto;
import me.github.notsaki.userapplication.domain.model.Gender;

import java.time.LocalDate;

public record ResponseUserDtoEntity(
		int id,
		String name,
		String surname,
		Gender gender,
		LocalDate birthdate,
		String workAddress,
		String homeAddress
) implements ResponseUserDto {
}
