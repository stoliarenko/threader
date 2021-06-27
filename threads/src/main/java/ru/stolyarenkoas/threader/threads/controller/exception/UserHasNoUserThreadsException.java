package ru.stolyarenkoas.threader.threads.controller.exception;

import javax.annotation.Nonnull;

/**
 * Exception thrown when user threads can not be found by given user identifier.
 *
 * @author Alexander Stolyarenko (27.06.2021)
 */
public class UserHasNoUserThreadsException extends RuntimeException {

    /**
     * Constructor that sets message about user identifier.
     *
     * @param userId identifier of a user that has no user threads.
     */
    public UserHasNoUserThreadsException(@Nonnull final String userId) {
        super("User " + userId + " has no threads.");
    }

}
