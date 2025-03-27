package com.itm.space.backendresources.controllerTest;


import com.itm.space.backendresources.BaseIntegrationTest;
import com.itm.space.backendresources.api.request.UserRequest;
import com.itm.space.backendresources.api.response.UserResponse;
import com.itm.space.backendresources.exception.BackendResourcesException;
import com.itm.space.backendresources.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import javax.ws.rs.core.MediaType;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest extends BaseIntegrationTest {

    @Autowired
    UserService userService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(roles = "MODERATOR")
    void createUser_whenAuthenticated() throws Exception {
        UserRequest request = new UserRequest(
                "dramma",
                "bogna@gmail.com",
                "pass",
                "dan",
                "nik"
        );
        doNothing().when(userService).createUser(request);
        mockMvc.perform(requestWithContent(post("/api/users"), request))
                .andExpect(status().isOk());

        verify(userService, times(1)).createUser(request);
    }

    @Test
    void createUser_whenNotAuthenticated() throws Exception {
        UserRequest request = new UserRequest(
                "dramma",
                "bogna@gmail.com",
                "pass",
                "dan",
                "nik"
        );
        doNothing().when(userService).createUser(request);
        mockMvc.perform(requestWithContent(post("/api/users"), request))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "MODERATOR")
    void createUser_withBadRequest() throws Exception {
        UserRequest request = new UserRequest(
                "dramma",
                "bogna@gmail.com",
                "pass",
                "dan",
                "nik"
        );
        doThrow(new BackendResourcesException("Invalid password", HttpStatus.BAD_REQUEST))
                .when(userService).createUser(request);
        mockMvc.perform(requestWithContent(post("/api/users"), request))
                .andExpect(status().isBadRequest());

    }

    @Test
    @WithMockUser(username = "moderator", roles = "MODERATOR")
    void hello_whenAuthenticated() throws Exception {
        mockMvc.perform(get("/api/users/hello")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("moderator"));
    }

    @Test
    void hello_whenNotAuthenticated() throws Exception {
        mockMvc.perform(get("/api/users/hello")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "MODERATOR")
    void getUser_whenAuthenticated() throws Exception {
        UUID id = UUID.randomUUID();
        UserResponse response = new UserResponse(
                "dan",
                "nik",
                "bogna@gmail.com",
                List.of("moderator"),
                List.of("moderators")
        );
        when(userService.getUserById(id)).thenReturn(response);
        mockMvc.perform(get("/api/users/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("dan"))
                .andExpect(jsonPath("$.lastName").value("nik"))
                .andExpect(jsonPath("$.email").value("bogna@gmail.com"))
                .andExpect(jsonPath("$.groups").isArray())
                .andExpect(jsonPath("$.groups").value("moderators"));

        verify(userService, times(1)).getUserById(id);
    }

    @Test
    void getUser_whenNotAuthenticated() throws Exception {
        UUID id = UUID.randomUUID();
        mockMvc.perform(get("/api/users/{id}", id))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "MODERATOR")
    void getUser_whenBadRequest() throws Exception {
        UUID id = UUID.randomUUID();
        String errorMassage = "User not found";
        when(userService.getUserById(id)).thenThrow(new BackendResourcesException(errorMassage, HttpStatus.BAD_REQUEST));
        mockMvc.perform(get("/api/users/{id}", id))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(errorMassage));
    }
}
