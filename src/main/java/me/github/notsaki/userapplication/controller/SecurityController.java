package me.github.notsaki.userapplication.controller;

import me.github.notsaki.userapplication.domain.entity.receive.RefreshToken;
import me.github.notsaki.userapplication.domain.entity.response.JwtToken;
import me.github.notsaki.userapplication.domain.service.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController("Security Controller")
public class SecurityController {
	private final TokenService tokenService;
	private final UserDetailsService userDetailsService;

	public SecurityController(
			TokenService tokenService,
			UserDetailsService userDetailsService
	) {
		this.tokenService = tokenService;
		this.userDetailsService = userDetailsService;
	}

	@PostMapping("/login")
	@ResponseStatus(HttpStatus.CREATED)
	public JwtToken login(HttpServletRequest request) {
		var principal = request.getUserPrincipal();
		return this.tokenService.generateToken(principal.getName(), "/login");
	}


	@PostMapping("/token")
	@ResponseStatus(HttpStatus.CREATED)
	public JwtToken refresh(HttpServletRequest request, @RequestBody RefreshToken refreshToken) {
		var user = this.tokenService
				.validateToken("", refreshToken.refreshToken())
				.orElseThrow(() -> new UsernameNotFoundException("User not found."));

		var admin = this.userDetailsService.loadUserByUsername(user);
		var issuer = request.getRequestURI();

		return this.tokenService.generateToken(admin.getUsername(), issuer);
	}
}
