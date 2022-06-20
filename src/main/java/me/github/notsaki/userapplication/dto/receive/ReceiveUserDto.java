package me.github.notsaki.userapplication.dto.receive;

import me.github.notsaki.userapplication.domain.model.Gender;
import me.github.notsaki.userapplication.domain.model.User;
import org.springframework.lang.Nullable;

import java.time.LocalDate;

public record ReceiveUserDto(
		String name,
		String surname,
		Gender gender,
		LocalDate birthdate,
		@Nullable String workAddress,
		@Nullable String homeAddress
) {
	public User toUser() {
		return new User(
				this.name,
				this.surname,
				this.gender,
				this.birthdate,
				this.workAddress,
				this.homeAddress
		);
	}
}
