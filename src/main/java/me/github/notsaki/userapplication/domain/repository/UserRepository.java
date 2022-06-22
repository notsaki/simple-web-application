package me.github.notsaki.userapplication.domain.repository;

import me.github.notsaki.userapplication.domain.entity.response.UserListItemDto;
import me.github.notsaki.userapplication.domain.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {
    User save(User user);
    void deleteById(int id);
    List<UserListItemDto> findAll();
    User findById(int id);
}
