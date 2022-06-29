package me.github.notsaki.userapplication.domain.service;

import me.github.notsaki.userapplication.domain.entity.receive.ReceiveUserDto;
import me.github.notsaki.userapplication.domain.entity.response.ResponseUserDto;
import me.github.notsaki.userapplication.domain.entity.response.UserListItemDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
	/**
	 * Save a new user.
	 * @param userDto the user data excluding the ID.
	 * @return the same user date including the generated ID.
	 */
	ResponseUserDto save(ReceiveUserDto userDto);

	/**
	 * Remove a user by ID.
	 * @param id the id for the user to delete.
	 * @return true if the user was deleted and false if the user was not found.
	 */
	boolean deleteById(int id);

	/**
	 * Update a user by ID.
	 * @param id the user ID to update.
	 * @param user the new user data including the unchanged ones.
	 * @return The user data updated including the ID.
	 */
	Optional<ResponseUserDto> updateById(int id, ReceiveUserDto user);

	/**
	 * Retrieve all saved users.
	 * @return a list of users' names and surnames.
	 */
	List<UserListItemDto> findAll();

	/**
	 * Find a user by ID.
	 * @param id the user ID to match.
	 * @return a user if the user was found and empty if not.
	 */
	Optional<ResponseUserDto> findById(int id);
}
