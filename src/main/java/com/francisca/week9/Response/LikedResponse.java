package com.francisca.week9.Response;

import com.francisca.week9.Model.Like;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LikedResponse {
    private String message;
    private LocalDateTime timeStamp;
    private Like like;
    private int totalLikes;

}
