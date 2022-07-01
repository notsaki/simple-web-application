package me.github.notsaki.userapplication.repository;

import me.github.notsaki.userapplication.domain.repository.AdminRepository;
import me.github.notsaki.userapplication.util.AppProfile;
import me.github.notsaki.userapplication.util.stub.admin.AdminStub;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
public class AdminRepositoryImplTests {

	@Autowired
	@Qualifier(AppProfile.IMPL)
	private AdminRepository adminRepository;

	@Test
	public void onSaveShouldReturnTheSameUser() {
		var admin = AdminStub.one();
		var result = this.adminRepository.save(admin);

		Assert.assertEquals(result, admin);
	}

	@Test
	public void onSaveDuplicateShouldThrow() {
		var admin = AdminStub.one();
		this.adminRepository.save(admin);

		Assertions.assertThrows(ConstraintViolationException.class, () -> this.adminRepository.save(admin));
	}
}
