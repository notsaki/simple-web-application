package me.github.notsaki.userapplication.domain.repository;

import me.github.notsaki.userapplication.domain.model.User;

import java.util.List;

public interface UserRepository {
    User save(User user);
    int deleteById(int id);
    List<User> findAll();
}
