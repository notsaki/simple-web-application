package me.github.notsaki.userapplication.service.mock;

import me.github.notsaki.userapplication.domain.dto.receive.ReceiveUserDto;
import me.github.notsaki.userapplication.domain.dto.response.ResponseUserDto;
import me.github.notsaki.userapplication.domain.model.AppProfile;
import me.github.notsaki.userapplication.domain.model.User;
import me.github.notsaki.userapplication.domain.service.UserService;
import me.github.notsaki.userapplication.stub.UserStub;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier(AppProfile.MOCK)
public class UserServiceMock implements UserService {
	@Override
	public ResponseUserDto save(ReceiveUserDto userDto) {
		return UserStub.ForResponse();
	}

	@Override
	public void deleteById(int id) {
	}

	@Override
	public List<ResponseUserDto> findAll() {
		return UserStub.ListForResponse();
	}
}
