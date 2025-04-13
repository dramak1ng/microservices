package ru.itmentor.spring.boot_security.demo.service.admin;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itmentor.spring.boot_security.demo.dto.request.UserCreateDto;
import ru.itmentor.spring.boot_security.demo.dto.request.UserUpdateDto;
import ru.itmentor.spring.boot_security.demo.dto.response.UserResponse;
import ru.itmentor.spring.boot_security.demo.exception.UserNotFoundException;
import ru.itmentor.spring.boot_security.demo.mapper.UserMapper;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.repository.UserRepository;

import java.util.List;

@Service

@AllArgsConstructor

public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;




    private User findUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));

    }

    @Override
    public List<UserResponse> findAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Override
    public UserResponse saveUser(UserCreateDto userDto) {
        User user = userMapper.toEntity(userDto);
        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public UserResponse updateUser(UserUpdateDto userDto, Long userId) {
       User user = findUserById(userId);
       userMapper.updateFromDto(userDto, user);
       return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public void deleteUser(Long userId) {
        deleteUser(userId);
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
