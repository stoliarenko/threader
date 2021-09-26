package ru.stolyarenkoas.threader.threads.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.stolyarenkoas.threader.threads.controller.exception.UserHasNoUserThreadsException;
import ru.stolyarenkoas.threader.threads.controller.exception.UserThreadNotFoundException;
import ru.stolyarenkoas.threader.threads.model.UserThread;
import ru.stolyarenkoas.threader.threads.service.api.UserThreadManager;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * REST service access point to manage user threads.
 *
 * @author Alexander Stolyarenko (28.05.2021)
 */
@RestController
@RequestMapping(path = "/userThreads")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserThreadController {

    /**
     * Service to manage user threads.
     */
    @Nonnull
    private final UserThreadManager userThreadManager;

    /**
     * Creates a new user thread.
     *
     * @param userThread user thread data.
     * @return created user thread.
     */
    @Operation(
            summary = "Creates a new user thread.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "A new user thread. Identifier field is ignored."
            )
    )
    @ApiResponses({
            @ApiResponse(
                    description = "User thread successfully created.",
                    responseCode = "201",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserThread.class)
                    )
            ),
            @ApiResponse(
                    description = "Unable to create a user thread.",
                    responseCode = "400", content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE)
            )
    })
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST, path = "/create")
    public UserThread create(@RequestBody @Nonnull UserThread userThread) {
        final String userThreadId = userThreadManager.create(userThread);
        userThread.setId(userThreadId);
        return userThread;
    }

    /**
     * Retrieves a user thread by its identifier.
     *
     * @param id identifier of a user thread.
     * @return user thread that has specified identifier.
     * @throws UserThreadNotFoundException if there is no user thread corresponding to specified id.
     */
    @Operation(summary = "Retrieves a user thread by its id.")
    @ApiResponses({
            @ApiResponse(
                    description = "Returns user thread that has given id.",
                    responseCode = "200",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserThread.class)
                    )
            ),
            @ApiResponse(
                    description = "User thread with given id does not exist.",
                    responseCode = "404", content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE)
            )
    })
    @Parameter(name = "id", description = "Identifier of a user thread")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public UserThread get(@PathVariable @Nonnull final String id) {
        final UserThread userThread = userThreadManager.get(id);
        if (Objects.isNull(userThread)) {
            throw new UserThreadNotFoundException(id);
        }
        return userThread;
    }

    /**
     * Retrieves user threads of a user that has specified identifier.
     *
     * @param userId existing user identifier.
     * @return set of user threads.
     * @throws UserHasNoUserThreadsException if no threads found for specified user.
     */
    @Operation(summary = "Retrieves all user threads by user id.")
    @ApiResponses({
            @ApiResponse(
                    description = "Returns user thread with given id.",
                    responseCode = "200",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = UserThread.class))
                    )
            ),
            @ApiResponse(
                    description = "User with given id does not have any user threads.",
                    responseCode = "404", content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE)
            )
    })
    @Parameter(name = "userId", description = "Identifier of a user.")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, path = "/user/{userId}")
    public Set<UserThread> getByUserId(@PathVariable @Nonnull final String userId) {
        final Set<UserThread> foundThreads = userThreadManager.getByUserId(userId);
        if (foundThreads.isEmpty()) {
            throw new UserHasNoUserThreadsException(userId);
        }
        return foundThreads;
    }

    /**
     * Deletes user thread that has specified identifier.
     *
     * @param userThreadId identifier of existing user thread.
     */
    @Operation(summary = "Removes user thread by its id.")
    @ApiResponses({
            @ApiResponse(
                    description = "User thread is successfully deleted.",
                    responseCode = "200"
            ),
            @ApiResponse(
                    description = "User thread with given id does not exist.",
                    responseCode = "404", content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE)
            )
    })
    @Parameter(name = "userThreadId", description = "Identifier of a user thread")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.DELETE, path = "/delete/{userThreadId}")
    public void delete(@PathVariable @Nonnull final String userThreadId) {
        userThreadManager.delete(userThreadId);
    }

    /**
     * Demonstration purposes mapping (FIXME: remove when not needed)
     */
    @RequestMapping(method = RequestMethod.GET, path = "/all")
    public List<UserThread> helloWorld() {
        return userThreadManager.getAll();
    }

}
