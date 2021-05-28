package ru.stolyarenkoas.threader.threads.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.stolyarenkoas.threader.threads.model.UserThread;
import ru.stolyarenkoas.threader.threads.service.api.UserThreadManager;

import javax.annotation.Nonnull;
import java.util.List;

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
     * Demonstration purposes mapping (FIXME: remove when not needed)
     */
    @RequestMapping(method = RequestMethod.GET, path = "/all")
    public List<UserThread> helloWorld() {
        return userThreadManager.getAll();
    }

}
