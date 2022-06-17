package me.github.notsaki.userapplication.stub;

import me.github.notsaki.userapplication.domain.model.Gender;
import me.github.notsaki.userapplication.domain.model.User;

import java.time.LocalDate;
import java.util.*;

public class UserStub {
	public static User ForSave() {
		return new User(
				"Anna",
				"Dimitriadou",
				Gender.FEMALE,
				LocalDate.of(1997, Calendar.AUGUST, 3),
				Optional.of("Andreou Dimitriou 3, 67100, Xanthi"),
				Optional.of("Michail Karaoli 33, 67100, Xanthi")
		);
	}

	public static List<User> ListOfUsers() {
		var user = UserStub.ForSave();
		var user2 = UserStub.ForSave();
		user2.setName("PANAGIOTIS SARANTINOS");
		user2.setGender(Gender.MALE);

		return new ArrayList<User>(
				Arrays.asList(
						user,
						user2
				)
		);
	}
}
