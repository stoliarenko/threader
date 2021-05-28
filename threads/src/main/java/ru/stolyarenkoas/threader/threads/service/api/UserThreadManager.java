package ru.stolyarenkoas.threader.threads.service.api;

import ru.stolyarenkoas.threader.threads.model.UserThread;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * Service for managing user threads.
 *
 * @author Alexander Stolyarenko (28.05.2021)
 */
public interface UserThreadManager {

    /**
     * Creates new user thread.
     *
     * @param userThread - new user thread.
     */
    void create(@Nonnull UserThread userThread);

    /**
     * Removes user thread if it exists.
     *
     * @param userThreadId - id of user thread.
     */
    void delete(@Nonnull String userThreadId);

    /**
     * Demo method used in development process.
     *
     * @return all the user threads.
     */
    List<UserThread> getAll();

}
