package ru.itmentor.spring.boot_security.demo.controller.user;

import ru.itmentor.spring.boot_security.demo.dto.response.FindUserResponce;
import ru.itmentor.spring.boot_security.demo.dto.response.UserResponse;

public interface UserController {

   UserResponse getInfoAboutUser();
}
