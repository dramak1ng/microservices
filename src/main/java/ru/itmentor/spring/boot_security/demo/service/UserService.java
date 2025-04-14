package ru.itmentor.spring.boot_security.demo.service;

import ru.itmentor.spring.boot_security.demo.dto.UserDto;
import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUser();

    void deleteUser(Long userId);

    void updateUser(UserDto userDto,Long userId);

    void createUser(UserDto userDto);

    User getCurrentUser();

    User findByUsername(String username);


}
