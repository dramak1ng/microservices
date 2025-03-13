package ru.itmentor.spring.boot_security.demo.dto.response;

import lombok.Getter;
import lombok.Setter;
import ru.itmentor.spring.boot_security.demo.model.Role;

import java.util.Set;

@Getter
@Setter
public class FindUserResponce {

    private String username;

    private String email;

    private  Integer age;

    private Set<Role> roles;
}
