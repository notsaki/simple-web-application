package me.github.notsaki.userapplication.e2e.security.usercontroller;

import me.github.notsaki.userapplication.e2e.E2eSetup;
import me.github.notsaki.userapplication.e2e.E2eAuthSetup;
import me.github.notsaki.userapplication.util.Routes;
import org.junit.Test;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class UserControllerFindAllTests extends E2eSetup {

    private final String route = Routes.user;

    @Test
    public void sendingRequestWithoutToken_shouldReturnForbidden() throws Exception {
        this.mvc
                .perform(
                        get(this.route)
                                .with(csrf().asHeader())
                )
                .andExpect(status().isForbidden());
    }

    @Test
    public void sendingRequestWithInvalidToken_shouldReturnForbidden() throws Exception {
        this.mvc
                .perform(
                        get(this.route)
                                .with(csrf().asHeader())
                                .cookie(this.invalidCookie)
                )
                .andExpect(status().isForbidden());
    }

    @Test
    public void sendingRequestWithExpiredToken_shouldReturnForbidden() throws Exception {
        this.mvc
                .perform(
                        get(this.route)
                                .with(csrf().asHeader())
                                .cookie(this.expiredCookie)
                )
                .andExpect(status().isForbidden());
    }
}
