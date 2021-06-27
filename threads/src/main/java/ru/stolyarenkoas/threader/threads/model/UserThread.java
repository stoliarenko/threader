package ru.stolyarenkoas.threader.threads.model;

import lombok.Data;
import lombok.NonNull;

import javax.annotation.Nullable;

/**
 * Text thread provided by a user.
 */
@Data
public class UserThread {

    /**
     * Unique identifier of a user thread.
     */
    @Nullable
    private String id;

    /**
     * Text of a user thread.
     */
    @NonNull
    private final String text;

    /**
     * User who started the thread.
     */
    @NonNull
    private final String user;

}
