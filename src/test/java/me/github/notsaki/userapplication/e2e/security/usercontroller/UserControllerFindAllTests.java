package me.github.notsaki.userapplication.e2e.security.usercontroller;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


public class UserControllerFindAllTests extends UserControllerWithoutBodyRequestTests {
    public UserControllerFindAllTests() {
        super(MockMvcRequestBuilders::get, "/user/1");
    }
}
