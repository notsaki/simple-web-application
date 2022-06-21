package me.github.notsaki.userapplication.dto.receive;

import me.github.notsaki.userapplication.domain.model.Gender;
import me.github.notsaki.userapplication.domain.model.HomeAddress;
import me.github.notsaki.userapplication.domain.model.User;
import me.github.notsaki.userapplication.domain.model.WorkAddress;
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
	public User toUser() {
		return new User(
				this.name,
				this.surname,
				this.gender,
				this.birthdate,
				Optional.ofNullable(this.workAddress).map(WorkAddress::new).orElse(null),
				Optional.ofNullable(this.homeAddress).map(HomeAddress::new).orElse(null)
		);
	}
}
