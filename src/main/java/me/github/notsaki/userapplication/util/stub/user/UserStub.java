package me.github.notsaki.userapplication.util.stub.user;

import me.github.notsaki.userapplication.infrastructure.model.UserModel;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class UserStub {
	public static UserModel One() {
		var user = ReceiveUserStub.one().toUser();
		user.setId(1);
		return user;
	}

	public static List<UserModel> list() {
		AtomicInteger count = new AtomicInteger();
		return ReceiveUserStub.list()
				.stream()
				.map(user -> {
					var newUser = user.toUser();
					newUser.setId(count.incrementAndGet());
					return newUser;
				})
				.toList();
	}
}
