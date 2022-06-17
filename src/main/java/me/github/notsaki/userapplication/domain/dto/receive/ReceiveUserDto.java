package me.github.notsaki.userapplication.domain.dto.receive;

import me.github.notsaki.userapplication.domain.model.Gender;
import me.github.notsaki.userapplication.domain.model.User;
import org.springframework.lang.Nullable;

import java.time.LocalDate;
import java.util.Optional;

public record ReceiveUserDto(
		String name,
		String surname,
		Gender gender,
		LocalDate birthdate,
		@Nullable String workAddress,
		@Nullable String homeAddress
) {
}
