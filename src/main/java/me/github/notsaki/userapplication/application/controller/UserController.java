package me.github.notsaki.userapplication.application.controller;

import me.github.notsaki.userapplication.domain.data.response.ResponseUserDto;
import me.github.notsaki.userapplication.domain.data.response.UserListItemDto;
import me.github.notsaki.userapplication.domain.exception.BadDataException;
import me.github.notsaki.userapplication.domain.exception.ValidationException;
import me.github.notsaki.userapplication.domain.service.UserService;
import me.github.notsaki.userapplication.infrastructure.data.receive.ReceiveUserDtoEntity;
import me.github.notsaki.userapplication.infrastructure.exception.RecordNotFoundException;
import me.github.notsaki.userapplication.util.Routes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Controller to manage and read users.
 */
@RestController("User Controller")
@RequestMapping(Routes.user)
public class UserController {
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	/**
	 * Save a new user. User information should be validated before reaching the controller.
	 * @param receiveUserDto valid user information.
	 * @return Created user information with the generated ID.
	 * @throws ValidationException when bad data format are received.
	 * @throws BadDataException when invalid body was received.
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseUserDto create(
			@RequestBody ReceiveUserDtoEntity receiveUserDto
	) throws ValidationException, BadDataException {
		return this.userService.save(receiveUserDto);
	}

	/**
	 * Remove a user by ID.
	 * @param id the user ID to match for deletion.
	 * @throws RecordNotFoundException when the ID doesn't match to any user.
	 */
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable int id) throws RecordNotFoundException {
		if(!this.userService.deleteById(id))
			throw new RecordNotFoundException(Map.of("userId", id));
	}

	/**
	 * Update a user by ID.
	 * @param id the user ID to match.
	 * @param user the new full user information including the unchanged properties.
	 * @return the updated user.
	 * @throws RecordNotFoundException when the ID doesn't match to any user.
	 * @throws ValidationException when bad data format are received.
	 * @throws BadDataException when invalid body was received.
	 */
	@PatchMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseUserDto updateById(
			@PathVariable("id") int id,
			@RequestBody ReceiveUserDtoEntity user
	) throws RecordNotFoundException, ValidationException, BadDataException {
		return this.userService
				.updateById(id, user)
				.orElseThrow(() -> new RecordNotFoundException(Map.of("userId", id)));
	}

	/**
	 * Returns a list of all users.
	 * @return Name and surname for each user.
	 */
	@GetMapping
	public List<UserListItemDto> findAll() {
		return this.userService.findAll();
	}

	/**
	 * Find a user by ID.
	 * @param id the user ID to match.
	 * @return The full user information.
	 * @throws RecordNotFoundException when the ID doesn't match to any user.
	 */
	@GetMapping("/{id}")
	public ResponseUserDto findById(@PathVariable("id") int id) throws RecordNotFoundException {
		return this.userService
				.findById(id)
				.orElseThrow(() -> new RecordNotFoundException(Map.of("userId", id)));
	}
}
