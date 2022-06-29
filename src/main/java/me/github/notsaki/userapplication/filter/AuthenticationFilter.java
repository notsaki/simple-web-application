package me.github.notsaki.userapplication.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.github.notsaki.userapplication.domain.entity.receive.Credentials;
import me.github.notsaki.userapplication.util.Routes;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filter to authenticate user credentials. The filter only runs in the POST /login endpoint.
 */
public class AuthenticationFilter extends OncePerRequestFilter {
	private final AuthenticationManager authenticationManager;

	public AuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {
		String path = request.getRequestURI();
		return !Routes.login.equals(path) || !request.getMethod().equals("POST");
	}

	/**
	 * The method deserialises the user body and creates an authentication user. Using the authentication manager
	 * which uses a UserDetailsService implementation validates the user and  if validated, it assigns them into the
	 * security context. Otherwise, the request is being interrupted and unauthorised response is being sent,
	 */
	@Override
	protected void doFilterInternal(
			HttpServletRequest request,
			HttpServletResponse response,
			FilterChain filterChain
	) throws IOException, ServletException {
		try {
			var credentials = new ObjectMapper().readValue(request.getInputStream(), Credentials.class);
			var token = new UsernamePasswordAuthenticationToken(credentials.username(), credentials.password());

			var authentication = authenticationManager.authenticate(token);
			SecurityContextHolder.getContext().setAuthentication(authentication);

		} catch (IOException | BadCredentialsException exception) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}

		filterChain.doFilter(request, response);
	}
}
