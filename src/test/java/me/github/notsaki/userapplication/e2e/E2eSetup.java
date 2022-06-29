package me.github.notsaki.userapplication.e2e;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import me.github.notsaki.userapplication.entity.receive.CredentialsEntity;
import me.github.notsaki.userapplication.entity.response.JwtTokenEntity;
import me.github.notsaki.userapplication.util.Routes;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
@AutoConfigureMockMvc
public abstract class E2eSetup {

	@Autowired
	protected MockMvc mvc;
	protected final ObjectMapper objectMapper;

	public E2eSetup() {
		this.objectMapper = new ObjectMapper();
		this.objectMapper.registerModule(new JavaTimeModule());
	}

	public JwtTokenEntity login() throws Exception {
		var body = this.objectMapper.writeValueAsString(new CredentialsEntity("admin", "admin"));

		var content = this.mvc
				.perform(
						post(Routes.login)
								.contentType(MediaType.APPLICATION_JSON)
								.content(body)
				)
				.andReturn()
				.getResponse()
				.getContentAsString();

		return this.objectMapper.readValue(content, JwtTokenEntity.class);
	}
}
