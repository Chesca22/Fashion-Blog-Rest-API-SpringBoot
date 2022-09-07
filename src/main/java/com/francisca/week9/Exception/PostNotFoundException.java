package com.francisca.week9.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter

public class PostNotFoundException extends RuntimeException{
    private String message;
}
