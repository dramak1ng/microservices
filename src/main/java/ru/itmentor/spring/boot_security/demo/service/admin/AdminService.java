package ru.itmentor.spring.boot_security.demo.service.admin;

import org.springframework.stereotype.Service;
import ru.itmentor.spring.boot_security.demo.dto.request.CreateUser;
import ru.itmentor.spring.boot_security.demo.dto.request.UpdateUser;
import ru.itmentor.spring.boot_security.demo.dto.response.FindUserResponce;
import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.List;


public interface AdminService {

    List<FindUserResponce> findAllUsers();

    void saveUser(CreateUser userDto);

    void updateUser(UpdateUser userDto,Long userId);

    void deleteUser(Long userId);

    User findUserByUsername(String username);
}
