package me.github.notsaki.userapplication.infrastructure.data.receive;

import me.github.notsaki.userapplication.domain.data.receive.ReceiveUserDto;
import me.github.notsaki.userapplication.domain.model.Gender;
import me.github.notsaki.userapplication.domain.model.User;
import me.github.notsaki.userapplication.infrastructure.model.HomeAddressModel;
import me.github.notsaki.userapplication.infrastructure.model.UserModel;
import me.github.notsaki.userapplication.infrastructure.model.WorkAddressModel;
import org.springframework.lang.Nullable;

import java.time.LocalDate;
import java.util.Optional;

public class ReceiveUserDtoEntity extends ReceiveUserDto {
    private String name;
    private String surname;
    private Gender gender;
    private LocalDate birthdate;
    @Nullable
    private String workAddress;
    @Nullable
    private String homeAddress;

    public ReceiveUserDtoEntity() {
    }

    public ReceiveUserDtoEntity(String name, String surname, Gender gender, LocalDate birthdate, String workAddress, String homeAddress) {
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.birthdate = birthdate;
        this.workAddress = workAddress;
        this.homeAddress = homeAddress;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Gender getGender() {
        return gender;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public Optional<String> getWorkAddress() {
        return Optional.ofNullable(workAddress);
    }

    public Optional<String> getHomeAddress() {
        return Optional.ofNullable(homeAddress);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public void setWorkAddress(Optional<String> workAddress) {
        this.workAddress = workAddress.orElse(null);
    }

    public void setHomeAddress(Optional<String> homeAddress) {
        this.homeAddress = homeAddress.orElse(null);
    }

    @Override
    public User toUser() {
        return new UserModel(
                this.name,
                this.surname,
                this.gender,
                this.birthdate,
                new WorkAddressModel(this.workAddress),
                new HomeAddressModel(this.homeAddress)
        );
    }
}
