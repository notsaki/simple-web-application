package me.github.notsaki.userapplication.entity.response;

import me.github.notsaki.userapplication.domain.entity.response.ResponseUserDto;
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
