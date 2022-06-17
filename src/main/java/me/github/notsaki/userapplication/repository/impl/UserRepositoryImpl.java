package me.github.notsaki.userapplication.repository.impl;

import me.github.notsaki.userapplication.domain.model.User;
import me.github.notsaki.userapplication.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;


@Repository
@Qualifier("impl")
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
    public int deleteById(int id) {
        return this.entityManager
                .createQuery("DELETE FROM User u WHERE u.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    @Transactional
    public List<User> findAll() {
        return this.entityManager
                .createQuery("SELECT u FROM User u", User.class)
                .getResultList();
    }
}
