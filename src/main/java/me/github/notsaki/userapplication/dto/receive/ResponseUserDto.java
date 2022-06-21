package me.github.notsaki.userapplication.dto.receive;

import me.github.notsaki.userapplication.domain.model.Gender;
import me.github.notsaki.userapplication.domain.model.HomeAddress;
import me.github.notsaki.userapplication.domain.model.User;
import me.github.notsaki.userapplication.domain.model.WorkAddress;

import java.time.LocalDate;

public record ResponseUserDto(
		int id,
		String name,
		String surname,
		Gender gender,
		LocalDate birthdate,
		String workAddress,
		String homeAddress
) {
}
