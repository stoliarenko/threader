package ru.stolyarenkoas.threader.threads.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.boot.test.json.ObjectContent;
import org.springframework.core.io.ClassPathResource;
import ru.stolyarenkoas.threader.threads.model.UserThread;

import java.io.IOException;

/**
 * Tests for serialization/deserialization of a {@link UserThread}.
 */
@JsonTest
@DisplayName("User thread JSON parsing tests")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserThreadParserTest {

    /**
     * Tested parser.
     */
    @Autowired
    private JacksonTester<UserThread> jacksonTester;

    /**
     * Test object to serialize/deserialize.
     */
    private static UserThread userThread;

    private static final String userThreadText = "Content of a user thread";
    private static final String userThreadAuthor = "UserCreatedTheThread";
    private static final String userThreadId = "user-thread-identifier";

    @BeforeAll
    static void prepareUserThread() {
        userThread = new UserThread(userThreadText, userThreadAuthor);
        userThread.setId(userThreadId);
    }

    /**
     * Ensures of correct serialization for {@link UserThread}.
     */
    @Test
    @DisplayName("Serialization of a UserThread test")
    void testSerialize() throws IOException {
        final JsonContent<UserThread> userThreadJson = jacksonTester.write(userThread);
        Assertions.assertThat(userThreadJson).isEqualToJson("/parser/user-thread.json");

        Assertions.assertThat(userThreadJson).extractingJsonPathStringValue("@.id", userThreadId);
        Assertions.assertThat(userThreadJson).extractingJsonPathStringValue("@.text", userThreadText);
        Assertions.assertThat(userThreadJson).extractingJsonPathStringValue("@.user", userThreadAuthor);
    }

    /**
     * Ensures of correct deserialization for {@link UserThread}.
     */
    @Test
    @DisplayName("Deserialization of a UserThread test")
    void testDeserialize() throws IOException {
        final ClassPathResource jsonResource = new ClassPathResource("/parser/user-thread.json");
        final String userThreadJson = new String(jsonResource.getInputStream().readAllBytes());
        final ObjectContent<UserThread> parsedUserThreadContent = jacksonTester.parse(userThreadJson);

        //noinspection AssertBetweenInconvertibleTypes AssertJ can do that.
        Assertions.assertThat(parsedUserThreadContent).isEqualTo(userThread);

        final UserThread parsedUserThread = jacksonTester.parseObject(userThreadJson);
        Assertions.assertThat(parsedUserThread.getId()).isEqualTo(userThreadId);
        Assertions.assertThat(parsedUserThread.getText()).isEqualTo(userThreadText);
        Assertions.assertThat(parsedUserThread.getUser()).isEqualTo(userThreadAuthor);
    }

}
