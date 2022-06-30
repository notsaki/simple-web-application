package me.github.notsaki.userapplication.domain.service;

import me.github.notsaki.userapplication.domain.data.receive.ReceiveUserDto;
import me.github.notsaki.userapplication.domain.data.response.ResponseUserDto;
import me.github.notsaki.userapplication.domain.data.response.UserListItemDto;
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
	 * Save a new user.
	 * @param userDto the user data excluding the ID.
	 * @return the same user date including the generated ID.
	 */
	public ResponseUserDto save(ReceiveUserDto userDto) {
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
	public Optional<ResponseUserDto> updateById(int id, ReceiveUserDto user) {
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
