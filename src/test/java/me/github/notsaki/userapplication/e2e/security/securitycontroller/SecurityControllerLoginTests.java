package me.github.notsaki.userapplication.e2e.security.securitycontroller;

import me.github.notsaki.userapplication.e2e.E2eSetup;
import me.github.notsaki.userapplication.infrastructure.data.receive.CredentialsEntity;
import me.github.notsaki.userapplication.util.Routes;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class SecurityControllerLoginTests extends E2eSetup {
	private final String route = Routes.login;

	public ResultActions login(String body) throws Exception {
		return this.mvc
				.perform(
						withAuth(
								post(this.route)
						)
								.content(body)
				);
	}

	@Test
	public void whenSendingValidCredentials_shouldReturnStatusCreated() throws Exception {
		var body = this.objectMapper.writeValueAsString(new CredentialsEntity("admin","admin"));

		this.login(body)
				.andExpect(status().isCreated());
	}

	@Test
	public void whenSendingInvalidBody_shouldReturnStatusBadRequest() throws Exception {
		this.login("random_body")
				.andExpect(status().isUnauthorized())
				.andExpect(jsonPath("access_token").doesNotExist())
				.andExpect(jsonPath("refresh_token").doesNotExist());
	}

	@Test
	public void whenSendingInvalidPassword_shouldReturnStatusUnauthorized() throws Exception {
		var body = this.objectMapper.writeValueAsString(new CredentialsEntity("admin",""));

		this.login(body)
				.andExpect(status().isUnauthorized())
				.andExpect(jsonPath("access_token").doesNotExist())
				.andExpect(jsonPath("refresh_token").doesNotExist());
	}

	@Test
	public void whenSendingInvalidUsername_shouldReturnStatusUnauthorized() throws Exception {
		var body = this.objectMapper.writeValueAsString(new CredentialsEntity("","admin"));

		this.login(body)
				.andExpect(status().isUnauthorized())
				.andExpect(jsonPath("access_token").doesNotExist())
				.andExpect(jsonPath("refresh_token").doesNotExist());
	}
}
