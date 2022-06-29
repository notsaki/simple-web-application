package me.github.notsaki.userapplication.repository;

import me.github.notsaki.userapplication.domain.entity.response.UserListItemDto;
import me.github.notsaki.userapplication.domain.model.User;
import me.github.notsaki.userapplication.util.AppProfile;
import me.github.notsaki.userapplication.model.UserModel;
import me.github.notsaki.userapplication.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


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
    public boolean deleteById(int id) {
        return Optional.ofNullable(this.entityManager.find(UserModel.class, id))
                .map(user -> {
                    this.entityManager.remove(user);
                    return true;
                })
                .orElse(false);
    }

    @Override
    @Transactional
    public Optional<User> update(User user) {
        return Optional.ofNullable(this.entityManager.find(UserModel.class, user.getId()))
                .map(u -> this.entityManager.merge(user));
    }

    @Override
    public List<UserListItemDto> findAll() {
        return this.entityManager
                .createQuery("""
                        SELECT
                            new me.github.notsaki.userapplication.entity.response.UserListItemDtoEntity(
                                u.id,
                                u.name,
                                u.surname
                            )
                        FROM UserModel u
                    """,
                        UserListItemDto.class
                )
                .getResultList();
    }

    @Override
    public Optional<User> findById(int id) {
        return Optional.ofNullable(this.entityManager.find(UserModel.class, id));
    }
}
