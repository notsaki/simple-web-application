package me.github.notsaki.userapplication.entity.receive;

import me.github.notsaki.userapplication.domain.entity.ValidationMessage;
import me.github.notsaki.userapplication.domain.entity.receive.ReceiveUserDto;
import me.github.notsaki.userapplication.domain.model.Gender;
import me.github.notsaki.userapplication.domain.model.User;
import me.github.notsaki.userapplication.model.HomeAddressModel;
import me.github.notsaki.userapplication.model.UserModel;
import me.github.notsaki.userapplication.model.WorkAddressModel;
import org.springframework.lang.Nullable;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Optional;

public record ReceiveUserDtoEntity(
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
		@Size(min = 1, max = 128, message = ValidationMessage.addressLength)
		@Nullable
		String workAddress,
		@Size(min = 1, max = 128, message = ValidationMessage.addressLength)
		@Nullable
		String homeAddress
) implements ReceiveUserDto {
	public UserModel toUser() {
		return new UserModel(
				this.name,
				this.surname,
				this.gender,
				this.birthdate,
				Optional.ofNullable(this.workAddress).map(WorkAddressModel::new).orElse(null),
				Optional.ofNullable(this.homeAddress).map(HomeAddressModel::new).orElse(null)
		);
	}
}
