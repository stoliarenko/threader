package ru.stolyarenkoas.threader.users.service;

import org.springframework.stereotype.Service;
import ru.stolyarenkoas.threader.users.exception.InvalidDataException;
import ru.stolyarenkoas.threader.users.exception.UserNotFoundException;
import ru.stolyarenkoas.threader.users.model.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Service for managing users.
 *
 * @author Alexander Stoliarenko (29.09.2021)
 */
@Service
public interface UserService {

    /**
     * Saves user to database.
     *
     * @param user user object.
     * @return saved user object with updated identifier.
     * @throws InvalidDataException if given user is {@code null}.
     */
    @Nonnull
    User create(@Nullable User user) throws InvalidDataException;

    /**
     * Retrieves user by identifier.
     *
     * @param userId identifier of a user.
     * @return user.
     * @throws UserNotFoundException if user with given identifier does not exist.
     */
    @Nonnull
    User getById(@Nullable String userId) throws UserNotFoundException;

    /**
     * Updates user data.
     *
     * @param user user data to update.
     * @throws UserNotFoundException if user with given identifier does not exist.
     */
    void update(@Nullable User user) throws UserNotFoundException;

    /**
     * Removes user by identifier.
     *
     * @param userId identifier of a user.
     * @throws UserNotFoundException if user with given identifier does not exist.
     */
    void remove(@Nullable String userId) throws UserNotFoundException;

    /**
     * Retrieves user by given active session identifier.
     *
     * @param sessionId session identifier
     * @return {@link User} or {@code null} if none found.
     */
    @Nullable
    User getBySessionId(@Nullable String sessionId);

}
