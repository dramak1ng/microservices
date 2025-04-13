package ru.itmentor.spring.boot_security.demo.service.admin;

import ru.itmentor.spring.boot_security.demo.dto.request.UserCreateDto;
import ru.itmentor.spring.boot_security.demo.dto.request.UserUpdateDto;
import ru.itmentor.spring.boot_security.demo.dto.response.UserResponse;
import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.List;


public interface AdminService {

    List<UserResponse> findAllUsers();

    UserResponse saveUser(UserCreateDto userDto);

    UserResponse updateUser(UserUpdateDto userDto, Long userId);

    void deleteUser(Long userId);

    User findUserByUsername(String username);
}
