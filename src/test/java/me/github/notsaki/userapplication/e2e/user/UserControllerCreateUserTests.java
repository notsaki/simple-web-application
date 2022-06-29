package me.github.notsaki.userapplication.e2e.user;

import me.github.notsaki.userapplication.domain.entity.ValidationMessage;
import me.github.notsaki.userapplication.domain.entity.error.ValidationInfo;
import me.github.notsaki.userapplication.domain.entity.receive.ReceiveUserDto;
import me.github.notsaki.userapplication.domain.entity.response.ResponseUserDto;
import me.github.notsaki.userapplication.domain.model.Gender;
import me.github.notsaki.userapplication.domain.repository.UserRepository;
import me.github.notsaki.userapplication.e2e.E2eSetup;
import me.github.notsaki.userapplication.util.AppProfile;
import me.github.notsaki.userapplication.util.Routes;
import me.github.notsaki.userapplication.util.entityreversemapper.UserReverseMapper;
import me.github.notsaki.userapplication.util.stub.user.ReceiveUserStub;
import org.junit.Assert;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserControllerCreateUserTests extends E2eSetup {
    private final String route = Routes.user;

    @Autowired
    @Qualifier(AppProfile.IMPL)
    private UserRepository userRepository;

    private void assertEmptyDb() {
        var users = this.userRepository.findAll();
        Assert.assertEquals(0, users.size());
    }

    @Test
    public void sendingRequestWithValidBody_shouldReturnCreated() throws Exception {
        var userStub = ReceiveUserStub.one();
        var user = this.objectMapper.writeValueAsString(userStub);
        var token = this.login();

        var body = this.mvc
                .perform(
                        post(this.route)
                                .header(AUTHORIZATION, "Bearer " + token.access_token())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(user)
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        var receivedUser = this.objectMapper.readValue(body.getResponse().getContentAsString(), ResponseUserDto.class);
        var dbUser = this.userRepository.findById(receivedUser.id()).orElseThrow().toResponse();

        Assert.assertEquals(userStub, UserReverseMapper.fromResponseToReceive(receivedUser));
        Assert.assertEquals(receivedUser, dbUser);
    }

    @Test
    public void sendingInvalidBodyFormat_shouldReturnBadRequestAndNotSaveAnyUser() throws Exception {
        var token = this.login();

        this.mvc
                .perform(
                        post(this.route)
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

        this.assertEmptyDb();
    }

    @Test
    public void sendingMissingBodyProperties_shouldReturnUnprocessableAndNotSaveAnyUser() throws Exception {
        var token = this.login();

        this.mvc
                .perform(
                        post(this.route)
                                .header(AUTHORIZATION, "Bearer " + token.access_token())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{}")
                )
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("name").doesNotExist())
                .andExpect(jsonPath("surname").doesNotExist())
                .andExpect(jsonPath("gender").doesNotExist())
                .andExpect(jsonPath("birthdate").doesNotExist())
                .andExpect(jsonPath("workAddress").doesNotExist())
                .andExpect(jsonPath("homeAddress").doesNotExist())
                // TODO: Find a more elegant way to test.
                .andExpect(jsonPath("$[*].instructions").value(containsInAnyOrder(ValidationMessage.notBlank, ValidationMessage.notBlank, ValidationMessage.notBlank, ValidationMessage.notBlank)))
                .andExpect(jsonPath("$[*].targetLocation").value(containsInAnyOrder("name", "surname", "gender", "birthdate")));

        this.assertEmptyDb();
    }

    private String getInstructionByTarget(List<ValidationInfo> info, String property) {
        return info
                .stream()
                .filter(error -> error.getTargetLocation().equals(property))
                .findFirst()
                .orElseThrow()
                .getInstructions();
    }

    @Test
    public void sendingInvalidBodyFormatValues_shouldReturnUnprocessableAndNotSaveAnyUser() throws Exception {
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

        var body = this.mvc
                .perform(
                        post(this.route)
                                .header(AUTHORIZATION, "Bearer " + token.access_token())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(user)
                )
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("name").doesNotExist())
                .andExpect(jsonPath("surname").doesNotExist())
                .andExpect(jsonPath("gender").doesNotExist())
                .andExpect(jsonPath("birthdate").doesNotExist())
                .andExpect(jsonPath("workAddress").doesNotExist())
                .andExpect(jsonPath("homeAddress").doesNotExist())
                .andExpect(jsonPath("$[*].targetLocation").value(containsInAnyOrder("name", "surname", "birthdate", "workAddress", "homeAddress")))
                .andReturn();

        var errors = Arrays
                .stream(this.objectMapper.readValue(body.getResponse().getContentAsString(), ValidationInfo[].class))
                .toList();

        Assert.assertEquals(ValidationMessage.nameLength, this.getInstructionByTarget(errors, "name"));
        Assert.assertEquals(ValidationMessage.nameLength, this.getInstructionByTarget(errors, "surname"));
        Assert.assertEquals(ValidationMessage.addressLength, this.getInstructionByTarget(errors, "workAddress"));
        Assert.assertEquals(ValidationMessage.addressLength, this.getInstructionByTarget(errors, "homeAddress"));
        Assert.assertEquals(ValidationMessage.datePast, this.getInstructionByTarget(errors, "birthdate"));

        this.assertEmptyDb();
    }

    @Test
    public void sendingInvalidGenderValue_shouldReturnBadRequestAndNotSaveAnyUser() throws Exception {
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
                        post(this.route)
                                .header(AUTHORIZATION, "Bearer " + token.access_token())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(user)
                )
                .andExpect(status().isBadRequest())
                .andReturn();

        this.assertEmptyDb();
    }
}
