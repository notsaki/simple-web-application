package me.github.notsaki.userapplication.e2e;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.github.notsaki.userapplication.domain.entity.receive.Credentials;
import me.github.notsaki.userapplication.domain.entity.response.JwtToken;
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
public abstract class E2eBaseClass {

	@Autowired
	protected MockMvc mvc;

	protected final ObjectMapper objectMapper = new ObjectMapper();

	public JwtToken login() throws Exception {
		var body = this.objectMapper.writeValueAsString(new Credentials("admin", "admin"));

		var content = this.mvc
				.perform(
						post("/login")
								.contentType(MediaType.APPLICATION_JSON)
								.content(body)
				)
				.andReturn()
				.getResponse()
				.getContentAsString();

		return this.objectMapper.readValue(content, JwtToken.class);
	}
}