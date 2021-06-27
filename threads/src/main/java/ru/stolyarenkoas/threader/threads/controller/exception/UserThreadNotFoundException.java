package ru.stolyarenkoas.threader.threads.controller.exception;

import javax.annotation.Nonnull;

/**
 * Exception thrown when user thread can not be found by given identifier.
 *
 * @author Alexander Stolyarenko (27.06.2021)
 */
public class UserThreadNotFoundException extends RuntimeException {

    /**
     * Constructor that sets message about missing parameter.
     *
     * @param userThreadId identifier that is not found.
     */
    public UserThreadNotFoundException(@Nonnull final String userThreadId) {
        super("User thread can not be found by specified identifier: " + userThreadId);
    }

}
