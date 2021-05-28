package ru.stolyarenkoas.threader.threads.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.stolyarenkoas.threader.threads.model.UserThread;
import ru.stolyarenkoas.threader.threads.repository.api.UserThreadRepository;
import ru.stolyarenkoas.threader.threads.service.api.UserThreadManager;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * Basic implementation of a service to manage user threads.
 *
 * @author Alexander Stolyarenko (28.05.2021)
 */
@Service
@RequiredArgsConstructor
public class UserThreadManagerBasic implements UserThreadManager {

    /**
     * Repository of user threads.
     */
    @Nonnull
    private final UserThreadRepository userThreadRepository;

    @Override
    public void create(@Nonnull UserThread userThread) {
        userThreadRepository.create(userThread);
    }

    @Override
    public void delete(@Nonnull String userThreadId) {
        userThreadRepository.delete(userThreadId);
    }

    @Override
    public List<UserThread> getAll() {
        return List.copyOf(userThreadRepository.getAll());
    }

}
