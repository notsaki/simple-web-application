package me.github.notsaki.userapplication.domain.entity.receive;

import me.github.notsaki.userapplication.domain.model.Gender;
import me.github.notsaki.userapplication.domain.model.HomeAddress;
import me.github.notsaki.userapplication.domain.model.User;
import me.github.notsaki.userapplication.domain.model.WorkAddress;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Optional;

public record ReceiveUserDto(
		@Size(min = 1, max = 64, message = "name should be between 1-64 characters long")
		String name,
		@Size(min = 1, max = 64, message = "surname should be between 1-64 characters long")
		String surname,
		Gender gender,
		@Past(message = "birthdate should be in the past")
		LocalDate birthdate,
		@Size(min = 1, max = 128, message = "work address should be between 1-128 characters long")
		@Nullable
		String workAddress,
		@Size(min = 1, max = 128, message = "home address should be between 1-128 characters long")
		@Nullable
		String homeAddress
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
