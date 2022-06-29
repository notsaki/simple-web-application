package me.github.notsaki.userapplication.e2e.user;

import me.github.notsaki.userapplication.domain.model.User;
import me.github.notsaki.userapplication.domain.repository.UserRepository;
import me.github.notsaki.userapplication.e2e.E2eSetup;
import me.github.notsaki.userapplication.util.AppProfile;
import me.github.notsaki.userapplication.util.stub.user.ReceiveUserStub;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserControllerDeleteByIdTests extends E2eSetup {
    private final String route = "/user";
    private User createdUser;

    @Autowired
    @Qualifier(AppProfile.IMPL)
    private UserRepository userRepository;

    @Before
    public void saveUser() {
        this.createdUser = this.userRepository.save(ReceiveUserStub.one().toUser());
    }

    public void assertDoesntExistInDb() {
        Assert.assertTrue(this.userRepository.findById(this.createdUser.getId()).isEmpty());
    }

    public void assertExistsInDb() {
        Assert.assertTrue(this.userRepository.findById(this.createdUser.getId()).isPresent());
    }

    @Test
    public void sendingRequestWithExistingId_shouldDeleteTheUser() throws Exception {
        var token = this.login();

        this.mvc
                .perform(
                        delete(this.route + "/" + this.createdUser.getId())
                                .header(AUTHORIZATION, "Bearer " + token.access_token())
                )
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("name").doesNotExist())
                .andExpect(jsonPath("surname").doesNotExist())
                .andExpect(jsonPath("gender").doesNotExist())
                .andExpect(jsonPath("birthdate").doesNotExist())
                .andExpect(jsonPath("workAddress").doesNotExist())
                .andExpect(jsonPath("homeAddress").doesNotExist())
                .andReturn();

        this.assertDoesntExistInDb();
    }

    @Test
    public void sendingRequestWithNonExistingId_shouldReturnNotFound() throws Exception {
        var token = this.login();

        this.mvc
                .perform(
                        delete(this.route + "/" + 0)
                                .header(AUTHORIZATION, "Bearer " + token.access_token())
                )
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("name").doesNotExist())
                .andExpect(jsonPath("surname").doesNotExist())
                .andExpect(jsonPath("gender").doesNotExist())
                .andExpect(jsonPath("birthdate").doesNotExist())
                .andExpect(jsonPath("workAddress").doesNotExist())
                .andExpect(jsonPath("homeAddress").doesNotExist())
                .andReturn();

        this.assertExistsInDb();
    }

    @Test
    public void sendingRequestWithInvalidId_shouldReturnBadRequest() throws Exception {
        var token = this.login();

        this.mvc
                .perform(
                        delete(this.route + "/" + "invalid_id")
                                .header(AUTHORIZATION, "Bearer " + token.access_token())
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("name").doesNotExist())
                .andExpect(jsonPath("surname").doesNotExist())
                .andExpect(jsonPath("gender").doesNotExist())
                .andExpect(jsonPath("birthdate").doesNotExist())
                .andExpect(jsonPath("workAddress").doesNotExist())
                .andExpect(jsonPath("homeAddress").doesNotExist())
                .andReturn();

        this.assertExistsInDb();
    }
}
