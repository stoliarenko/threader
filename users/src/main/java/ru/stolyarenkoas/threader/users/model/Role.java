package ru.stolyarenkoas.threader.users.model;

/**
 * Enumeration of roles that are available for assigning.
 *
 * @author Alexander Stoliarenko (26.09.2021)
 */
public enum Role {

    /**
     * God-mode role, everything is allowed.
     */
    ADMINISTRATOR,

    /**
     * Provides rights for authenticated users.
     */
    COMMON,

}
