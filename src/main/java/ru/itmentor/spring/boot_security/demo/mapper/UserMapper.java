package ru.itmentor.spring.boot_security.demo.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.itmentor.spring.boot_security.demo.dto.UserDto;
import ru.itmentor.spring.boot_security.demo.model.User;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final RoleMapper roleMapper;

    public User toEntity(UserDto dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setRoles(roleMapper.mapRoleNamesToEntities(dto.getRoles()));
        user.setPassword(dto.getPassword());
        user.setLastName(dto.getLastName());
        return user;
    }
}