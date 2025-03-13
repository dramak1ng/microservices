package ru.itmentor.spring.boot_security.demo.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.itmentor.spring.boot_security.demo.dto.response.FindUserResponce;
import ru.itmentor.spring.boot_security.demo.service.user.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserControllerImpl implements UserController {

    private final UserService userService;


    @Override
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public FindUserResponce getInfoAboutUser() {
        return userService.getCurrentUser();
    }
}
