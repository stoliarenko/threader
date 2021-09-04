package ru.stolyarenkoas.threader.threads.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.stolyarenkoas.threader.threads.controller.exception.UserHasNoUserThreadsException;
import ru.stolyarenkoas.threader.threads.controller.exception.UserThreadNotFoundException;
import ru.stolyarenkoas.threader.threads.model.UserThread;
import ru.stolyarenkoas.threader.threads.service.api.UserThreadManager;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * REST service access point to manage user threads.
 *
 * @author Alexander Stolyarenko (28.05.2021)
 */
@RestController
@RequestMapping(path = "/userThreads")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserThreadController {

    /**
     * Service to manage user threads.
     */
    @Nonnull
    private final UserThreadManager userThreadManager;

    /**
     * Creates a new user thread.
     *
     * @param userThread user thread data.
     * @return created user thread.
     */
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST, path = "/create")
    public UserThread create(@RequestBody @Nonnull UserThread userThread) {
        final String userThreadId = userThreadManager.create(userThread);
        userThread.setId(userThreadId);
        return userThread;
    }

    /**
     * Retrieves a user thread by it's identifier.
     *
     * @param id identifier of a user thread.
     * @return user thread that has specified identifier.
     * @throws UserThreadNotFoundException if there is no user thread corresponding to specified id.
     */
    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public UserThread get(@PathVariable @Nonnull final String id) {
        final UserThread userThread = userThreadManager.get(id);
        if (Objects.isNull(userThread)) {
            throw new UserThreadNotFoundException(id);
        }
        return userThread;
    }

    /**
     * Retrieves user threads of a user that has specified identifier.
     *
     * @param userId existing user identifier.
     * @return set of user threads.
     * @throws UserHasNoUserThreadsException if no threads found for specified user.
     */
    @RequestMapping(method = RequestMethod.GET, path = "/user/{userId}")
    public Set<UserThread> getByUserId(@PathVariable @Nonnull final String userId) {
        final Set<UserThread> foundThreads = userThreadManager.getByUserId(userId);
        if (foundThreads.isEmpty()) {
            throw new UserHasNoUserThreadsException(userId);
        }
        return foundThreads;
    }

    /**
     * Deletes user thread that has specified identifier.
     *
     * @param userThreadId identifier of existing user thread.
     */
    @RequestMapping(method = RequestMethod.DELETE, path = "/delete/{userThreadId}")
    public void delete(@PathVariable @Nonnull final String userThreadId) {
        userThreadManager.delete(userThreadId);
    }

    /**
     * Demonstration purposes mapping (FIXME: remove when not needed)
     */
    @RequestMapping(method = RequestMethod.GET, path = "/all")
    public List<UserThread> helloWorld() {
        return userThreadManager.getAll();
    }

}
