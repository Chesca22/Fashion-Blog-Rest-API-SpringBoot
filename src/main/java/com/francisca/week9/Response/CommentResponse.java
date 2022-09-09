package com.francisca.week9.Response;

import com.francisca.week9.Model.Comment;
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


public class CommentResponse {
    private String message;
    private LocalDateTime timeStamp;
    private Comment comment;
    private Post post;

}
