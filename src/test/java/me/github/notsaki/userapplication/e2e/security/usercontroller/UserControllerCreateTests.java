package me.github.notsaki.userapplication.e2e.security.usercontroller;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class UserControllerCreateTests extends UserControllerWithBodyTest {
    public UserControllerCreateTests() {
        super(MockMvcRequestBuilders::post, "/user");
    }
}