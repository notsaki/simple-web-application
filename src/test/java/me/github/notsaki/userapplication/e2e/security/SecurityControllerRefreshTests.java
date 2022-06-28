package me.github.notsaki.userapplication.e2e.security;

import me.github.notsaki.userapplication.domain.entity.receive.RefreshToken;
import me.github.notsaki.userapplication.e2e.E2eAuthBaseClass;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class SecurityControllerRefreshTests extends E2eAuthBaseClass {
	private final String expiredToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlzcyI6Ii9sb2dpbiIsImV4cCI6MTY1NjM1NzMwMX0.ywhkbp_2BQM-kch1f5yNDb0-PF3DFKT7lXdPtlEcJ8g";


	public ResultActions refresh(String body) throws Exception {
		return this.mvc
				.perform(
						post("/token")
								.contentType(MediaType.APPLICATION_JSON)
								.content(body)
				);
	}

	@Test
	public void whenSendingValidRefreshToken_shouldReturnStatusOk() throws Exception {
		var refreshToken = this.objectMapper.writeValueAsString(new RefreshToken(this.jwt.refresh_token()));

		this.refresh(refreshToken)
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("access_token").exists())
				.andExpect(jsonPath("refresh_token").exists());
	}

	@Test
	public void whenSendingExpiredToken_shouldReturnUnauthorized() throws Exception {
		var refreshToken = this.objectMapper.writeValueAsString(new RefreshToken(this.expiredToken));

		this.refresh(refreshToken)
				.andExpect(status().isUnauthorized());
	}

	@Test
	public void whenSendingInvalidBody_shouldReturnStatusBadRequest() throws Exception {
		this.refresh("token")
				.andExpect(status().isBadRequest());
	}

	@Test
	public void whenSendingInvalidToken_shouldReturnStatusUnauthorized() throws Exception {
		// TODO: Change API to return Unauthorized instead of Forbidden.
		var body = this.objectMapper.writeValueAsString(new RefreshToken("eyJ01XAiOiJKV1QiLCJhbGciOiJIUzI2NiJ9.eyJzdWIiOiJhZG1pbiIsImlzcyI6Ii9sb2dpbiIsImV4cCI6MTY1NjM1NzMwMX0.ywhkbp_2BQM-kch1f5yNDb5-PF3DFKT7lXdPtlecJ8g"));
		this.refresh(body)
				.andExpect(status().isUnauthorized());
	}
}
