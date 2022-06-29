package me.github.notsaki.userapplication.e2e.security.usercontroller;

import me.github.notsaki.userapplication.e2e.E2eAuthSetup;
import org.junit.Test;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerDeleteByIdTests extends E2eAuthSetup {

    private final String route = "/user/1";

    @Test
    public void sendingRequestWithoutToken_shouldReturnUnauthorized() throws Exception {
        this.mvc
                .perform(
                        delete(this.route)
                )
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void sendingRequestWithInvalidToken_shouldReturnUnauthorized() throws Exception {
        this.mvc
                .perform(
                        delete(this.route)
                                .header(AUTHORIZATION, this.invalidToken)
                )
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void sendingRequestWithExpiredToken_shouldReturnUnauthorized() throws Exception {
        this.mvc
                .perform(
                        delete(this.route)
                                .header(AUTHORIZATION, this.expiredToken)
                )
                .andExpect(status().isUnauthorized());
    }
}
