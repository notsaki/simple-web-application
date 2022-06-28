package me.github.notsaki.userapplication.e2e.security.usercontroller;

import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

interface RequestMethod {
    MockHttpServletRequestBuilder operation(String route);
}
