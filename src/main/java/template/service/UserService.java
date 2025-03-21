package template.service;

import template.client.UserApiClient;
import template.dto.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserApiClient userApiClient;

    public String task() {
        List<User> lustUsers = userApiClient.getAllUsers();

        User newUser = new User(3L, "JAMES", "BROWN", (byte) 18);
      String fist = userApiClient.createUser(newUser);

      User userForUpdate = new User(3L,"THOMAS","SHELBY",(byte)18);
      String second = userApiClient.updateUser(userForUpdate.getId(), userForUpdate);

      String third  = userApiClient.deleteUser(3L);

      return fist+second+third;
    }
}

