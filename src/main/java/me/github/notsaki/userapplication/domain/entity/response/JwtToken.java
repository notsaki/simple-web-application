package me.github.notsaki.userapplication.domain.entity.response;

public interface JwtToken {
	String access_token();
	String refresh_token();
}
