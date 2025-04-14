package ru.itmentor.spring.boot_security.demo.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.UserService;

@Controller
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {
    private final UserService userService;


    @Override
    public String getInfoAboutUser(Model model) {
     User user  = userService.getCurrentUser();
        model.addAttribute("user", user);
        return "user-profile";
    }
}
