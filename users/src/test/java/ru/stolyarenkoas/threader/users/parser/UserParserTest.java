package ru.stolyarenkoas.threader.users.parser;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.boot.test.json.ObjectContent;
import org.springframework.core.io.ClassPathResource;
import ru.stolyarenkoas.threader.users.model.Role;
import ru.stolyarenkoas.threader.users.model.User;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Tests for parsing {@link ru.stolyarenkoas.threader.users.model.User}.
 *
 * @author Alexander Stoliarenko (26.09.2021)
 */
@JsonTest
@Tag("parser")
@DisplayName("User JSON parsing tests")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserParserTest {

    /**
     * Tested parser.
     */
    @Autowired
    private JacksonTester<User> jacksonTester;

    /**
     * User object for tests.
     */
    private static User testUser;

    private static final String userIdentifier = "Test-User-Identifier";
    private static final String userName = "Test user name";
    private static final List<Role> userRoles = Arrays.asList(Role.COMMON, Role.ADMINISTRATOR);

    @BeforeAll
    void prepareUser() {
        testUser = new User(userName, userRoles);
        testUser.setId(userIdentifier);
    }

    /**
     * Ensures of correct serialization for {@link User}.
     */
    @Test
    @DisplayName("Serialization of a User test")
    void testSerialize() throws IOException {
        final JsonContent<User> userJson = jacksonTester.write(testUser);
        Assertions.assertThat(userJson).isEqualToJson("/parser/user.json");

        Assertions.assertThat(userJson).extractingJsonPathStringValue("@.id", userIdentifier);
        Assertions.assertThat(userJson).extractingJsonPathStringValue("@.name", userName);
        Assertions.assertThat(userJson).extractingJsonPathStringValue("@.roles[0]", userRoles.get(0));
        Assertions.assertThat(userJson).extractingJsonPathStringValue("@.roles[1]", userRoles.get(1));
    }

    /**
     * Ensures of correct deserialization for {@link User}.
     */
    @Test
    @DisplayName("Deserialization of a User test")
    void testDeserialize() throws IOException {
        final ClassPathResource userJsonResource = new ClassPathResource("/parser/user.json");
        final byte[] userJson = userJsonResource.getInputStream().readAllBytes();

        final User parsedUser = jacksonTester.parseObject(userJson);
        Assertions.assertThat(parsedUser.getId()).isEqualTo(userIdentifier);
        Assertions.assertThat(parsedUser.getName()).isEqualTo(userName);
        Assertions.assertThat(parsedUser.getRoles()).isEqualTo(userRoles);

        final ObjectContent<User> parsedUserContent = jacksonTester.parse(userJson);
        //noinspection AssertBetweenInconvertibleTypes AssertJ can do that.
        Assertions.assertThat(parsedUserContent).isEqualTo(testUser);
    }

}
