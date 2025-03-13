package ru.itmentor.spring.boot_security.demo.controller.user;

import ru.itmentor.spring.boot_security.demo.dto.response.FindUserResponce;

public interface UserController {

    FindUserResponce getInfoAboutUser();
}
