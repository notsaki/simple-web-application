package me.github.notsaki.userapplication.e2e.user;

import me.github.notsaki.userapplication.domain.entity.response.UserListItemDto;
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
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserControllerFindAllTests extends E2eSetup {
    private final String route = "/user";
    private List<User> createdUsers;

    @Autowired
    @Qualifier(AppProfile.IMPL)
    private UserRepository userRepository;

    @Before
    public void saveUser() {
        this.createdUsers = List.of(
                this.userRepository.save(ReceiveUserStub.one().toUser()),
                this.userRepository.save(ReceiveUserStub.one().toUser()),
                this.userRepository.save(ReceiveUserStub.one().toUser())
        );
    }

    @Test
    public void sendingRequest_shouldReturnTheCreatedUsers() throws Exception {
        var token = this.login();

        var body = this.mvc
                .perform(
                        get(this.route)
                                .header(AUTHORIZATION, "Bearer " + token.access_token())
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        var receivedUser = Arrays
                .stream(this.objectMapper.readValue(body.getResponse().getContentAsString(), UserListItemDto[].class))
                .toList();

        var savedUsers = this.createdUsers
                .stream()
                .map(User::toFullName)
                .toList();

        Assert.assertEquals(savedUsers, receivedUser);
    }
}
