package me.github.notsaki.userapplication.e2e.user;

import me.github.notsaki.userapplication.domain.entity.ValidationMessage;
import me.github.notsaki.userapplication.domain.entity.error.ValidationInfo;
import me.github.notsaki.userapplication.domain.entity.receive.ReceiveUserDto;
import me.github.notsaki.userapplication.domain.entity.response.ResponseUserDto;
import me.github.notsaki.userapplication.domain.model.Gender;
import me.github.notsaki.userapplication.domain.model.User;
import me.github.notsaki.userapplication.domain.repository.UserRepository;
import me.github.notsaki.userapplication.e2e.E2eSetup;
import me.github.notsaki.userapplication.util.AppProfile;
import me.github.notsaki.userapplication.util.modelmapper.UserMapper;
import me.github.notsaki.userapplication.util.stub.user.ReceiveUserStub;
import me.github.notsaki.userapplication.util.stub.user.UserStub;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserControllerUpdateUserTests extends E2eSetup {
    private final String route = "/user";
    private User createdUser;

    @Autowired
    @Qualifier(AppProfile.IMPL)
    private UserRepository userRepository;

    private void assertNotUpdatedInDb() {
        var user = this.userRepository
                .findById(this.createdUser.getId())
                .orElseThrow();

        Assert.assertEquals(user, this.createdUser);
    }

    private void assertUpdatedInDb(ResponseUserDto response) {
        var user = this.userRepository
                .findById(this.createdUser.getId())
                .orElseThrow()
                .toResponse();

        Assert.assertEquals(user, response);
    }

    @Before
    public void saveUser() {
        this.createdUser = this.userRepository.save(UserStub.One());
    }

    @Test
    public void sendingRequestWithValidBody_shouldReturnOk() throws Exception {
        var userToUpdate = this.createdUser;
        userToUpdate.setName("Olga");

        var user = this.objectMapper.writeValueAsString(UserMapper.fromUserToReceive(userToUpdate));
        var token = this.login();

        var body = this.mvc
                .perform(
                        patch(this.route)
                                .header(AUTHORIZATION, "Bearer " + token.access_token())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(user)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        var userToUpdateAsResponse = userToUpdate.toResponse();
        var receivedUser = this.objectMapper.readValue(body.getResponse().getContentAsString(), ResponseUserDto.class);
        var dbUser = this.userRepository.findById(receivedUser.id()).orElseThrow().toResponse();

        Assert.assertEquals(userToUpdateAsResponse, dbUser);
        Assert.assertEquals(receivedUser, dbUser);

        this.assertUpdatedInDb(receivedUser);
    }
}
