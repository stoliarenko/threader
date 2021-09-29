package ru.stolyarenkoas.threader.threads.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * Data class, representing service user.
 *
 * @author Alexander Stoliarenko (26.09.2021)
 */
@Data
@NoArgsConstructor
public class User {

    /**
     * Unique identifier.
     */
    @Nonnull
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
