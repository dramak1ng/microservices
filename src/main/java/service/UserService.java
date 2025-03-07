package service;

import ru.itmentor.crudspringboot.dto.UserDto;
import ru.itmentor.crudspringboot.model.User;
import java.util.List;

public interface UserService {
    List<User> getAllUser();

    void addUser(UserDto userDto);

    void updateUser(long userId, UserDto userDto);

    void deleteUser(long userId);
}
