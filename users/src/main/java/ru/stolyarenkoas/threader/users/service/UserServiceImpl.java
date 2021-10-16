package ru.stolyarenkoas.threader.users.service;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.stolyarenkoas.threader.users.exception.InvalidDataException;
import ru.stolyarenkoas.threader.users.exception.UserNotFoundException;
import ru.stolyarenkoas.threader.users.model.Role;
import ru.stolyarenkoas.threader.users.model.User;
import ru.stolyarenkoas.threader.users.repository.UserRepository;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Base implementation for user management service.
 *
 * @author Alexander Stoliarenko (29.09.2021)
 */
@Service
public class UserServiceImpl implements UserService {

    /**
     * Underlying repository.
     */
    @Setter(onMethod_ = @Autowired)
    private UserRepository repository;

    @Nonnull
    @Override
    public User create(@Nullable final User user) throws InvalidDataException {
        if (Objects.isNull(user)) {
            throw new InvalidDataException("Given user object is null");
        }
        user.setId(UUID.randomUUID().toString());
        return repository.save(user);
    }

    @Nonnull
    @Override
    public User getById(@Nullable final String userId) throws UserNotFoundException {
        if (Objects.isNull(userId)) {
            throw new UserNotFoundException(null);
        }
        return repository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }

    @Override
    public void update(@Nullable final User user) throws UserNotFoundException {
        if (Objects.isNull(user)) {
            throw new UserNotFoundException(null);
        }
        final String userId = user.getId();
        if (Objects.isNull(userId) || !repository.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }
        repository.save(user);
    }

    @Override
    public void remove(@Nullable final String userId) throws UserNotFoundException {
        if (Objects.isNull(userId)) {
            throw new UserNotFoundException(null);
        }
        repository.deleteById(userId);
    }

    @Nullable
    @Override
    public User getBySessionId(@Nullable String sessionId) {
        return getStubUser();
    }

    /**
     * Creates a new test user.
     * FIXME: Test purposes method.
     */
    private User getStubUser() {
        final String userId = UUID.randomUUID().toString();
        final String userName = "Test-User";
        final List<Role> userRoles = Collections.singletonList(Role.ADMINISTRATOR);
        final User user = new User();
        user.setName(userName);
        user.setRoles(userRoles);
        user.setId(userId);
        return user;
    }

}
