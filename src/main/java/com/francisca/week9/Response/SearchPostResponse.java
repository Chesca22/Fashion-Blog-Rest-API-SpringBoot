package com.francisca.week9.Response;

import com.francisca.week9.Model.Post;
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
public class SearchPostResponse {
    private String message;
    private LocalDateTime timeStamp;
    private List<Post> post;
}
