package ru.itmentor.spring.boot_security.demo.exception;

public class UserNotFoundException extends RuntimeException{
    private static final String ERROR_MESSAGE = "User not found";
    public UserNotFoundException(Long userId) {
        super(ERROR_MESSAGE);
    }
}
