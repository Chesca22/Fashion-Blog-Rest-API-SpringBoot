package com.francisca.week9.Response;

import com.francisca.week9.Model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterResponse {
    private String message;
    private LocalDateTime timeStamp;
    private User user;

}
