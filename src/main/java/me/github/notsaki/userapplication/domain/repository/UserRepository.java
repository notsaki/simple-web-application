package me.github.notsaki.userapplication.domain.repository;

import me.github.notsaki.userapplication.domain.data.response.UserListItemDto;
import me.github.notsaki.userapplication.domain.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository {
    /**
     * Save a new user.
     * @param user The user data.
     * @return The saved user.
     */
    User save(User user);

    /**
     * Remove a user.
     * @param id The user ID to look up for.
     * @return True if the operation was successful or false otherwise.
     */
    boolean deleteById(int id);

    /**
     * Updates user information matching.
     * @param user The new user entity.
     * @return The updated user if successful or empty if no matching user was found.
     */
    Optional<User> update(User user);

    /**
     * Get all users.
     * @return A list of all the users containing only their IDs, names and surnames.
     */
    List<UserListItemDto> findAll();

    /**
     * Find a user matching the ID.
     * @param id The ID to look for.
     * @return A user if was found or empty otherwise.
     */
    Optional<User> findById(int id);
}
