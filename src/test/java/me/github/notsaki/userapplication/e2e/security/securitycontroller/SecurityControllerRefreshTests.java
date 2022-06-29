package me.github.notsaki.userapplication.e2e.security.securitycontroller;

import me.github.notsaki.userapplication.domain.entity.receive.RefreshToken;
import me.github.notsaki.userapplication.e2e.E2eAuthSetup;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class SecurityControllerRefreshTests extends E2eAuthSetup {
	private final String route = "/token";

	public ResultActions refresh(String body) throws Exception {
		return this.mvc
				.perform(
						post(this.route)
								.contentType(MediaType.APPLICATION_JSON)
								.content(body)
				);
	}

	@Test
	public void whenSendingValidRefreshToken_shouldReturnStatusOk() throws Exception {
		this.jwt = this.login();

		var refreshToken = this.objectMapper.writeValueAsString(new RefreshToken(this.jwt.refresh_token()));

		this.refresh(refreshToken)
				.andExpect(status().isCreated())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("access_token").exists())
				.andExpect(jsonPath("refresh_token").exists());
	}

	@Test
	public void whenSendingExpiredToken_shouldReturnUnauthorized() throws Exception {
		var refreshToken = this.objectMapper.writeValueAsString(new RefreshToken(this.expiredToken));

		this.refresh(refreshToken)
				.andExpect(status().isForbidden())
				.andExpect(jsonPath("access_token").doesNotExist())
				.andExpect(jsonPath("refresh_token").doesNotExist());
	}

	@Test
	public void whenSendingInvalidBody_shouldReturnStatusBadRequest() throws Exception {
		this.refresh("token")
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("access_token").doesNotExist())
				.andExpect(jsonPath("refresh_token").doesNotExist());
	}

	@Test
	public void whenSendingInvalidToken_shouldReturnStatusUnauthorized() throws Exception {
		// TODO: Change API to return Unauthorized instead of Forbidden.
		var body = this.objectMapper.writeValueAsString(new RefreshToken(this.invalidToken));
		this.refresh(body)
				.andExpect(status().isForbidden())
				.andExpect(jsonPath("access_token").doesNotExist())
				.andExpect(jsonPath("refresh_token").doesNotExist());
	}
}
