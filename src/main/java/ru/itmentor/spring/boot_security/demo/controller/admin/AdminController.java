package ru.itmentor.spring.boot_security.demo.controller.admin;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.dto.UserDto;
import ru.itmentor.spring.boot_security.demo.model.User;

@RequestMapping("/admin")
public interface AdminController {

    @GetMapping
    String getAllUser(Model model);

    @DeleteMapping
    String deleteUser(@RequestParam("id") Long userId);

    @PostMapping
    String addUser(@ModelAttribute("userDto") UserDto userDto);

    @PutMapping
    String updateUser(@ModelAttribute("id") Long userId,@ModelAttribute("userDto") UserDto userDto);
}
