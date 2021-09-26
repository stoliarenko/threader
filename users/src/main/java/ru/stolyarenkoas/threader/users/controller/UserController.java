package ru.stolyarenkoas.threader.users.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.stolyarenkoas.threader.users.model.Role;
import ru.stolyarenkoas.threader.users.model.User;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * REST service access point to manage users.
 *
 * @author Alexander Stoliarenko (26.09.2021)
 */
@RestController
@RequestMapping(path = "/users")
public class UserController {

    /**
     * Retrieves an authenticated user by session identifier.
     *
     * @param sessionId identifier of a session.
     * @return user with corresponding session identifier.
     */
    @Operation(
            summary = "Retrieves an authenticated user by session identifier",
            parameters = @Parameter(name = "sessionId", description = "Identifier of a session")
    )
    @ApiResponses({
            @ApiResponse(
                    description = "Returns a user corresponding to given session identifier",
                    responseCode = "200",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = User.class)
                    )
            ),
            @ApiResponse(
                    description = "User authenticated by given session identifier is not found",
                    responseCode = "404",
                    content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE)
            )
    })
    @GetMapping(path = "/{sessionId}")
    public User getBySessionId(@PathVariable @Nonnull final String sessionId) {
        return getStubUser();
    }

    /**
     * Creates a new test user.
     * FIXME: Test purposes method.
     */
    private User getStubUser() {
        final String userId = UUID.randomUUID().toString();
        final String userName = "Test-User";
        final List<Role> userRoles = Collections.singletonList(Role.ADMINISTRATOR);
        final User user = new User(userName, userRoles);
        user.setId(userId);
        return user;
    }

}
