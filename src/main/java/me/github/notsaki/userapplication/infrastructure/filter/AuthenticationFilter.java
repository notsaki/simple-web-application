package me.github.notsaki.userapplication.infrastructure.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.github.notsaki.userapplication.domain.service.SecurityService;
import me.github.notsaki.userapplication.infrastructure.data.receive.CredentialsEntity;
import me.github.notsaki.userapplication.util.Routes;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Filter to authenticate user credentials. The filter only runs in the POST /login endpoint.
 */
public class AuthenticationFilter extends OncePerRequestFilter {
	private final SecurityService securityService;

	public AuthenticationFilter(SecurityService securityService) {
		this.securityService = securityService;
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
			var credentials = new ObjectMapper().readValue(request.getInputStream(), CredentialsEntity.class);
			var result = this.securityService.authenticate(credentials);

			if(result.isPresent()) {
				var authentication = new UsernamePasswordAuthenticationToken(
						result.get().getUsername(),
						null,
						List.of()
				);

				SecurityContextHolder.getContext().setAuthentication(authentication);

				filterChain.doFilter(request, response);
				return;
			}

		} catch (IOException ignore) {
		}

		response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
	}
}
