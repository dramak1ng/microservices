package ru.itmentor.spring.boot_security.demo.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.itmentor.spring.boot_security.demo.dto.response.FindUserResponce;
import ru.itmentor.spring.boot_security.demo.mapper.UserMapper;
import ru.itmentor.spring.boot_security.demo.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    //TODO
    @Override
    public FindUserResponce getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userMapper.toFindUserResponceDtoFromEntity(userRepository.findByUsername(username)
                .orElseThrow());
    }
}
