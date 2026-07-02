package com.testing.spring;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.NoSuchElementException;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetUser_Success() throws Exception {
        User user = new User(1L, "Bob");
        when(userService.getUserById(1L)).thenReturn(user);

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Bob"));

        verify(userService).getUserById(1L);
    }

    @Test
    public void testGetUser_NotFound_MappedByAdvice() throws Exception {
        when(userService.getUserById(99L)).thenThrow(new NoSuchElementException("User not found"));

        mockMvc.perform(get("/users/99"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("User not found"));

        verify(userService).getUserById(99L);
    }

    @Test
    public void testCreateUser_Success() throws Exception {
        User user = new User(2L, "Charlie");
        when(userService.saveUser(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.name").value("Charlie"));

        verify(userService).saveUser(any(User.class));
    }
}
