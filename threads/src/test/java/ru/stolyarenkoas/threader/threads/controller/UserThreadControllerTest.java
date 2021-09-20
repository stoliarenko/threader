package ru.stolyarenkoas.threader.threads.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import ru.stolyarenkoas.threader.threads.model.UserThread;
import ru.stolyarenkoas.threader.threads.service.api.UserThreadManager;

import java.util.LinkedHashSet;
import java.util.Set;

import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests for a user threads REST controller.
 */
@WebMvcTest(UserThreadController.class)
class UserThreadControllerTest {

    /**
     * Mapper for data objects.
     */
    @Autowired
    private ObjectMapper mapper;

    /**
     * Standard stub for a web component.
     */
    @Autowired
    private MockMvc mockMvc;

    /**
     * Stub for a user thread managing service.
     */
    @MockBean
    private UserThreadManager userThreadManagerMock;

    /**
     * Identifier of a test user thread.
     */
    private final String userThreadId = "totally-new-id";

    /**
     * Content of a test user thread.
     */
    private final String userThreadText = "any-text";

    /**
     * Creator of a test user thread.
     */
    private final String userThreadUser = "any-user";

    /**
     * Tests that returned object for create user thread request has correct structure.
     */
    @Test
    void testCreate() throws Exception {
        final UserThread userThread = new UserThread(userThreadText, userThreadUser);
        when(userThreadManagerMock.create(any(UserThread.class))).thenReturn(userThreadId);

        final MockHttpServletRequestBuilder request = post("/userThreads/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(userThread));
        mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(userThreadId)))
                .andExpect(jsonPath("$.text", is(userThreadText)))
                .andExpect(jsonPath("$.user", is(userThreadUser)));

        verify(userThreadManagerMock, times(1)).create(any(UserThread.class));
        verifyNoMoreInteractions(userThreadManagerMock);
    }

    /**
     * Tests that returned object for get request corresponds one that was returned by service.
     */
    @Test
    void testGet() throws Exception {
        final UserThread userThread = new UserThread(userThreadText, userThreadUser);
        userThread.setId(userThreadId);
        when(userThreadManagerMock.get(eq(userThreadId))).thenReturn(userThread);

        final MockHttpServletRequestBuilder request = get("/userThreads/{id}", userThreadId);
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(userThreadId)))
                .andExpect(jsonPath("$.text", is(userThreadText)))
                .andExpect(jsonPath("$.user", is(userThreadUser)));

        verify(userThreadManagerMock, times(1)).get(eq(userThreadId));
        verifyNoMoreInteractions(userThreadManagerMock);
    }

    /**
     * Tests that collection of user threads is returned on ger by user id request.
     */
    @Test
    void testGetByUserId() throws Exception {
        final UserThread userThread = new UserThread(userThreadText, userThreadUser);
        final String userThreadId = "1st identifier";
        userThread.setId(userThreadId);
        final UserThread anotherUserThread = new UserThread(userThreadText, userThreadUser);
        final String anotherUserThreadId = "2nd identifier";
        userThread.setId(anotherUserThreadId);
        final Set<UserThread> userThreads = new LinkedHashSet<>();
        userThreads.add(userThread);
        userThreads.add(anotherUserThread);
        when(userThreadManagerMock.getByUserId(userThreadUser)).thenReturn(userThreads);

        final MockHttpServletRequestBuilder request = get("/userThreads/user/{userId}", userThreadUser);
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)));

        verify(userThreadManagerMock, times(1)).getByUserId(eq(userThreadUser));
        verifyNoMoreInteractions(userThreadManagerMock);
    }

    /**
     * Tests that service method is invoked on delete request.
     */
    @Test
    void testDelete() throws Exception {
        doNothing().when(userThreadManagerMock).delete(userThreadId);

        final MockHttpServletRequestBuilder request = delete("/userThreads/delete/{id}", userThreadId);
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(emptyString()));

        verify(userThreadManagerMock, times(1)).delete(userThreadId);
        verifyNoMoreInteractions(userThreadManagerMock);
    }

}