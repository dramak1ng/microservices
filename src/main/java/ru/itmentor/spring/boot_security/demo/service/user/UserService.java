package ru.itmentor.spring.boot_security.demo.service.user;

import ru.itmentor.spring.boot_security.demo.dto.response.FindUserResponce;

public interface UserService {

    FindUserResponce getCurrentUser();
}
