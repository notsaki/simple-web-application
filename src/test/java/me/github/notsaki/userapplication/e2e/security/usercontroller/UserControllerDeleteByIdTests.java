package me.github.notsaki.userapplication.e2e.security.usercontroller;

import me.github.notsaki.userapplication.e2e.E2eSetup;
import me.github.notsaki.userapplication.util.Routes;
import org.junit.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

public class UserControllerDeleteByIdTests extends E2eSetup {

    private final String route = Routes.user + "/1";

    @Test
    public void sendingRequestWithoutAuthorisedCookie_shouldReturnForbidden() throws Exception {
        this.noCookieRequest(delete(this.route));
    }

    @Test
    public void sendingRequestWithInvalidCookie_shouldReturnForbidden() throws Exception {
        this.invalidCookieRequest(delete(this.route));
    }

    @Test
    public void sendingRequestWithExpiredCookie_shouldReturnForbidden() throws Exception {
        this.expiredCookieRequest(delete(this.route));
    }

    @Test
    public void sendingRequestWithoutCsrfToken_shouldReturnForbidden() throws Exception {
        this.noCsrfTokenRequest(delete(this.route));
    }

    @Test
    public void sendingRequestWithInvalidCsrfToken_shouldReturnForbidden() throws Exception {
        this.invalidCsrfToken(delete(this.route));
    }
}
