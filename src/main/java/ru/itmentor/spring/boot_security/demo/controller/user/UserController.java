package ru.itmentor.spring.boot_security.demo.controller.user;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itmentor.spring.boot_security.demo.service.UserService;

@RequestMapping("/user")
public interface UserController {

    @GetMapping
    String getInfoAboutUser(Model model);
}
