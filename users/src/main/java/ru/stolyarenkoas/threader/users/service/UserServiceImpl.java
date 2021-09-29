package ru.stolyarenkoas.threader.users.service;

import org.springframework.stereotype.Service;
import ru.stolyarenkoas.threader.users.model.Role;
import ru.stolyarenkoas.threader.users.model.User;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Base implementation for user management service.
 *
 * @author Alexander Stoliarenko (29.09.2021)
 */
@Service
public class UserServiceImpl implements UserService {

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
        final User user = new User(userName, userRoles);
        user.setId(userId);
        return user;
    }

}
