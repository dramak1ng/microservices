package ru.itmentor.spring.boot_security.demo.dto.exceptionResponce;


import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class ExceptionResponse {

    private final String message;
    private final Integer code;
    private final Timestamp timestamp;

    public ExceptionResponse(String message, Integer code) {
        this.message = message;
        this.code = code;
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }
}
