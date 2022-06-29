package me.github.notsaki.userapplication.filter;

import me.github.notsaki.userapplication.domain.service.TokenService;
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

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

/**
 * Filter to authorise a request. The filter skips if the route is /login or /token and also skips any kind of CORS
 * request.
 */
public class AuthorizationFilter extends OncePerRequestFilter {
	private final TokenService tokenService;
	public AuthorizationFilter(TokenService tokenService) {
		this.tokenService = tokenService;
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {
		String path = request.getRequestURI();
		return Routes.login.equals(path) || Routes.refreshToken.equals(path) || request.getMethod().equals("OPTIONS");
	}

	/**
	 * The method reads the Authorization header, and validates it through a UserService implementation. If the service
	 * returns a username, it assigns it to the SecurityContextHolder. Otherwise, the request gets interrupted and an
	 * unauthorised response is being sent.
	 */
	@Override
	protected void doFilterInternal(
			HttpServletRequest request,
			HttpServletResponse response,
			FilterChain filterChain
	) throws ServletException, IOException {
		var tokenPrefix = "Bearer ";
		var header = request.getHeader(AUTHORIZATION);

		if(header != null && header.startsWith(tokenPrefix)) {
			try {
				var username = this.tokenService.validateToken(tokenPrefix, header);
				if(username.isEmpty()) {
					response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
					return;
				}

				var authentication = new UsernamePasswordAuthenticationToken(username, null, List.of());

				SecurityContextHolder.getContext().setAuthentication(authentication);
			} catch (Exception exception) {
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
				return;
			}
		} else {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}

		filterChain.doFilter(request, response);
	}
}
