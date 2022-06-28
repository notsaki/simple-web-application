package me.github.notsaki.userapplication.e2e.security.usercontroller;

import me.github.notsaki.userapplication.e2e.E2eAuthSetup;
import org.junit.Test;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerWithoutBodyRequestTests extends E2eAuthSetup {
    private final String route;
    private final RequestMethod method;

    public UserControllerWithoutBodyRequestTests(RequestMethod method, String route) {
        this.method = method;
        this.route = route;
    }

    @Test
    public void sendingRequestWithoutToken_shouldReturnForbidden() throws Exception {
        this.mvc
                .perform(
                        this.method.operation(this.route)
                )
                .andExpect(status().isForbidden());
    }

    @Test
    public void sendingRequestWithInvalidToken_shouldReturnForbidden() throws Exception {
        this.mvc
                .perform(
                        this.method.operation(this.route)
                                .header(AUTHORIZATION, this.invalidToken)
                )
                .andExpect(status().isForbidden());
    }

    @Test
    public void sendingRequestWithExpiredToken_shouldReturnForbidden() throws Exception {
        this.mvc
                .perform(
                        this.method.operation(this.route)
                                .header(AUTHORIZATION, this.expiredToken)
                )
                .andExpect(status().isForbidden());
    }
}
