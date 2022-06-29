package me.github.notsaki.userapplication.application.controller;

import me.github.notsaki.userapplication.domain.data.response.JwtToken;
import me.github.notsaki.userapplication.infrastructure.data.receive.RefreshTokenEntity;
import me.github.notsaki.userapplication.domain.service.TokenService;
import me.github.notsaki.userapplication.util.Routes;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller to receive access tokens.
 */
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

	/**
	 * Retrieve a JWT token. When reaching the method the credentials should already be valid. The method gets the
	 * principal and generates a token based on it.
	 * @param request Servlet's request object.
	 * @return access and refresh tokens.
	 */
	@PostMapping(Routes.login)
	@ResponseStatus(HttpStatus.CREATED)
	public JwtToken login(HttpServletRequest request) {
		var principal = request.getUserPrincipal();
		return this.tokenService.generateToken(principal.getName(), Routes.login);
	}

	/**
	 * Retrieve a new JWT token. Accepts any JWT token and after validating it, it generates a new one.
	 * @param request Servlet's request object.
	 * @param refreshToken Any valid JWT token with subject a valid admin user.
	 * @return access and refresh tokens.
	 */
	@PostMapping(Routes.refreshToken)
	@ResponseStatus(HttpStatus.CREATED)
	public JwtToken refresh(HttpServletRequest request, @RequestBody RefreshTokenEntity refreshToken) {
		var user = this.tokenService
				.validateToken("", refreshToken.refreshToken())
				.orElseThrow(() -> new UsernameNotFoundException("User not found."));

		var admin = this.userDetailsService.loadUserByUsername(user);
		var issuer = request.getRequestURI();

		return this.tokenService.generateToken(admin.getUsername(), issuer);
	}
}
