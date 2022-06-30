package me.github.notsaki.userapplication.domain.util;

public interface PasswordEncoder {
    String encode(String raw);
    boolean verify(String raw, String encoded);
}
