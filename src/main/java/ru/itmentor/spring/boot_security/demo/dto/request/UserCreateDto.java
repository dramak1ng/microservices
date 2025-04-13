package ru.itmentor.spring.boot_security.demo.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserCreateDto {
    @NotEmpty
    private String username;

    @NotEmpty
    private String email;

    private Integer age;

    @NotBlank
    private String password;

    private Set<String> roles;
}
