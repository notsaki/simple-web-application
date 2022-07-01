package me.github.notsaki.userapplication.service;

import me.github.notsaki.userapplication.domain.repository.AdminRepository;
import me.github.notsaki.userapplication.domain.service.AdminService;
import me.github.notsaki.userapplication.infrastructure.util.PasswordHasher;
import me.github.notsaki.userapplication.testutil.HashMatcher;
import me.github.notsaki.userapplication.util.AppProfile;
import me.github.notsaki.userapplication.util.stub.admin.AdminStub;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static me.github.notsaki.userapplication.testutil.HashMatcher.isHash;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
public class AdminServiceTests {

    @Autowired
    @Qualifier(AppProfile.IMPL)
    private AdminRepository adminRepository;

    @Autowired
    private AdminService adminService;

    private final PasswordHasher passwordHasher = new PasswordHasher();

    @Test
    public void onSavePasswordShouldBeStoredHashed() {
        var admin = AdminStub.one();
        admin.setUsername("admin2");

        this.adminService.save(admin);

        var hash = this.adminService
                .findByUsername(admin.getUsername())
                .orElseThrow()
                .getPassword();

        Assert.assertTrue(isHash(hash));
    }

    @Test
    public void onSavePasswordShouldMatchTheHash() {
        var admin = AdminStub.one();
        admin.setUsername("admin2");

        this.adminService.save(admin);

        var hash = this.adminService
                .findByUsername(admin.getUsername())
                .orElseThrow()
                .getPassword();

        var match = this.passwordHasher.matches(AdminStub.one().getPassword(), hash);

        Assert.assertTrue(match);
    }
}
