package me.github.notsaki.userapplication.dto.response;

import me.github.notsaki.userapplication.domain.model.Gender;
import org.springframework.lang.Nullable;

import java.time.LocalDate;

public record ResponseUserDto(
		int id,
		String name,
		String surname,
		Gender gender,
		LocalDate birthdate,
		@Nullable
		String workAddress,
		@Nullable
		String homeAddress
) {
}
