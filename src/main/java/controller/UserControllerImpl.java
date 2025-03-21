package controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itmentor.crudspringboot.dto.UserDto;
import ru.itmentor.crudspringboot.service.UserService;

@Controller
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {
    private final UserService userService;

    private static final String REDIRECT_URL = "redirect:/users";


    @Override
    public String getAllUser(Model model) {
        model.addAttribute("users", userService.getAllUser());
        model.addAttribute("userDto", new UserDto());
        return "/users/list";
    }

    @Override
    public String addUser(@ModelAttribute("userDto") UserDto userDto){
        userService.addUser(userDto);
        return REDIRECT_URL;
    }

    @Override
    public String deleteUser(long userId) {
        userService.deleteUser(userId);
        return REDIRECT_URL;
    }

    @Override
    public String updateUser(long userId, UserDto userDto) {
        userService.updateUser(userId,userDto);
        return REDIRECT_URL;
    }
}
