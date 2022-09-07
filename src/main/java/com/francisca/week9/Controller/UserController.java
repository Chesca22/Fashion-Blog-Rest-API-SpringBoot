package com.francisca.week9.Controller;

import com.francisca.week9.DTO.*;
import com.francisca.week9.Response.*;
import com.francisca.week9.Service.UserService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@AllArgsConstructor
@Getter
@Setter
@Slf4j
@RequestMapping(value="/api")

public class UserController {
private final UserService userService;

@GetMapping(value="/login")
public ResponseEntity<LoginResponse> login(@RequestBody LoginDTO loginDTO){
    log.info("loginSuccessfully", loginDTO.getEmail());
    return new ResponseEntity<>(userService.login(loginDTO), OK);
}


@PostMapping(value ="/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody UserDTO userDTO){
    log.info("Successfully Registered {} ", userDTO.getEmail());
    return new ResponseEntity<>(userService.register(userDTO), CREATED);
}

@PostMapping(value="/create")
    public ResponseEntity<CreatePostResponse> createPost(@RequestBody PostDTO postDTO){
    log.info("Successfully Registered {} ", postDTO.getTitle());
    return new ResponseEntity<>(userService.createPost(postDTO), CREATED);
}

@PostMapping(value="/comment/{user_id}/{post_id}")
public ResponseEntity<CommentResponse> comment(@PathVariable(value = "user_id") Integer user_id, @PathVariable(value="post_id") Integer post_id, @RequestBody CommentDTO commentDTO){
    log.info("Successfully commented {} ", commentDTO.getComment());
    return new ResponseEntity<>(userService.comment(user_id, post_id, commentDTO), OK);

}

    @PostMapping(value="/like/{user_id}/{post_id}")
    public ResponseEntity<LikedResponse> like(@PathVariable(value = "user_id") Integer user_id, @PathVariable(value="post_id") Integer post_id, @RequestBody LikeDTO likeDTO){
        log.info("Successfully liked {} ", likeDTO.isLiked());
        return new ResponseEntity<>(userService.like(user_id, post_id, likeDTO), OK);
    }
}
