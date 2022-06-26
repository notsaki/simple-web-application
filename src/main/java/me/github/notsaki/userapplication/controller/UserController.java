package me.github.notsaki.userapplication.controller;

import me.github.notsaki.userapplication.domain.entity.response.UserListItemDto;
import me.github.notsaki.userapplication.domain.service.UserService;
import me.github.notsaki.userapplication.domain.entity.receive.ReceiveUserDto;
import me.github.notsaki.userapplication.domain.entity.response.ResponseUserDto;
import me.github.notsaki.userapplication.exception.RecordNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController("User Controller")
@RequestMapping("/user")
public class UserController {
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping
	public ResponseUserDto create(@Valid @RequestBody ReceiveUserDto receiveUserDto) {
		return this.userService.save(receiveUserDto);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable int id) throws RecordNotFoundException {
		if(!this.userService.deleteById(id))
			throw new RecordNotFoundException(new Object() { public final int userId = id; });
	}

	@GetMapping
	public List<UserListItemDto> findAll() {
		return this.userService.findAll();
	}

	@GetMapping("/{id}")
	public ResponseUserDto findById(@PathVariable("id") int id) throws RecordNotFoundException {
		return this.userService
				.findById(id)
				.orElseThrow(() -> new RecordNotFoundException(new Object() { public final int userId = id; }));
	}
}
