package me.github.notsaki.userapplication.repository.impl;

import me.github.notsaki.userapplication.domain.model.User;
import me.github.notsaki.userapplication.domain.repository.UserRepository;

import javax.persistence.EntityManager;
import java.util.List;


public class UserRepositoryImpl implements UserRepository {

    private final EntityManager entityManager;

    public UserRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public User save(User user) {
        this.entityManager.persist(user);
        return user;
    }

    @Override
    public int deleteById(int id) {
        return this.entityManager
                .createQuery("DELETE FROM users WHERE id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public List<User> findAll() {
        return this.entityManager
                .createQuery("from User", User.class)
                .getResultList();
    }
}
