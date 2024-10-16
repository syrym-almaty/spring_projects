package com.example.securitytest.controller;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.securitytest.service.UserService;

@SpringBootTest // Ensures the full application context is loaded
@AutoConfigureMockMvc // Configures MockMvc for testing controllers
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService; // Mock the UserService

    @Test
    @WithMockUser(username = "admin", roles = "USER") // Mock authenticated user with role
    public void testGetAllUsers() throws Exception {
        // Mock the UserService to return an empty list
        when(userService.getAllUsers()).thenReturn(Collections.emptyList());

        // Perform a GET request and verify the result
        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk()) // Expect HTTP 200 OK
                .andExpect(jsonPath("$.length()").value(0)); // Expect the response body length to be 0
    }
}
