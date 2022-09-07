package com.francisca.week9.Response;

import com.francisca.week9.Model.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SearchCommentResponse {
    private String message;
    private LocalDateTime timeStamp;
    private List<Comment> commentList;
}
