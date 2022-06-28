package me.github.notsaki.userapplication.filter;

import me.github.notsaki.userapplication.domain.service.TokenService;
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

public class AuthorizationFilter extends OncePerRequestFilter {
	private final TokenService tokenService;
	public AuthorizationFilter(TokenService tokenService) {
		this.tokenService = tokenService;
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {
		String path = request.getRequestURI();
		return "/login".equals(path);
	}

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
