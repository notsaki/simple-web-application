package me.github.notsaki.userapplication.domain.util;

import java.util.Optional;

public interface TokenUtil {
    String create(TokenType type, String username, String issuer);
    Optional<String> extractUsername(String token);
}
