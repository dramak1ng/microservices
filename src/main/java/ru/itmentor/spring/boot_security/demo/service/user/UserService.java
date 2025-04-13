package ru.itmentor.spring.boot_security.demo.service.user;


import ru.itmentor.spring.boot_security.demo.dto.response.UserResponse;

public interface UserService {

    UserResponse getCurrentUser();
}
