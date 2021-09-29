package ru.stolyarenkoas.threader.threads.contract;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.junit.StubRunnerExtension;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ru.stolyarenkoas.threader.threads.model.Role;
import ru.stolyarenkoas.threader.threads.model.User;

/**
 * Tests for external contract of user service.
 *
 * @author Alexander Stoliarenko (29.09.2021)
 */
@SpringBootTest
@DisplayName("External contract tests for user service")
public class UserServiceContractTest {

    /**
     * Junit extension for running contract service stubs.
     */
    @RegisterExtension
    public StubRunnerExtension stubRunnerExtension = new StubRunnerExtension()
            .downloadStub("ru.stolyarenkoas.threader", "users", "1.0-SNAPSHOT")
            .withPort(6060)
            .stubsMode(StubRunnerProperties.StubsMode.LOCAL);

    /**
     * Tests retrieving of a user by active session identifier contract.
     */
    @Test
    @DisplayName("Retrieve by session ID test")
    void testUserServiceContract() {
        final RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<User> userEntity = restTemplate
                .getForEntity("http://localhost:6060/users/session/1", User.class);
        BDDAssertions.then(userEntity).isNotNull();
        final User user = userEntity.getBody();
        Assertions.assertThat(user).isNotNull();
        BDDAssertions.then(user.getId()).isEqualTo("user-id");
        BDDAssertions.then(user.getName()).isEqualTo("user-name");
        BDDAssertions.then(user.getRoles().size()).isEqualTo(1);
        BDDAssertions.then(user.getRoles().get(0)).isEqualTo(Role.COMMON);
    }

}
