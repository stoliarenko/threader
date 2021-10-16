package ru.stolyarenkoas.threader.users.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.stolyarenkoas.threader.users.model.Role;
import ru.stolyarenkoas.threader.users.model.User;

import java.util.Arrays;
import java.util.Collections;

/**
 * Tests for user repository.
 *
 * @author Alexander Stoliarenko (16.10.2021)
 */
@DataJpaTest()
class UserRepositoryTest {

    /**
     * Test instance for entity manager.
     */
    @Autowired
    private TestEntityManager entityManager;

    /**
     * Tested repository.
     */
    @Autowired
    private UserRepository repository;

    @BeforeEach
    void setUp() {
        final User user = new User();
        user.setId("test-id");
        user.setName("test-name");
        user.setRoles(Collections.singletonList(Role.COMMON));
        entityManager.persist(user);
    }

    /**
     * Ensures save method works correctly.
     */
    @Test
    void testSave() {
        final User user = new User();
        user.setId("save-test-id");
        user.setName("save-test-name");
        user.setRoles(Arrays.asList(Role.ADMINISTRATOR, Role.COMMON));

        repository.save(user);
        final User foundUser = entityManager.find(User.class, "save-test-id");

        Assertions.assertNotNull(foundUser);
        Assertions.assertEquals("save-test-id", foundUser.getId());
        Assertions.assertEquals("save-test-name", foundUser.getName());
        Assertions.assertEquals(2, foundUser.getRoles().size());
        Assertions.assertTrue(foundUser.getRoles().contains(Role.COMMON));
        Assertions.assertTrue(foundUser.getRoles().contains(Role.ADMINISTRATOR));
    }

    /**
     * Ensures findById method works correctly.
     */
    @Test
    void testFindById() {
        final User foundUser = repository.findById("test-id").orElse(null);

        Assertions.assertNotNull(foundUser);
        Assertions.assertEquals("test-name", foundUser.getName());
        Assertions.assertEquals(1, foundUser.getRoles().size());
        Assertions.assertEquals(Role.COMMON, foundUser.getRoles().get(0));
    }

    /**
     * Ensures data updates correctly.
     */
    @Test
    void testUpdate() {
        final User user = new User();
        user.setId("test-id");
        user.setName("update-test-name");

        repository.save(user);
        final User foundUser = entityManager.find(User.class, "test-id");

        Assertions.assertNotNull(foundUser);
        Assertions.assertEquals("test-id", foundUser.getId());
        Assertions.assertEquals("update-test-name", foundUser.getName());
    }

    /**
     * Ensures deleteById method works correctly.
     */
    @Test
    void testDeleteById() {
        repository.deleteById("test-id");

        final User foundUser = entityManager.find(User.class, "test-id");
        Assertions.assertNull(foundUser);
    }

}
