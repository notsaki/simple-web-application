package me.github.notsaki.userapplication.domain.data.receive;

import me.github.notsaki.userapplication.domain.data.ValidatableEntity;
import me.github.notsaki.userapplication.domain.data.ValidationMessage;
import me.github.notsaki.userapplication.domain.data.error.ValidationIdentifier;
import me.github.notsaki.userapplication.domain.data.error.ValidationInfo;
import me.github.notsaki.userapplication.domain.model.Gender;
import me.github.notsaki.userapplication.domain.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public abstract class ReceiveUserDto implements ValidatableEntity {

    public ReceiveUserDto() {
    }

    public abstract String getName();
    public abstract String getSurname();
    public abstract Gender getGender();
    public abstract LocalDate getBirthdate();
    public abstract Optional<String> getWorkAddress();
    public abstract Optional<String> getHomeAddress();

    public abstract void setName(String name);
    public abstract void setSurname(String surname);
    public abstract void setGender(Gender gender);
    public abstract void setBirthdate(LocalDate birthdate);
    public abstract void setWorkAddress(Optional<String> workAddress);
    public abstract void setHomeAddress(Optional<String> homeAddress);

    public abstract User toUser();

    public List<ValidationInfo> validate() {
        var errors = new ArrayList<ValidationInfo>();

        if(this.getName().length() < 1 || this.getName().length() > 64)
            errors.add(new ValidationInfo("name", ValidationMessage.nameLength, ValidationIdentifier.INVALID_LENGTH));

        if(this.getSurname().length() < 1 || this.getSurname().length() > 64)
            errors.add(new ValidationInfo("surname", ValidationMessage.nameLength, ValidationIdentifier.INVALID_LENGTH));

        if(this.getBirthdate().isAfter(LocalDate.now()))
            errors.add(new ValidationInfo("birthdate", ValidationMessage.datePast, ValidationIdentifier.INVALID_VALUE));

        if(this.getWorkAddress().isPresent() && (this.getWorkAddress().get().length() < 1 || this.getWorkAddress().get().length() > 128))
            errors.add(new ValidationInfo("workAddress", ValidationMessage.addressLength, ValidationIdentifier.INVALID_LENGTH));

        if(this.getHomeAddress().isPresent() && (this.getHomeAddress().get().length() < 1 || this.getHomeAddress().get().length() > 128))
            errors.add(new ValidationInfo("homeAddress", ValidationMessage.addressLength, ValidationIdentifier.INVALID_LENGTH));

        return errors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReceiveUserDto that = (ReceiveUserDto) o;
        return this.getName().equals(that.getName()) && this.getSurname().equals(that.getSurname()) && this.getGender() == that.getGender() && this.getBirthdate().equals(that.getBirthdate()) && Objects.equals(this.getWorkAddress(), that.getWorkAddress()) && Objects.equals(this.getHomeAddress(), that.getHomeAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getName(), this.getSurname(), this.getGender(), this.getBirthdate(), this.getWorkAddress(), this.getHomeAddress());
    }
}
