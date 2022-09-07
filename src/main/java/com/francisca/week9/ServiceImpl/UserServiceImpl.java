package com.francisca.week9.ServiceImpl;

import com.francisca.week9.DTO.*;
import com.francisca.week9.Exception.PostAlreadyLikedException;
import com.francisca.week9.Exception.PostNotFoundException;
import com.francisca.week9.Exception.UserNotFoundException;
import com.francisca.week9.Model.Comment;
import com.francisca.week9.Model.Like;
import com.francisca.week9.Model.Post;
import com.francisca.week9.Model.User;
import com.francisca.week9.Repository.CommentRepository;
import com.francisca.week9.Repository.LikeRepository;
import com.francisca.week9.Repository.PostRepository;
import com.francisca.week9.Repository.UserRepository;
import com.francisca.week9.Response.*;
import com.francisca.week9.Service.UserService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
@Getter
@Setter
public class UserServiceImpl implements UserService {
    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

    @Override
    public  RegisterResponse register(UserDTO userDTO){
    User user = new User();
    user.setName(userDTO.getName());
    user.setEmail(userDTO.getEmail());
    user.setPassword(userDTO.getPassword());
    user.setRole(userDTO.getRole());
    userRepository.save(user);

    return new RegisterResponse("success", LocalDateTime.now(), user);
  }
    @Override
    public LoginResponse login(LoginDTO loginDTO){
      User user = findUserByEmail(loginDTO.getEmail());
      LoginResponse loginResponse =  null;
      if(user != null){
          if(user.getPassword().equals(loginDTO.getPassword())){
              loginResponse = new LoginResponse("success", LocalDateTime.now());
          }
          else {
              loginResponse = new LoginResponse("wrong Password", LocalDateTime.now());
          }
      }
      return loginResponse;

    }
    @Override
    public CreatePostResponse createPost(PostDTO postDTO){
        Post post = new Post();
        User user = findUserById(postDTO.getUser_id());
        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        post.setSlug(makeSlug(postDTO.getTitle()));
        post.setFeaturedImage(postDTO.getFeaturedImage());
        post.setUser(user);
        postRepository.save(post);
         return new CreatePostResponse ("success", LocalDateTime.now(), post);
    }

    @Override
    public CommentResponse comment(int user_id, int post_id, CommentDTO commentDTO){

        User user = findUserById(user_id);
        Post post = findPostById(post_id);
        Comment comment = new Comment();
        comment.setComment(commentDTO.getComment());
        comment.setUser(user);
        comment.setPost(post);
        commentRepository.save(comment);
        return new CommentResponse("", LocalDateTime.now(), comment, post);

    }

    @Override
    public LikedResponse like(int user_id, int post_id, LikeDTO likeDTO){
        Like like = new Like();
        User user = findUserById(user_id);
        Post post = findPostById(post_id);
        LikedResponse  likeResponse = null;
        Like duplicatedLike = likeRepository.findLikedByUserIdAndPostId(user_id, post_id);
        if(duplicatedLike == null){
            like.setLiked(likeDTO.isLiked());
            like.setUser(user);
            like.setPost(post);
            likeRepository.save(like);
            List<Like> likedList = likeRepository.likeList(post_id);
            likeResponse = new LikedResponse("success", LocalDateTime.now(), like, likedList.size());
        }
        else {
            likeRepository.delete(duplicatedLike);
            throw new PostAlreadyLikedException("This post has been liked");
        }
        return likeResponse;
    }

    @Override
    public SearchCommentResponse searchComment(String keyword){
        List<Comment> commentList = commentRepository.findByCommentContainingIgnoreCase(keyword);
        return new SearchCommentResponse("success", LocalDateTime.now(), commentList);
    }
    @Override
    public SearchPostResponse searchPost(String keyword){
        List<Post> postList = postRepository.findByTitleContainingIgnoreCase(keyword);
        return new SearchPostResponse("success", LocalDateTime.now(), postList);
    }

    public User findUserById(int id){
        return userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("User with ID: " + id + " not found"));

    }

    public Post findPostById(int id){
        return postRepository.findById(id).orElseThrow(()-> new PostNotFoundException("Post not found"));
    }
        public User findUserByEmail(String email){
        return userRepository.findUserByEmail(email).orElseThrow(()-> new UserNotFoundException("User with email: "+ email + " not found"));
        }

        public String makeSlug(String input){
        String nowWhiteSpace = WHITESPACE.matcher(input).replaceAll("-");
        String normalized = Normalizer.normalize(nowWhiteSpace, Normalizer.Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll((""));
        return slug.toLowerCase(Locale.ENGLISH);
    }



}
