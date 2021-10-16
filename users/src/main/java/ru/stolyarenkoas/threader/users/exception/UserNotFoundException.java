package ru.stolyarenkoas.threader.users.exception;

import javax.annotation.Nullable;

/**
 * Exception, thrown when user is not found in database.
 *
 * @author Alexander Stoliarenko (16.10.2021)
 */
public class UserNotFoundException extends Exception {

    /**
     * Constructor that sets a message about user identifier.
     */
    public UserNotFoundException(@Nullable final String identifier) {
        super("User with given identifier is not found: " + identifier);
    }

}
