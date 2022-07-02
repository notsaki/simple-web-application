package me.github.notsaki.userapplication.domain.data.response;

import me.github.notsaki.userapplication.domain.model.Gender;

import java.time.LocalDate;
import java.util.Optional;

public record ResponseUserDto(
		int id,
		String name,
		String surname,
		Gender gender,
		LocalDate birthdate,
		Optional<String> workAddress,
		Optional<String> homeAddress
) {
}
