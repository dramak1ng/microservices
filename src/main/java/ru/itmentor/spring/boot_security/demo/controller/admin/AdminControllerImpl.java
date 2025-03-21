package ru.itmentor.spring.boot_security.demo.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.itmentor.spring.boot_security.demo.dto.UserDto;
import ru.itmentor.spring.boot_security.demo.service.UserService;

@Controller
@RequiredArgsConstructor
public class AdminControllerImpl implements AdminController {

    private final UserService userService;

    private static final String REDIRECT_URL = "redirect:/admin";

    @Override
    public String getAllUser(Model model) {
        model.addAttribute("users", userService.getAllUser());
        model.addAttribute("userDto", new UserDto());
        return "admin";
    }

    @Override
    public String deleteUser(Long userId) {
        userService.deleteUser(userId);
        return REDIRECT_URL;
    }

    @Override
    public String addUser(@ModelAttribute("userDto") UserDto userDto) {
        userService.createUser(userDto);
        return REDIRECT_URL;
    }

    @Override
    public String updateUser(Long userId, UserDto userDto) {
        userService.updateUser(userDto, userId);
        return REDIRECT_URL;
    }
}
