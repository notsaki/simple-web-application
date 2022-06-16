package me.github.notsaki.userapplication.repository.mock;

import me.github.notsaki.userapplication.domain.model.Gender;
import me.github.notsaki.userapplication.domain.model.User;
import me.github.notsaki.userapplication.domain.repository.UserRepository;

import java.util.*;

public class UserRepositoryMock implements UserRepository {
	private List<User> users;

	public UserRepositoryMock() {
		this.users = Arrays.asList(
				new User(1,
						"Pantelis",
						"Anastasiadis",
						Gender.MALE,
						new Date(1996, Calendar.JULY, 1),
						Optional.of("Skra 23, 67100, Xanthi"),
						Optional.empty()
				),
				new User(2,
						"Anna",
						"Palaiokosta",
						Gender.FEMALE,
						new Date(1998, Calendar.MARCH, 15)
				),
				new User(3,
						"Panagiotis",
						"Sardatselis",
						Gender.MALE,
						new Date(1997, Calendar.APRIL, 12),
						Optional.of("28 of October, 67100, Xanthi"),
						Optional.of("40 Ekklision, 67100, Xanthi")
				)
		);
	}

	@Override
	public User save(User user) {
		int nextId = this.users
				.stream()
				.map(i -> i.getId().orElseThrow())
				.max(Comparator.comparing(i -> i))
				.orElse(1) + 1;

		user.setId(nextId);
		this.users.add(user);

		return user;
	}

	@Override
	public int deleteById(int id) {
		int oldLength = this.users.size();

		this.users = this.users
				.stream()
				.filter(i -> i.getId().orElseThrow() == id)
				.toList();

		return this.users.size() - oldLength;
	}

	@Override
	public List<User> findAll() {
		return this.users;
	}
}
