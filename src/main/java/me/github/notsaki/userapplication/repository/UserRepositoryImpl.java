package me.github.notsaki.userapplication.repository;

import me.github.notsaki.userapplication.domain.entity.response.UserListItemDto;
import me.github.notsaki.userapplication.domain.model.AppProfile;
import me.github.notsaki.userapplication.domain.model.User;
import me.github.notsaki.userapplication.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;


@Repository
@Profile(AppProfile.IMPL)
@Qualifier(AppProfile.IMPL)
@Primary
public class UserRepositoryImpl implements UserRepository {

    private final EntityManager entityManager;

    public UserRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public User save(User user) {
        this.entityManager.persist(user);
        return user;
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        var user = this.entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    @Transactional
    public List<UserListItemDto> findAll() {
        return this.entityManager
                .createQuery("SELECT new me.github.notsaki.userapplication.domain.entity.response.UserListItemDto(u.id, u.name, u.surname) FROM User u", UserListItemDto.class)
                .getResultList();
    }

    @Override
    @Transactional
    public User findById(int id) {
        return this.entityManager.find(User.class, id);
    }
}
