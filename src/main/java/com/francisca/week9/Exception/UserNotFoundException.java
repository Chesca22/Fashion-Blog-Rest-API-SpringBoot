package com.francisca.week9.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

//@AllArgsConstructor
@Getter

public class UserNotFoundException extends RuntimeException{
private String message;

public UserNotFoundException(String message){
    this.message = message;
}

}
