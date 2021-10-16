package ru.stolyarenkoas.threader.users.exception;

import javax.annotation.Nonnull;

/**
 * Exception, thrown when invalid data is provided to a service.
 *
 * @author Alexander Stoliarenko (16.10.2021)
 */
public class InvalidDataException extends Exception {

    /**
     * Constructor that sets a custom message.
     *
     * @param message exception message.
     */
    public InvalidDataException(@Nonnull final String message) {
        super(message);
    }

    /**
     * Constructor that sets information about invalid field.
     *
     * @param fieldName name of the field that has invalid value.
     * @param value invalid value.
     */
    public InvalidDataException(@Nonnull final String fieldName, @Nonnull final String value) {
        super("Invalid value for field " + fieldName + ": " + value);
    }

}
