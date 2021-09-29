package ru.stolyarenkoas.threader.threads.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.stolyarenkoas.threader.threads.model.User;
import ru.stolyarenkoas.threader.threads.model.UserThread;
import ru.stolyarenkoas.threader.threads.repository.api.UserThreadRepository;
import ru.stolyarenkoas.threader.threads.service.api.UserThreadManager;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Basic implementation of a service to manage user threads.
 *
 * @author Alexander Stolyarenko (28.05.2021)
 */
@Service
@RequiredArgsConstructor
public class UserThreadManagerBasic implements UserThreadManager {

    /**
     * TODO: move to separate service.
     * TODO: refactor to create RestTemplate.
     */
    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    /**
     * Repository of user threads.
     */
    @Nonnull
    private final UserThreadRepository userThreadRepository;

    @Nonnull
    @Override
    public String create(@Nonnull final UserThread userThread) {
        return userThreadRepository.create(userThread);
    }

    @Nullable
    @Override
    public UserThread get(@Nonnull final String id) {
        return userThreadRepository.get(id);
    }

    @Nonnull
    @Override
    public Set<UserThread> getByUserId(@Nonnull final String userId) {
        return userThreadRepository.getByUserId(getCurrentUserId());
    }

    /**
     * Retrieves identifier of currently authenticated user.
     * TODO: move to separate service.
     */
    @Nonnull
    private String getCurrentUserId() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        final User currentUser = restTemplate.getForObject("localhost:8080/users/service/1", User.class);
        Objects.requireNonNull(currentUser);
        return currentUser.getId();
    }

    @Override
    public void delete(@Nonnull final String userThreadId) {
        userThreadRepository.delete(userThreadId);
    }

    @Override
    public List<UserThread> getAll() {
        return List.copyOf(userThreadRepository.getAll());
    }

}
