package ru.stolyarenkoas.threader.threads.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.UUID;

/**
 * Text thread provided by a user.
 */
@Getter
@ToString
@RequiredArgsConstructor
public class UserThread {

    /**
     * Unique identifier of a user thread.
     */
    @NonNull
    private final String id = UUID.randomUUID().toString();

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
