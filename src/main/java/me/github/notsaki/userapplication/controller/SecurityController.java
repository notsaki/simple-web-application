package me.github.notsaki.userapplication.controller;

import me.github.notsaki.userapplication.domain.entity.receive.RefreshToken;
import me.github.notsaki.userapplication.domain.entity.response.JwtToken;
import me.github.notsaki.userapplication.domain.service.TokenService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController("Security Controller")
@RequestMapping("/token")
public class SecurityController {
	private final TokenService tokenService;
	private final UserDetailsService userDetailsService;

	public SecurityController(TokenService tokenService, UserDetailsService userDetailsService) {
		this.tokenService = tokenService;
		this.userDetailsService = userDetailsService;
	}

	@PostMapping
	public JwtToken refresh(HttpServletRequest request, @RequestBody RefreshToken refreshToken) {
		var user = this.tokenService
				.validate("", refreshToken.refreshToken())
				.orElseThrow(() -> new UsernameNotFoundException("User not found."));

		var admin = this.userDetailsService.loadUserByUsername(user);
		var issuer = request.getRequestURI();
		var principal = new User(admin.getUsername(), admin.getPassword(), List.of());

		return this.tokenService.generate(principal, issuer);
	}
}
