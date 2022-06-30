package me.github.notsaki.userapplication.domain.service;

import me.github.notsaki.userapplication.domain.data.receive.Credentials;
import me.github.notsaki.userapplication.domain.repository.AdminRepository;
import me.github.notsaki.userapplication.domain.util.PasswordEncoder;

public class SecurityService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder encoder;
    private final SessionService securityService;

    public SecurityService(AdminRepository adminRepository, PasswordEncoder encoder, SessionService securityService) {
        this.adminRepository = adminRepository;
        this.encoder = encoder;
        this.securityService = securityService;
    }

    public boolean authenticate(Credentials credentials) {
        return this.adminRepository
                .findByUsername(credentials.username())
                .map(user -> this.encoder.verify(credentials.password(), user.getPassword()))
                .orElse(false);
    }

    boolean authorise() {
        return this.securityService.verifySession();
    }
}
