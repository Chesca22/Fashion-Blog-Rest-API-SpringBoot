package com.francisca.week9.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter @AllArgsConstructor
public class ExceptionResponse {
    private String message;
    private HttpStatus status;
}
