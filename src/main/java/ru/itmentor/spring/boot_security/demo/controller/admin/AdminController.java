package ru.itmentor.spring.boot_security.demo.controller.admin;

import ru.itmentor.spring.boot_security.demo.dto.request.UserCreateDto;
import ru.itmentor.spring.boot_security.demo.dto.request.UserUpdateDto;
import ru.itmentor.spring.boot_security.demo.dto.response.FindUserResponce;
import ru.itmentor.spring.boot_security.demo.dto.response.UserResponse;

import java.util.List;

public interface AdminController {

    List<UserResponse> getAllUsers();
    UserResponse addUser(UserCreateDto userDTO);
    void deleteUser(Long userId);
    UserResponse updateUser(Long userId, UserUpdateDto userDTO);
}
