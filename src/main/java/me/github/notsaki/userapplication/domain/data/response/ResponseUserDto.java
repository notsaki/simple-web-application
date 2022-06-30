package me.github.notsaki.userapplication.domain.data.response;

import me.github.notsaki.userapplication.domain.model.Gender;

import java.time.LocalDate;

public interface ResponseUserDto {
	int id();
	String name();
	String surname();
	Gender gender();
	LocalDate birthdate();
	String workAddress();
	String homeAddress();
}
