package me.github.notsaki.userapplication.util.stub.admin;

import me.github.notsaki.userapplication.domain.model.Admin;

public class AdminStub {
	public static Admin one() {
		return new Admin("admin", "admin");
	}
}
