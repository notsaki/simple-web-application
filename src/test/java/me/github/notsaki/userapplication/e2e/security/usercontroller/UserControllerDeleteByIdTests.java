package me.github.notsaki.userapplication.e2e.security.usercontroller;

import me.github.notsaki.userapplication.e2e.E2eSetup;
import me.github.notsaki.userapplication.util.Routes;
import org.junit.Test;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerDeleteByIdTests extends E2eSetup {

    private final String route = Routes.user + "/1";

    @Test
    public void sendingRequestWithoutToken_shouldReturnForbidden() throws Exception {
        this.mvc
                .perform(
                        delete(this.route)
                                .with(csrf().asHeader())
                )
                .andExpect(status().isForbidden());
    }

    @Test
    public void sendingRequestWithInvalidToken_shouldReturnForbidden() throws Exception {
        this.mvc
                .perform(
                        delete(this.route)
                                .with(csrf().asHeader())
                                .cookie(this.invalidCookie)
                )
                .andExpect(status().isForbidden());
    }

    @Test
    public void sendingRequestWithExpiredToken_shouldReturnForbidden() throws Exception {
        this.mvc
                .perform(
                        delete(this.route)
                                .with(csrf().asHeader())
                                .cookie(this.expiredCookie)
                )
                .andExpect(status().isForbidden());
    }
}
