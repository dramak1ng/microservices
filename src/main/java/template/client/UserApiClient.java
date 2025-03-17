package template.client;


import template.dto.User;

import java.util.List;

public interface UserApiClient {

    List<User> getAllUsers();

    String createUser(User user);

    String updateUser(Long id, User user);

    String deleteUser(Long id);

}
