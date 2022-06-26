package me.github.notsaki.userapplication.domain.repository;

import me.github.notsaki.userapplication.domain.entity.response.UserListItemDto;
import me.github.notsaki.userapplication.domain.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository {
    User save(User user);
    boolean deleteById(int id);
    Optional<User> update(User user);
    List<UserListItemDto> findAll();
    Optional<User> findById(int id);
}
