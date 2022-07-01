package me.github.notsaki.userapplication.domain.service;

import me.github.notsaki.userapplication.domain.data.receive.ReceiveUserDto;
import me.github.notsaki.userapplication.domain.data.response.ResponseUserDto;
import me.github.notsaki.userapplication.domain.data.response.UserListItemDto;
import me.github.notsaki.userapplication.domain.exception.BadDataException;
import me.github.notsaki.userapplication.domain.exception.ValidationException;
import me.github.notsaki.userapplication.domain.model.User;
import me.github.notsaki.userapplication.domain.repository.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserService {
	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	/**
	 * Validates and saves a new user.
	 * @param userDto the user data excluding the ID.
	 * @return the same user date including the generated ID.
	 * @throws ValidationException if user is not in a valid format.
	 * @throws BadDataException for unprocessable data types.
	 */
	public ResponseUserDto save(ReceiveUserDto userDto) throws ValidationException, BadDataException {
		try {
			var errors = userDto.validate();
			if(errors.size() > 0) throw new ValidationException(errors);
		} catch (NullPointerException exception) {
			throw new BadDataException();
		}

		return this.userRepository.save(userDto.toUser()).toResponse();
	}

	/**
	 * Remove a user by ID.
	 * @param id the id for the user to delete.
	 * @return true if the user was deleted and false if the user was not found.
	 */
	public boolean deleteById(int id) {
		return this.userRepository.deleteById(id);
	}

	/**
	 * Update a user by ID.
	 * @param id the user ID to update.
	 * @param user the new user data including the unchanged ones.
	 * @return The user data updated including the ID.
	 */
	public Optional<ResponseUserDto> updateById(
			int id,
			ReceiveUserDto user
	) throws ValidationException, BadDataException {
		try {
			var errors = user.validate();
			if(errors.size() > 0) throw new ValidationException(errors);
		} catch (NullPointerException exception) {
			throw new BadDataException();
		}

		var updatedUser = user.toUser();
		updatedUser.setId(id);
		return this.userRepository.update(updatedUser).map(User::toResponse);
	}

	/**
	 * Retrieve all saved users.
	 * @return a list of users' names and surnames.
	 */
	public List<UserListItemDto> findAll() {
		return this.userRepository.findAll();
	}

	/**
	 * Find a user by ID.
	 * @param id the user ID to match.
	 * @return a user if the user was found and empty if not.
	 */
	public Optional<ResponseUserDto> findById(int id) {
		return this.userRepository.findById(id).map(User::toResponse);
	}
}
