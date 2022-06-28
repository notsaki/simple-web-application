package me.github.notsaki.userapplication.e2e.security.usercontroller;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class UserControllerFindByIdTests extends UserControllerWithoutBodyRequestTests {
    public UserControllerFindByIdTests() {
        super(MockMvcRequestBuilders::get, "/user/1");
    }
}
