package me.github.notsaki.userapplication.domain.data.receive;

import me.github.notsaki.userapplication.domain.model.Gender;
import me.github.notsaki.userapplication.domain.model.User;

import java.time.LocalDate;

public interface ReceiveUserDto {
    String name();
    String surname();
    Gender gender();
    LocalDate birthdate();
    String workAddress();
    String homeAddress();
    User toUser();
}
