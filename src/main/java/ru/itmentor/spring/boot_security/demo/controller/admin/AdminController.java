package ru.itmentor.spring.boot_security.demo.controller.admin;

import ru.itmentor.spring.boot_security.demo.dto.request.CreateUser;
import ru.itmentor.spring.boot_security.demo.dto.request.UpdateUser;
import ru.itmentor.spring.boot_security.demo.dto.response.FindUserResponce;

import java.util.List;

public interface AdminController {

    List<FindUserResponce> getAllUsers();
    void addUser(CreateUser userDTO);
    void deleteUser(Long userId);
    void updateUser(Long userId, UpdateUser userDTO);
}
