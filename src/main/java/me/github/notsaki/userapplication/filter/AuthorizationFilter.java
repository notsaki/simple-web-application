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
	protected void doFilterInternal(
			HttpServletRequest request,
			HttpServletResponse response,
			FilterChain filterChain
	) throws ServletException, IOException {
		if(request.getServletPath().equals("/login") || request.getServletPath().equals("/token")) {
			filterChain.doFilter(request, response);
			return;
		}

		var tokenPrefix = "Bearer ";
		var header = request.getHeader(AUTHORIZATION);

		if(header != null && header.startsWith(tokenPrefix)) {
			try {
				var username = this.tokenService.validate(tokenPrefix, header);
				var authentication = new UsernamePasswordAuthenticationToken(username, null, List.of());

				SecurityContextHolder.getContext().setAuthentication(authentication);
			} catch (Exception exception) {
				response.sendError(HttpServletResponse.SC_FORBIDDEN);
				return;
			}
		}

		filterChain.doFilter(request, response);
	}
}
