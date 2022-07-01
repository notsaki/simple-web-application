package me.github.notsaki.userapplication.infrastructure.data.receive;

import me.github.notsaki.userapplication.domain.data.receive.ReceiveUserDto;
import me.github.notsaki.userapplication.domain.model.Gender;
import me.github.notsaki.userapplication.domain.model.User;
import me.github.notsaki.userapplication.infrastructure.model.HomeAddressModel;
import me.github.notsaki.userapplication.infrastructure.model.UserModel;
import me.github.notsaki.userapplication.infrastructure.model.WorkAddressModel;

import java.time.LocalDate;
import java.util.Objects;

public class ReceiveUserDtoEntity extends ReceiveUserDto {
    private String name;
    private String surname;
    private Gender gender;
    private LocalDate birthdate;
    private String workAddress;
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

    public String name() {
        return name;
    }

    public String surname() {
        return surname;
    }

    public Gender gender() {
        return gender;
    }

    public LocalDate birthdate() {
        return birthdate;
    }

    public String workAddress() {
        return workAddress;
    }

    public String homeAddress() {
        return homeAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReceiveUserDto that = (ReceiveUserDto) o;
        return name.equals(that.name()) && surname.equals(that.surname()) && gender == that.gender() && birthdate.equals(that.birthdate()) && Objects.equals(workAddress, that.workAddress()) && Objects.equals(homeAddress, that.homeAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, gender, birthdate, workAddress, homeAddress);
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
