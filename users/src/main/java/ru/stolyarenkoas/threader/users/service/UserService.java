package ru.stolyarenkoas.threader.users.service;

import ru.stolyarenkoas.threader.users.model.User;

import javax.annotation.Nullable;

/**
 * Service for managing users.
 *
 * @author Alexander Stoliarenko (29.09.2021)
 */
public interface UserService {

    /**
     * Retrieves user by given active session identifier.
     *
     * @param sessionId session identifier
     * @return {@link User} jr {@code null} if none found.
     */
    @Nullable
    User getBySessionId(@Nullable String sessionId);

}
