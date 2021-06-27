package ru.stolyarenkoas.threader.threads.repository.api;

import ru.stolyarenkoas.threader.threads.model.UserThread;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;

/**
 * Repository of text threads created by users.
 *
 * @author Alexander Stolyarenko (28.05.2021)
 */
public interface UserThreadRepository {

    /**
     * Create new user thread record.
     *
     * @param userThread - new user thread.
     * @return identifier of a created user thread.
     */
    @Nonnull
    String create(@Nonnull UserThread userThread);

    /**
     * Retrieves user thread that has specified identifier.
     *
     * @param id identifier of a user thread.
     * @return user thread that has spevidied identifier or {@code null} if no matches found.
     */
    @Nullable
    UserThread get(@Nonnull String id);

    /**
     * Retrieves user threads of a user that has specified identifier.
     *
     * @param userId user identifier.
     * @return set of user threads or empty set if there are no threads of corresponding user.
     */
    @Nonnull
    Set<UserThread> getByUserId(@Nonnull String userId);

    /**
     * Removes a record of user thread if it exists.
     *
     * @param userThreadId - identifier of a record to remove.
     */
    void delete(@Nonnull String userThreadId);

    /**
     * Demo method used in development process.
     *
     * @return all the user threads.
     */
    List<UserThread> getAll();

}
