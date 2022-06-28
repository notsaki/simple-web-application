package me.github.notsaki.userapplication.e2e.security.usercontroller;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class UserControllerUpdateByIdTests extends UserControllerWithBodyTest {
    public UserControllerUpdateByIdTests() {
        super(MockMvcRequestBuilders::patch, "/user");
    }
}
