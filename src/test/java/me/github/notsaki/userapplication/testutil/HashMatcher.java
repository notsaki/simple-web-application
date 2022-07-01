package me.github.notsaki.userapplication.testutil;

import java.util.regex.Pattern;

public class HashMatcher {
    public static boolean isHash(String text) {
        return Pattern.matches("^\\$2[ayb]\\$.{56}$", text);
    }
}
