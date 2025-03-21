package service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import ru.itmentor.crudspringboot.dto.UserDto;
import ru.itmentor.crudspringboot.exception.UserNotFoundException;
import ru.itmentor.crudspringboot.mapper.UserMapper;
import ru.itmentor.crudspringboot.model.User;
import ru.itmentor.crudspringboot.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;


    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();

    }

    @Override
    @Transactional
    public void addUser(UserDto userDto) {
      User user = UserMapper.toEntity(userDto);
      userRepository.save(user);
    }

    @Override
    @Transactional
    public void updateUser(long userId, UserDto userDto) {
        User user = findUserById(userId);
        user.setName(userDto.getName());
        user.setLastName(userDto.getLastName());
        userRepository.save(user);


    }

    @Override
    @Transactional
    public void deleteUser(long userId) {
        userRepository.deleteById(userId);

    }

    private User findUserById(long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }
}
