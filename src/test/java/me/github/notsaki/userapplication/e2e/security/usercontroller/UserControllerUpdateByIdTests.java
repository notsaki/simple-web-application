package me.github.notsaki.userapplication.e2e.security.usercontroller;

import me.github.notsaki.userapplication.e2e.E2eSetup;
import me.github.notsaki.userapplication.util.Routes;
import org.junit.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class UserControllerUpdateByIdTests extends E2eSetup {
    private final String route = Routes.user;

    @Test
    public void sendingRequestWithoutAuthorisedCookie_shouldReturnForbidden() throws Exception {
        this.noCookieRequest(patch(this.route));
    }

    @Test
    public void sendingRequestWithInvalidCookie_shouldReturnForbidden() throws Exception {
        this.invalidCookieRequest(patch(this.route));
    }

    @Test
    public void sendingRequestWithExpiredCookie_shouldReturnForbidden() throws Exception {
        this.expiredCookieRequest(patch(this.route));
    }

    @Test
    public void sendingRequestWithoutCsrfToken_shouldReturnForbidden() throws Exception {
        this.noCsrfTokenRequest(patch(this.route));
    }

    @Test
    public void sendingRequestWithInvalidCsrfToken_shouldReturnForbidden() throws Exception {
        this.invalidCsrfToken(patch(this.route));
    }
}
