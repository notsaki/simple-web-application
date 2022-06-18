package me.github.notsaki.userapplication.util.stub.user;

import me.github.notsaki.userapplication.domain.model.User;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class UserStub {
	public static User One() {
		var user = ReceiveUserStub.One().toUser();
		user.setId(1);
		return user;
	}

	public static List<User> List() {
		AtomicInteger count = new AtomicInteger();
		return ReceiveUserStub.List()
				.stream()
				.map(user -> {
					var newUser = user.toUser();
					newUser.setId(count.incrementAndGet());
					return newUser;
				})
				.toList();
	}




}
