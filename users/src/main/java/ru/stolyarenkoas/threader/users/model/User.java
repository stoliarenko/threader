package ru.stolyarenkoas.threader.users.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.util.List;

/**
 * Data class, representing service user.
 *
 * @author Alexander Stoliarenko (26.09.2021)
 */
@Data
@Entity(name = "USER")
@NoArgsConstructor
public class User {

    /**
     * Unique identifier.
     */
    @Id
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
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Role.class)
    private List<Role> roles;

}
