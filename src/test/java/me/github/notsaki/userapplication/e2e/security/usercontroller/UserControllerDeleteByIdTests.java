package me.github.notsaki.userapplication.e2e.security.usercontroller;

import me.github.notsaki.userapplication.e2e.E2eAuthSetup;
import me.github.notsaki.userapplication.service.TokenServiceImpl;
import me.github.notsaki.userapplication.util.Routes;
import me.github.notsaki.userapplication.util.stub.admin.AdminStub;
import me.github.notsaki.userapplication.util.stub.user.ReceiveUserStub;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerDeleteByIdTests extends E2eAuthSetup {

    private final String route = Routes.user + "/1";

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

    @Test
    public void switchingTokenWithAnotherSecret_shouldReturnUnauthorized() throws Exception {
        var admin = AdminStub.one();
        var jwt = new TokenServiceImpl("test")
                .generateToken(admin.getUsername(), "/token");

        this.mvc
                .perform(
                        delete(this.route)
                                .header(AUTHORIZATION, jwt.access_token())
                )
                .andExpect(status().isUnauthorized());
    }
}
