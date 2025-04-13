package ru.itmentor.spring.boot_security.demo.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.dto.request.UserCreateDto;
import ru.itmentor.spring.boot_security.demo.dto.request.UserUpdateDto;
import ru.itmentor.spring.boot_security.demo.dto.response.FindUserResponce;
import ru.itmentor.spring.boot_security.demo.dto.response.UserResponse;
import ru.itmentor.spring.boot_security.demo.service.admin.AdminService;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminControllerImpl implements AdminController {

    private final AdminService adminService;

    @Override
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> getAllUsers() {
        return adminService.findAllUsers();
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse addUser(@RequestBody UserCreateDto userDTO) {
       return adminService.saveUser(userDTO);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("id") Long userId) {
        adminService.deleteUser(userId);
    }

    @Override
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse updateUser(@PathVariable("id") Long userId, @RequestBody UserUpdateDto userDTO) {
       return adminService.updateUser(userDTO, userId);
    }
}
