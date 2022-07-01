package me.github.notsaki.userapplication.e2e.security.usercontroller;

import me.github.notsaki.userapplication.e2e.E2eSetup;
import me.github.notsaki.userapplication.util.Routes;
import org.junit.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class UserControllerFindByIdTests extends E2eSetup {

    private final String route = Routes.user + "/1";

    @Test
    public void sendingRequestWithoutAuthorisedCookie_shouldReturnForbidden() throws Exception {
        this.noCookieRequest(get(this.route));
    }

    @Test
    public void sendingRequestWithInvalidCookie_shouldReturnForbidden() throws Exception {
        this.invalidCookieRequest(get(this.route));
    }

    @Test
    public void sendingRequestWithExpiredCookie_shouldReturnForbidden() throws Exception {
        this.expiredCookieRequest(get(this.route));
    }
}
