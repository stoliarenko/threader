package ru.stolyarenkoas.threader.threads.repository.api;

import ru.stolyarenkoas.threader.threads.model.UserThread;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * Repository of text threads created by users.
 *
 * @author Alexander Stolyarenko (28.05.2021)
 */
public interface UserThreadRepository {

    /**
     * Create new user thread record.
     *
     * @param userThread -
     * @throws IllegalArgumentException if thread with specified key already exists.
     */
    void create(@Nonnull UserThread userThread) throws IllegalArgumentException;

    /**
     * Removes a record of user thread if it exists.
     *
     * @param userThreadId - id of a record to remove.
     */
    void delete(@Nonnull String userThreadId);

    /**
     * Demo method used in development process.
     *
     * @return all the user threads.
     */
    List<UserThread> getAll();

}
