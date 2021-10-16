package ru.stolyarenkoas.threader.users.contract;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.stolyarenkoas.threader.users.MainApplication;
import ru.stolyarenkoas.threader.users.controller.UserController;
import ru.stolyarenkoas.threader.users.model.Role;
import ru.stolyarenkoas.threader.users.model.User;
import ru.stolyarenkoas.threader.users.service.UserService;

import java.util.Collections;

/**
 * Base class for generated contracts.
 *
 * @author Alexander Stoliarenko (29.09.2021)
 */
@SpringBootTest(classes = MainApplication.class)
public abstract class ContractTestBase {

    /**
     * Tested controller class.
     */
    @Autowired
    UserController userController;

    /**
     * Stub dor underlying user management service.
     */
    @MockBean
    UserService userService;

    /**
     * Prepare server and stubs.
     */
    @BeforeEach
    public void setUp() {
        RestAssuredMockMvc.standaloneSetup(UserController.class);

        final User stubUser = new User();
        stubUser.setName("user-name");
        stubUser.setRoles(Collections.singletonList(Role.COMMON));
        stubUser.setId("user-id");
        Mockito.when(userService.getBySessionId("1")).thenReturn(stubUser);
    }

}
