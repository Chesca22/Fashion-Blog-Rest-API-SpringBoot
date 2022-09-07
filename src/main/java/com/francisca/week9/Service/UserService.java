package com.francisca.week9.Service;

import com.francisca.week9.DTO.*;
import com.francisca.week9.Response.*;

public interface UserService {
  RegisterResponse register(UserDTO userDTO);

  LoginResponse login(LoginDTO loginDTO);

  CreatePostResponse createPost(PostDTO postDTO);

    CommentResponse comment(int user_id, int post_id, CommentDTO commentDTO);

    LikedResponse like(int user_id, int post_id, LikeDTO likeDTO);

  SearchCommentResponse searchComment(String keyword);

  SearchPostResponse searchPost(String keyword);

}
