package com.francisca.week9.Response;

import com.francisca.week9.Model.Like;
import com.francisca.week9.Model.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreatePostResponse {
    private String title;
    private LocalDateTime timeStamp;
    private Post post;
}

