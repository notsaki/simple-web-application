package me.github.notsaki.userapplication.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.github.notsaki.userapplication.domain.entity.receive.Credentials;
import me.github.notsaki.userapplication.domain.service.TokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private final AuthenticationManager authenticationManager;
	private final TokenService tokenService;

	public AuthenticationFilter(AuthenticationManager authenticationManager, TokenService tokenService) {
		this.authenticationManager = authenticationManager;
		this.tokenService = tokenService;
	}
	@Override
	public Authentication attemptAuthentication(
			HttpServletRequest request,
			HttpServletResponse response
	) throws AuthenticationException {
		try {
			var credentials = new ObjectMapper().readValue(request.getInputStream(), Credentials.class);
			var token = new UsernamePasswordAuthenticationToken(credentials.username(), credentials.password());
			return authenticationManager.authenticate(token);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(
			HttpServletRequest request,
			HttpServletResponse response,
			FilterChain chain,
			Authentication authResult
	) throws IOException {
		var user = (User)authResult.getPrincipal();
		var jwt = this.tokenService.generate(user, request.getRequestURI());

		response.setContentType(APPLICATION_JSON_VALUE);
		response.getWriter().write(new ObjectMapper().writeValueAsString(jwt));
	}
}
