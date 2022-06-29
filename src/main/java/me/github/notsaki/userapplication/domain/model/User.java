package me.github.notsaki.userapplication.domain.model;

import me.github.notsaki.userapplication.domain.entity.response.ResponseUserDto;
import me.github.notsaki.userapplication.domain.entity.response.UserListItemDto;
import me.github.notsaki.userapplication.model.HomeAddressModel;
import me.github.notsaki.userapplication.model.WorkAddressModel;
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

    @Override
    boolean equals(Object o);

    int hashCode();

    ResponseUserDto toResponse();

    UserListItemDto toFullName();
}
