package me.github.notsaki.userapplication.e2e.security.usercontroller;

import me.github.notsaki.userapplication.e2e.E2eSetup;
import me.github.notsaki.userapplication.util.Routes;
import me.github.notsaki.userapplication.util.stub.user.ReceiveUserStub;
import org.junit.Test;
import org.springframework.http.MediaType;

import javax.servlet.http.Cookie;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerCreateTests extends E2eSetup {
    private final String route = Routes.user;

    @Test
    public void sendingRequestWithoutToken_shouldReturnForbidden() throws Exception {
        var user = this.objectMapper.writeValueAsString(ReceiveUserStub.one());

        this.mvc
                .perform(
                        post(this.route)
                                .with(csrf().asHeader())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(user)
                )
                .andExpect(status().isForbidden());
    }

    @Test
    public void sendingRequestWithInvalidToken_shouldReturnForbidden() throws Exception {
        var user = this.objectMapper.writeValueAsString(ReceiveUserStub.one());

        this.mvc
                .perform(
                        post(this.route)
                                .with(csrf().asHeader())
                                .cookie(this.invalidCookie)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(user)
                )
                .andExpect(status().isForbidden());
    }

    @Test
    public void sendingRequestWithExpiredToken_shouldReturnForbidden() throws Exception {
        var user = this.objectMapper.writeValueAsString(ReceiveUserStub.one());

        this.mvc
                .perform(
                        post(this.route)
                                .with(csrf().asHeader())
                                .cookie(this.expiredCookie)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(user)
                )
                .andExpect(status().isForbidden());
    }
}