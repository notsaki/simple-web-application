package me.github.notsaki.userapplication.domain.service;

import me.github.notsaki.userapplication.domain.data.receive.Credentials;
import me.github.notsaki.userapplication.domain.model.Admin;
import me.github.notsaki.userapplication.domain.util.PasswordEncoder;

import java.util.Optional;

public class SecurityService {
    private final PasswordEncoder passwordEncoder;
    private final AdminService adminService;

    public SecurityService(PasswordEncoder passwordEncoder, AdminService adminService) {
        this.passwordEncoder = passwordEncoder;
        this.adminService = adminService;
    }

    /**
     * Authenticates an admin by looking up the username and then matching the given raw password with the save hash.
     * @param credentials Admin username and password.
     * @return The authenticated admin user or empty if authentication fails.
     */
    public Optional<Admin> authenticate(Credentials credentials) {
        return this.adminService
                .findByUsername(credentials.username())
                .filter(admin -> this.passwordEncoder.verify(credentials.password(), admin.getPassword()));
    }
}
