package controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.crudspringboot.dto.UserDto;
@RequestMapping("/users")
public interface UserController {

    @GetMapping
    String getAllUser(Model model);

    @PostMapping
    String addUser(@ModelAttribute("userDto") UserDto userDto);

    @DeleteMapping
    String deleteUser(@RequestParam("id") long userId);

    @PutMapping
    String updateUser(@RequestParam("id") long userId, UserDto userDto);
}
