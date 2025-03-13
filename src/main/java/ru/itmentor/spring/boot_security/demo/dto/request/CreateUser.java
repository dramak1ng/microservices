package ru.itmentor.spring.boot_security.demo.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUser {

    @NotEmpty
    private String username;

    @NotEmpty
    private String email;

    private Integer age;
}
