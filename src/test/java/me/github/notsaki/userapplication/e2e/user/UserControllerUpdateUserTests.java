package me.github.notsaki.userapplication.e2e.user;

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
        this.createdUser = this.userRepository.save(ReceiveUserStub.one().toUser());
    }

    @Test
    public void sendingRequestWithValidBody_shouldReturnOkAndUpdateTheUser() throws Exception {
        var userToUpdate = ReceiveUserStub.another();
        var user = this.objectMapper.writeValueAsString(userToUpdate);

        var token = this.login();

        var body = this.mvc
                .perform(
                        patch(this.route + "/" + this.createdUser.getId())
                                .header(AUTHORIZATION, "Bearer " + token.access_token())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(user)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        var receivedUser = this.objectMapper.readValue(body.getResponse().getContentAsString(), ResponseUserDto.class);
        var dbUser = this.userRepository.findById(receivedUser.id()).orElseThrow().toResponse();

        Assert.assertEquals(UserMapper.fromResponseToReceive(receivedUser), userToUpdate);
        Assert.assertEquals(dbUser, receivedUser);

        this.assertUpdatedInDb(receivedUser);
    }

    @Test
    public void sendingInvalidBodyFormat_shouldReturnBadRequestAndNotUpdateAnyUser() throws Exception {
        var token = this.login();

        this.mvc
                .perform(
                        patch(this.route + "/" + this.createdUser.getId())
                                .header(AUTHORIZATION, "Bearer " + token.access_token())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("invalid_body")
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("name").doesNotExist())
                .andExpect(jsonPath("surname").doesNotExist())
                .andExpect(jsonPath("gender").doesNotExist())
                .andExpect(jsonPath("birthdate").doesNotExist())
                .andExpect(jsonPath("workAddress").doesNotExist())
                .andExpect(jsonPath("homeAddress").doesNotExist());

        this.assertNotUpdatedInDb();
    }

    @Test
    public void sendingMissingProperties_shouldReturnUnprocessableEntityAndNotUpdateAnyUser() throws Exception {
        var obj = new ReceiveUserDto(
                "",
                "",
                Gender.FEMALE,
                LocalDate.now().plusDays(1),
                "",
                ""
        );

        var user = this.objectMapper.writer().writeValueAsString(obj);
        var token = this.login();

        this.mvc
                .perform(
                        patch(this.route + "/" + this.createdUser.getId())
                                .header(AUTHORIZATION, "Bearer " + token.access_token())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(user)
                )
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("name").doesNotExist())
                .andExpect(jsonPath("surname").doesNotExist())
                .andExpect(jsonPath("gender").doesNotExist())
                .andExpect(jsonPath("birthdate").doesNotExist())
                .andExpect(jsonPath("workAddress").doesNotExist())
                .andExpect(jsonPath("homeAddress").doesNotExist());

        this.assertNotUpdatedInDb();
    }

    @Test
    public void sendingInvalidGenderValue_shouldReturnBadRequestAndNotUpdateTheUser() throws Exception {
        var stub = ReceiveUserStub.one();

        var obj = Map.of(
                "name", stub.name(),
                "surname", stub.surname(),
                "gender", "",
                "birthdate", stub.birthdate(),
                "workAddress", Objects.requireNonNull(stub.workAddress()),
                "homeAddress", Objects.requireNonNull(stub.homeAddress())
        );

        var user = this.objectMapper.writer().writeValueAsString(obj);
        var token = this.login();

        this.mvc
                .perform(
                        patch(this.route + "/" + this.createdUser.getId())
                                .header(AUTHORIZATION, "Bearer " + token.access_token())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(user)
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("name").doesNotExist())
                .andExpect(jsonPath("surname").doesNotExist())
                .andExpect(jsonPath("gender").doesNotExist())
                .andExpect(jsonPath("birthdate").doesNotExist())
                .andExpect(jsonPath("workAddress").doesNotExist())
                .andExpect(jsonPath("homeAddress").doesNotExist());

        this.assertNotUpdatedInDb();
    }

    @Test
    public void updatingNonExistentUser_shouldReturnNotFoundAndNotUpdateTheUser() throws Exception {
        var stub = ReceiveUserStub.one();

        var user = this.objectMapper.writer().writeValueAsString(stub);
        var token = this.login();

        this.mvc
                .perform(
                        patch(this.route + "/" + 0)
                                .header(AUTHORIZATION, "Bearer " + token.access_token())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(user)
                )
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("name").doesNotExist())
                .andExpect(jsonPath("surname").doesNotExist())
                .andExpect(jsonPath("gender").doesNotExist())
                .andExpect(jsonPath("birthdate").doesNotExist())
                .andExpect(jsonPath("workAddress").doesNotExist())
                .andExpect(jsonPath("homeAddress").doesNotExist());

        this.assertNotUpdatedInDb();
    }

    @Test
    public void updatingNonExistentUserWithInvalidBody_shouldReturnUnprocessableEntityAndNotUpdateTheUser() throws Exception {
        var obj = new ReceiveUserDto(
                "",
                "",
                Gender.FEMALE,
                LocalDate.now().plusDays(1),
                "",
                ""
        );

        var user = this.objectMapper.writer().writeValueAsString(obj);
        var token = this.login();

        this.mvc
                .perform(
                        patch(this.route + "/" + 0)
                                .header(AUTHORIZATION, "Bearer " + token.access_token())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(user)
                )
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("name").doesNotExist())
                .andExpect(jsonPath("surname").doesNotExist())
                .andExpect(jsonPath("gender").doesNotExist())
                .andExpect(jsonPath("birthdate").doesNotExist())
                .andExpect(jsonPath("workAddress").doesNotExist())
                .andExpect(jsonPath("homeAddress").doesNotExist());

        this.assertNotUpdatedInDb();
    }
}
