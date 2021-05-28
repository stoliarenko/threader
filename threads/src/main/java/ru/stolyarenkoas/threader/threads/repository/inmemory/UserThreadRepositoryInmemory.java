package ru.stolyarenkoas.threader.threads.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.stolyarenkoas.threader.threads.model.UserThread;
import ru.stolyarenkoas.threader.threads.repository.api.UserThreadRepository;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Temporary implementation of a repository.
 * Backed by a map.
 *
 * @author Alexander Stolyarenko (28.05.2021)
 */
@Repository
public class UserThreadRepositoryInmemory implements UserThreadRepository {

    /**
     * Map for storing user threads.
     */
    @Nonnull
    private final Map<String, UserThread> repository = new HashMap<>();

    /**
     * Constructor that also adds some basic demo values to repository.
     */
    public UserThreadRepositoryInmemory() {
        final UserThread demoThread = new UserThread("Demo-thread-text", "demo-thread-user");
        repository.put(demoThread.getId(), demoThread);
    }

    @Override
    public void create(@Nonnull UserThread userThread) throws IllegalArgumentException {
        if (repository.containsKey(userThread.getId())) {
            throw new IllegalArgumentException("User thread with specified key is already exists");
        }
        repository.put(userThread.getId(), userThread);
    }

    @Override
    public void delete(@Nonnull String userThreadId) {
        repository.remove(userThreadId);
    }

    @Override
    public List<UserThread> getAll() {
        return List.copyOf(repository.values());
    }

}
