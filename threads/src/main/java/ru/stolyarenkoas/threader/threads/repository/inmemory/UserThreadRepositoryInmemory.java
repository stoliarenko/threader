package ru.stolyarenkoas.threader.threads.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.stolyarenkoas.threader.threads.model.UserThread;
import ru.stolyarenkoas.threader.threads.repository.api.UserThreadRepository;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

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

    @Nonnull
    @Override
    public String create(@Nonnull final UserThread userThread) {
        final String newId = UUID.randomUUID().toString();
        userThread.setId(newId);
        repository.put(newId, userThread);
        return newId;
    }

    @Nullable
    @Override
    public UserThread get(@Nonnull final String id) {
        if (repository.containsKey(id)) {
            return repository.get(id);
        }
        return null;
    }

    @Nonnull
    @Override
    public Set<UserThread> getByUserId(@Nonnull final String userId) {
        return repository.values().stream()
                .filter(ut -> ut.getUser().equals(userId))
                .collect(Collectors.toUnmodifiableSet());
    }

    @Override
    public void delete(@Nonnull final String userThreadId) {
        repository.remove(userThreadId);
    }

    @Override
    public List<UserThread> getAll() {
        return List.copyOf(repository.values());
    }

}
