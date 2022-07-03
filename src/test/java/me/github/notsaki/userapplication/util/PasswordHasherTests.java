package me.github.notsaki.userapplication.util;

import me.github.notsaki.userapplication.infrastructure.util.PasswordHasher;
import org.junit.Assert;
import org.junit.Test;

import static me.github.notsaki.userapplication.testutil.HashMatcher.isHash;

public class PasswordHasherTests {
    private final PasswordHasher encoder = new PasswordHasher();

    @Test
    public void whenEncodingShouldNotReturnTheSamePassword() {
        var password = "Password";
        var hash = this.encoder.encode(password);

        Assert.assertNotEquals(password, hash);
    }

    @Test
    public void whenEncodingShouldHash() {
        var password = "Password";
        var hash = this.encoder.encode(password);

        Assert.assertTrue(isHash(hash));
    }

    @Test
    public void whenVerifyingSamePasswordShouldReturnTrue() {
        var password = "Password";
        var hash = this.encoder.encode(password);

        var result = this.encoder.verify(password, hash);

        Assert.assertTrue(result);
    }

    @Test
    public void whenVerifyingSamePasswordShouldReturnFalse() {
        var password = "Password";
        var hash = this.encoder.encode(password);

        var result = this.encoder.verify("password", hash);

        Assert.assertFalse(result);
    }
}
