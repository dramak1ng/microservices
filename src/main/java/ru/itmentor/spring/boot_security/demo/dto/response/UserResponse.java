package ru.itmentor.spring.boot_security.demo.dto.response;

import lombok.Data;

import java.util.Set;

@Data
public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private Integer age;
    private Set<String> roles;
}