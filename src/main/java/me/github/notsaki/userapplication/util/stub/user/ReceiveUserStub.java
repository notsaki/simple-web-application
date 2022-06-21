package me.github.notsaki.userapplication.util.stub.user;

import me.github.notsaki.userapplication.domain.entity.receive.ReceiveUserDto;
import me.github.notsaki.userapplication.domain.model.Gender;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class ReceiveUserStub {
	public static ReceiveUserDto One() {
		return new ReceiveUserDto(
				"Anna",
				"Dimitriadou",
				Gender.FEMALE,
				LocalDate.of(1997, Calendar.AUGUST, 3),
				"Andreou Dimitriou 3, 67100, Xanthi",
				"Michail Karaoli 33, 67100, Xanthi"
		);
	}

	public static List<ReceiveUserDto> List() {
		return new ArrayList(
				Arrays.asList(
						new ReceiveUserDto(
								"Pantelis",
								"Anastasiadis",
								Gender.MALE,
								LocalDate.of(1996, Calendar.JULY, 1),
								"Skra 23, 67100, Xanthi",
								null
						),
						new ReceiveUserDto(
								"Anna",
								"Palaiokosta",
								Gender.FEMALE,
								LocalDate.of(1998, Calendar.MARCH, 15),
								null,
								null
						),
						new ReceiveUserDto(
								"Panagiotis",
								"Sardatselis",
								Gender.MALE,
								LocalDate.of(1997, Calendar.APRIL, 12),
								"28 of October, 67100, Xanthi",
								"40 Ekklision, 67100, Xanthi"
						)
				)
		);
	}
}
