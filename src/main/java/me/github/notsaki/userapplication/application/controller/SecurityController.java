package me.github.notsaki.userapplication.application.controller;

import me.github.notsaki.userapplication.util.Routes;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * Controller to receive access tokens.
 */
@RestController("Security Controller")
public class SecurityController {
	@PostMapping(Routes.login)
	@ResponseStatus(HttpStatus.CREATED)
	public void login() {
	}

	@PostMapping(Routes.logout)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void logout() {
		SecurityContextHolder.clearContext();
	}

	@GetMapping(Routes.token)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void token() {
	}
}
