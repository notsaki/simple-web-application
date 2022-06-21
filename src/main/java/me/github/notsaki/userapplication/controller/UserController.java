package me.github.notsaki.userapplication.controller;

import me.github.notsaki.userapplication.domain.model.User;
import me.github.notsaki.userapplication.domain.service.UserService;
import me.github.notsaki.userapplication.dto.receive.ReceiveUserDto;
import me.github.notsaki.userapplication.dto.receive.ResponseUserDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("User Controller")
@RequestMapping("/user")
public class UserController {
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping
	public ResponseUserDto create(@RequestBody ReceiveUserDto receiveUserDto) {
		return this.userService.save(receiveUserDto);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable int id) {
		this.userService.deleteById(id);
	}

	@GetMapping
	public List<ResponseUserDto> findAll() {
		return this.userService.findAll();
	}
}
