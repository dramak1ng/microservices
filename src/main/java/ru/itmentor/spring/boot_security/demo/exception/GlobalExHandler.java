package ru.itmentor.spring.boot_security.demo.exception;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.itmentor.spring.boot_security.demo.dto.exceptionResponce.ExceptionResponse;

@ControllerAdvice
public class GlobalExHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
    public ExceptionResponse handleUserNotFoundException(UserNotFoundException ex) {
        return new ExceptionResponse(ex.getMessage(),HttpStatus.NOT_FOUND.value());
    }
}
