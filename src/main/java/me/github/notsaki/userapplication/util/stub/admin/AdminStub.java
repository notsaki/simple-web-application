package me.github.notsaki.userapplication.util.stub.admin;

import me.github.notsaki.userapplication.infrastructure.model.AdminModel;

public class AdminStub {
	public static AdminModel one() {
		return new AdminModel("admin", "admin");
	}
}
