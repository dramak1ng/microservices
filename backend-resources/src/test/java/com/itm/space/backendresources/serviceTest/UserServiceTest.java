package com.itm.space.backendresources.serviceTest;

import com.itm.space.backendresources.BaseIntegrationTest;
import com.itm.space.backendresources.api.request.UserRequest;
import com.itm.space.backendresources.api.response.UserResponse;
import com.itm.space.backendresources.exception.BackendResourcesException;
import com.itm.space.backendresources.mapper.UserMapper;
import com.itm.space.backendresources.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.GroupRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.mockito.Answers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import javax.ws.rs.core.Response;

import java.util.List;
import java.util.UUID;

import static javax.ws.rs.core.Response.Status;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest  {

    @MockBean(answer = Answers.RETURNS_DEEP_STUBS)
    private Keycloak keycloak;

    @Value("${keycloak.realm}")
    private String realm;

    @Autowired
    private UserService userService;

    private UserRequest userRequest;

    @BeforeEach
    void createUserRequest() {
        this.userRequest = new UserRequest(
                "userTestCreation",
                "bogna@gmail.com",
                "pass",
                "rows",
                "erws"
        );
    }

    @Test
    void createUser_IfNotExists() {
        Response response = Response.status(Response.Status.CREATED)
                .header("Location", "http://localhost/auth/admin/realms/" + realm + "/users/228").build();
        when(keycloak.realm(realm).users().create(any(UserRepresentation.class)))
                .thenReturn(response);
        assertDoesNotThrow(() -> userService.createUser(userRequest));

        verify(keycloak.realm(anyString()).users(), times(1))
                .create(any(UserRepresentation.class));
    }

    @Test
    void createUser_IfAlredyExists() {
        Response response = Response.status(Status.BAD_REQUEST)
                .header("Location", "http://localhost/auth/admin/realms/" + realm + "/users").build();
        when(keycloak.realm(realm).users().create(any(UserRepresentation.class)))
                .thenReturn(response);
        assertThrows(BackendResourcesException.class, () -> userService.createUser(userRequest));

        verify(keycloak.realm(anyString()).users(), times(1))
                .create(any(UserRepresentation.class));

    }

    @Test
    void getUser_IfNotExists() {
        UUID id = UUID.randomUUID();
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setId(id.toString());
        userRepresentation.setEmail("bogna@gmail.com");
        userRepresentation.setFirstName("boga");
        userRepresentation.setLastName("pass");

        List<RoleRepresentation> roles = List.of(new RoleRepresentation("MODERATOR", "", false));
        GroupRepresentation groupRepresentation = new GroupRepresentation();
        groupRepresentation.setName("group");
        List<GroupRepresentation> groups = List.of(groupRepresentation);

        when(keycloak.realm(realm).users().get(id.toString()).toRepresentation())
                .thenReturn(userRepresentation);
        when(keycloak.realm(realm).users().get(id.toString()).roles().getAll().getRealmMappings())
                .thenReturn(roles);
        when(keycloak.realm(realm).users().get(id.toString()).groups()).
                thenReturn(groups);

        UserResponse userResponse = userService.getUserById(id);
        assertNotNull(userResponse);
        assertEquals(userRepresentation.getFirstName(), userResponse.getFirstName());
    }

    @Test
    void getUser_IfAlreadyExists() {
        UUID id = UUID.randomUUID();
        when(keycloak.realm(realm).users().get(id.toString()).toRepresentation())
                .thenThrow(new BackendResourcesException("User not found",HttpStatus.BAD_REQUEST));
        BackendResourcesException exception = assertThrows(BackendResourcesException.class, () -> userService.getUserById(id));
        assertEquals("User not found", exception.getMessage());
    }
}
