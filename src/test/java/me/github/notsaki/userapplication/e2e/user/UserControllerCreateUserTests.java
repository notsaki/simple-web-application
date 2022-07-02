package me.github.notsaki.userapplication.e2e.user;

import me.github.notsaki.userapplication.domain.data.ValidationMessage;
import me.github.notsaki.userapplication.domain.data.error.ValidationInfo;
import me.github.notsaki.userapplication.domain.data.response.ResponseUserDto;
import me.github.notsaki.userapplication.domain.model.Gender;
import me.github.notsaki.userapplication.domain.repository.UserRepository;
import me.github.notsaki.userapplication.e2e.E2eSetup;
import me.github.notsaki.userapplication.infrastructure.data.receive.ReceiveUserDtoEntity;
import me.github.notsaki.userapplication.util.AppProfile;
import me.github.notsaki.userapplication.util.Routes;
import me.github.notsaki.userapplication.testutil.entityreversemapper.UserReverseMapper;
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

        var body = this.mvc
                .perform(
                        withAuth(
                                post(this.route)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(user)
                        )
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
        this.mvc
                .perform(
                        withAuth(
                                post(this.route)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content("invalid_body")
                        )
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
    public void sendingMissingBodyProperties_shouldReturnBadRequestAndNotSaveAnyUser() throws Exception {
        this.mvc
                .perform(
                        withAuth(
                                post(this.route)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content("{}")
                        )
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

    private String getInstructionByTarget(List<ValidationInfo> info, String property) {
        return info
                .stream()
                .filter(error -> error.targetLocation().equals(property))
                .findFirst()
                .orElseThrow()
                .instructions();
    }

    @Test
    public void sendingInvalidBodyFormatValues_shouldReturnUnprocessableAndNotSaveAnyUser() throws Exception {
        var obj = new ReceiveUserDtoEntity(
                "",
                "",
                Gender.FEMALE,
                LocalDate.now().plusDays(1),
                "",
                ""
        );

        var user = this.objectMapper.writer().writeValueAsString(obj);
        var body = this.mvc
                .perform(
                        withAuth(
                                post(this.route)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(user)
                        )
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
                "name", stub.getName(),
            "surname", stub.getSurname(),
            "gender", "",
            "birthdate", stub.getBirthdate(),
            "workAddress", Objects.requireNonNull(stub.getWorkAddress()),
            "homeAddress", Objects.requireNonNull(stub.getHomeAddress())
        );

        var user = this.objectMapper.writer().writeValueAsString(obj);
        this.mvc
                .perform(
                        withAuth(
                                post(this.route)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(user)
                        )
                )
                .andExpect(status().isBadRequest())
                .andReturn();

        this.assertEmptyDb();
    }
}
