package ru.itmentor.spring.boot_security.demo.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.dto.request.CreateUser;
import ru.itmentor.spring.boot_security.demo.dto.request.UpdateUser;
import ru.itmentor.spring.boot_security.demo.dto.response.FindUserResponce;
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
    public List<FindUserResponce> getAllUsers() {
        return adminService.findAllUsers();
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addUser(CreateUser userDTO) {
        adminService.saveUser(userDTO);

    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(Long userId) {
        adminService.deleteUser(userId);
    }

    @Override
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(Long userId, UpdateUser userDTO) {
        adminService.updateUser(userDTO, userId);
    }
}
