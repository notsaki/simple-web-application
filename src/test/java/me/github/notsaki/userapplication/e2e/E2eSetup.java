package me.github.notsaki.userapplication.e2e;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import javax.servlet.http.Cookie;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
@AutoConfigureMockMvc
public abstract class E2eSetup {

	@Autowired
	protected MockMvc mvc;
	protected final ObjectMapper objectMapper;
	protected Cookie cookie;
	protected Cookie invalidCookie = new Cookie("JSESSION", "invalid");
	protected Cookie expiredCookie = new Cookie("JSESSION", "FBAC394FB8CC4E0C82B817F1F8D058FA");

	public E2eSetup() {
		this.objectMapper = new ObjectMapper();
		this.objectMapper.registerModule(new JavaTimeModule());
	}

	protected static MockHttpServletRequestBuilder withAuth(MockHttpServletRequestBuilder context) {
		return context
				.with(csrfHeader())
				.with(admin());
	}

	protected static SecurityMockMvcRequestPostProcessors.UserRequestPostProcessor admin() {
		return user("admin");
	}

	protected static SecurityMockMvcRequestPostProcessors.CsrfRequestPostProcessor csrfHeader() {
		return csrf().asHeader();
	}

	protected void expiredCookieRequest(MockHttpServletRequestBuilder request) throws Exception {
		this.mvc
				.perform(
						request
								.cookie(this.expiredCookie)
								.with(csrfHeader())
				)
				.andExpect(status().isForbidden());
	}

	protected void invalidCookieRequest(MockHttpServletRequestBuilder request) throws Exception {
		this.mvc
				.perform(
						request
								.cookie(this.invalidCookie)
								.with(csrfHeader())
				)
				.andExpect(status().isForbidden());
	}

	protected void noCookieRequest(MockHttpServletRequestBuilder request) throws Exception {
		this.mvc
				.perform(
						request
								.with(csrfHeader())
				)
				.andExpect(status().isForbidden());
	}

	protected void noCsrfTokenRequest(MockHttpServletRequestBuilder request) throws Exception {
		this.mvc
				.perform(
						request
								.with(admin())
				)
				.andExpect(status().isForbidden());
	}

	protected void invalidCsrfToken(MockHttpServletRequestBuilder request) throws Exception {
		this.mvc
				.perform(
						request
								.with(admin())
								.header("X-CSRF-Token", "invalid")
				)
				.andExpect(status().isForbidden());
	}
}
