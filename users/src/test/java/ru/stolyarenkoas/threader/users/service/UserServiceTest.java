package ru.stolyarenkoas.threader.users.service;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.stolyarenkoas.threader.users.repository.UserRepository;

/**
 * Tests for user management service.
 * TODO implement tests.
 *
 * @author Alexander Stoliarenko (16.10.2021)
 */
@SpringBootTest(classes = UserServiceImpl.class)
public class UserServiceTest {

    /**
     * Mock for a user repository.
     */
    @MockBean
    private UserRepository repositoryMock;

}
