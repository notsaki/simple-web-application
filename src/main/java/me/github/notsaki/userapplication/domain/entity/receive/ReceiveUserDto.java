package me.github.notsaki.userapplication.domain.entity.receive;

import me.github.notsaki.userapplication.domain.entity.ValidationMessage;
import me.github.notsaki.userapplication.domain.model.Gender;
import me.github.notsaki.userapplication.domain.model.HomeAddress;
import me.github.notsaki.userapplication.domain.model.User;
import me.github.notsaki.userapplication.domain.model.WorkAddress;
import org.springframework.lang.Nullable;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Optional;

public record ReceiveUserDto(
		@NotNull(message = ValidationMessage.notBlank)
		@Size(min = 1, max = 64, message = ValidationMessage.nameLength)
		String name,
		@NotNull(message = ValidationMessage.notBlank)
		@Size(min = 1, max = 64, message = ValidationMessage.nameLength)
		String surname,
		@NotNull(message = ValidationMessage.notBlank)
		Gender gender,
		@NotNull(message = ValidationMessage.notBlank)
		@Past(message = ValidationMessage.datePast)
		LocalDate birthdate,
		@NotNull(message = ValidationMessage.notBlank)
		@Size(min = 1, max = 128, message = ValidationMessage.addressLength)
		@Nullable
		String workAddress,
		@NotNull(message = ValidationMessage.notBlank)
		@Size(min = 1, max = 128, message = ValidationMessage.addressLength)
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
