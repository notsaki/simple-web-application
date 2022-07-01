package me.github.notsaki.userapplication.util;

import org.junit.Assert;
import org.junit.Test;

import static me.github.notsaki.userapplication.testutil.HashMatcher.isHash;

public class HashMatcherTests {

    @Test
    public void withTextShouldReturnFalse() {
        Assert.assertFalse(isHash("text"));
    }

    @Test
    public void withHashShouldReturnFalse() {
        Assert.assertTrue(isHash("$2a$10$b1zD10ObeiCZxEFQbj7PqOUCX15pZ4vIMb/kMkQOPl01KdfXxzTg6"));
    }
}
