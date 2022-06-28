package me.github.notsaki.userapplication.e2e.security.usercontroller;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class UserControllerDeleteByIdTests extends UserControllerWithoutBodyRequestTests {
    public UserControllerDeleteByIdTests() {
        super(MockMvcRequestBuilders::delete, "/user/1");
    }
}
