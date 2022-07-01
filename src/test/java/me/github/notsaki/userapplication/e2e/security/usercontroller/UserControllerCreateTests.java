package me.github.notsaki.userapplication.e2e.security.usercontroller;

import me.github.notsaki.userapplication.e2e.E2eSetup;
import me.github.notsaki.userapplication.util.Routes;
import me.github.notsaki.userapplication.util.stub.user.ReceiveUserStub;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerCreateTests extends E2eSetup {
    private final String route = Routes.user;

    @Test
    public void sendingRequestWithoutAuthorisedCookie_shouldReturnForbidden() throws Exception {
        this.noCookieRequest(post(this.route));
    }

    @Test
    public void sendingRequestWithInvalidCookie_shouldReturnForbidden() throws Exception {
        this.invalidCookieRequest(post(this.route));
    }

    @Test
    public void sendingRequestWithExpiredCookie_shouldReturnForbidden() throws Exception {
        this.expiredCookieRequest(post(this.route));
    }

    @Test
    public void sendingRequestWithoutCsrfToken_shouldReturnForbidden() throws Exception {
        this.noCsrfTokenRequest(post(this.route));
    }

    @Test
    public void sendingRequestWithInvalidCsrfToken_shouldReturnForbidden() throws Exception {
        this.invalidCsrfToken(post(this.route));
    }
}