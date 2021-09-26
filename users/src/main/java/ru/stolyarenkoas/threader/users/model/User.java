package ru.stolyarenkoas.threader.users.model;

import lombok.Data;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/**
 * Data class, representing service user.
 *
 * @author Alexander Stoliarenko (26.09.2021)
 */
@Data
public class User {

    /**
     * Unique identifier.
     */
    @Nullable
    private String id;

    /**
     * Display name.
     */
    @Nonnull
    private String name;

    /**
     * Current roles, defining user rights.
     */
    @Nonnull
    private List<Role> roles;

}
