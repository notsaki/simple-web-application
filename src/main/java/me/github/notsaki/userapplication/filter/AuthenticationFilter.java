package me.github.notsaki.userapplication.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.github.notsaki.userapplication.domain.entity.receive.Credentials;
import me.github.notsaki.userapplication.domain.service.TokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

public class AuthenticationFilter extends OncePerRequestFilter {
	private final AuthenticationManager authenticationManager;
	private final TokenService tokenService;

	public AuthenticationFilter(AuthenticationManager authenticationManager, TokenService tokenService) {
		this.authenticationManager = authenticationManager;
		this.tokenService = tokenService;
	}

	@Override
	protected void doFilterInternal(
			HttpServletRequest request,
			HttpServletResponse response,
			FilterChain filterChain
	) throws IOException {
		try {
			var credentials = new ObjectMapper().readValue(request.getInputStream(), Credentials.class);

			var token = new UsernamePasswordAuthenticationToken(credentials.username(), credentials.password());

			var authentication = authenticationManager.authenticate(token);

			var user = (User)authentication.getPrincipal();
			var jwt = this.tokenService.generate(user, request.getRequestURI());

			response.setContentType(APPLICATION_JSON_VALUE);
			response.getWriter().write(new ObjectMapper().writeValueAsString(jwt));
		} catch (BadCredentialsException exception) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		} catch (IOException exception) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
	}
}
