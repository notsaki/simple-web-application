package me.github.notsaki.userapplication.domain.model;

import me.github.notsaki.userapplication.domain.data.response.ResponseUserDto;
import me.github.notsaki.userapplication.domain.data.response.UserListItemDto;
import me.github.notsaki.userapplication.infrastructure.model.HomeAddressModel;
import me.github.notsaki.userapplication.infrastructure.model.WorkAddressModel;
import org.springframework.lang.Nullable;

import java.time.LocalDate;
import java.util.Optional;

public interface User {
    int getId();

    void setId(int id);

    String getName();

    void setName(String name);

    String getSurname();

    void setSurname(String surname);

    Gender getGender();

    void setGender(Gender gender);

    LocalDate getBirthdate();

    void setBirthdate(LocalDate birthdate);

    Optional<WorkAddressModel> getWorkAddress();

    void setWorkAddress(@Nullable WorkAddressModel workAddress);

    Optional<HomeAddressModel> getHomeAddress();

    void setHomeAddress(HomeAddressModel homeAddress);

    boolean equals(Object o);

    int hashCode();

    ResponseUserDto toResponse();

    UserListItemDto toFullName();
}
