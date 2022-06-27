package me.github.notsaki.userapplication.util.mock.service;

import me.github.notsaki.userapplication.domain.model.Admin;
import me.github.notsaki.userapplication.domain.service.AdminService;
import me.github.notsaki.userapplication.util.stub.admin.AdminStub;

public class AdminServiceMock implements AdminService {
	@Override
	public Admin save(Admin admin) {
		return AdminStub.One();
	}
}
