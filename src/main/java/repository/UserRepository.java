package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmentor.crudspringboot.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}