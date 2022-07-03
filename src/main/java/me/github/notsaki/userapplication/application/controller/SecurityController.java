package me.github.notsaki.userapplication.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.github.notsaki.userapplication.domain.data.receive.Credentials;
import me.github.notsaki.userapplication.domain.service.SecurityService;
import me.github.notsaki.userapplication.util.Routes;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Controller to receive access tokens.
 */
@RestController("Security Controller")
public class SecurityController {

	private final SecurityService securityService;
	private final ObjectMapper objectMapper;

	public SecurityController(SecurityService securityService, ObjectMapper objectMapper) {
		this.securityService = securityService;
		this.objectMapper = objectMapper;
	}

	/**
	 * The method deserialises the user body and creates an authentication user. Using the authentication manager
	 * which uses a UserDetailsService implementation validates the user and  if validated, it assigns them into the
	 * security context. Otherwise, the request is being interrupted and unauthorised response is being sent,
	 */
	@PostMapping(Routes.login)
	@ResponseStatus(HttpStatus.CREATED)
	public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			var credentials = this.objectMapper.readValue(request.getInputStream(), Credentials.class);
			var result = this.securityService.authenticate(credentials);

			if(result.isPresent()) {
				var authentication = new UsernamePasswordAuthenticationToken(
						result.get().getUsername(),
						null,
						List.of()
				);

				SecurityContextHolder.getContext().setAuthentication(authentication);

				response.setStatus(HttpServletResponse.SC_CREATED);
				return;
			}

		} catch (IOException | IllegalArgumentException ignore) {
		}

		response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
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
