package me.github.notsaki.userapplication.e2e.security.usercontroller;

import me.github.notsaki.userapplication.e2e.E2eAuthSetup;
import me.github.notsaki.userapplication.util.stub.user.ReceiveUserStub;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerCreateTests extends E2eAuthSetup {
    private final String route = "/user";

    @Test
    public void sendingRequestWithoutToken_shouldReturnUnauthorized() throws Exception {
        var user = this.objectMapper.writeValueAsString(ReceiveUserStub.one());

        this.mvc
                .perform(
                        post(this.route)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(user)
                )
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void sendingRequestWithInvalidToken_shouldReturnUnauthorized() throws Exception {
        var user = this.objectMapper.writeValueAsString(ReceiveUserStub.one());

        this.mvc
                .perform(
                        post(this.route)
                                .header(AUTHORIZATION, this.invalidToken)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(user)
                )
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void sendingRequestWithExpiredToken_shouldReturnUnauthorized() throws Exception {
        var user = this.objectMapper.writeValueAsString(ReceiveUserStub.one());

        this.mvc
                .perform(
                        post(this.route)
                                .header(AUTHORIZATION, this.expiredToken)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(user)
                )
                .andExpect(status().isUnauthorized());
    }
}