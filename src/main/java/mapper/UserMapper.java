package mapper;

import lombok.NoArgsConstructor;
import ru.itmentor.crudspringboot.dto.UserDto;
import ru.itmentor.crudspringboot.model.User;

@NoArgsConstructor
public class UserMapper {
    public static User toEntity(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setLastName(userDto.getLastName());
        return user;
    }
}
