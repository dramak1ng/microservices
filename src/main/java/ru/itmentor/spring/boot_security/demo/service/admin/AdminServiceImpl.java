package ru.itmentor.spring.boot_security.demo.service.admin;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itmentor.spring.boot_security.demo.dto.request.CreateUser;
import ru.itmentor.spring.boot_security.demo.dto.request.UpdateUser;
import ru.itmentor.spring.boot_security.demo.dto.response.FindUserResponce;
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

    @Override
    public List<FindUserResponce> findAllUsers() {
        return userMapper.toDtoListFromEntityList(userRepository.findAll());
    }

    @Override
    @Transactional
    public void saveUser(CreateUser userDto) {
        User user = userMapper.toEntityFromDto(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updateUser(UpdateUser userDto, Long userId) {
        User user = findUserById(userId);
        userMapper.updateUserFromDto(userDto, user);

        if(userDto.getPassword() != null){
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }
        userRepository.save(user);

    }

    @Override
    @Transactional
    public void deleteUser(Long userId) {
        User user = findUserById(userId);
        userRepository.delete(user);

    }


    private User findUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));

    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
