package com.francisca.week9.ServiceImpl;

import com.francisca.week9.DTO.LoginDTO;
import com.francisca.week9.DTO.PostDTO;
import com.francisca.week9.DTO.UserDTO;
import com.francisca.week9.Model.Comment;
import com.francisca.week9.Model.Like;
import com.francisca.week9.Model.Post;
import com.francisca.week9.Model.User;
import com.francisca.week9.Repository.CommentRepository;
import com.francisca.week9.Repository.LikeRepository;
import com.francisca.week9.Repository.PostRepository;
import com.francisca.week9.Repository.UserRepository;
import com.francisca.week9.Response.CreatePostResponse;
import com.francisca.week9.Response.LoginResponse;
import com.francisca.week9.Response.RegisterResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.time.Month.SEPTEMBER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private PostRepository postRepository;
    @Mock
    private CommentRepository commentRepository;
    @Mock
    private LikeRepository likeRepository;
    @InjectMocks
    private UserServiceImpl userService;

    private User user;
    private Post post;
    private Comment comment;
    private Like like;
    List<User> userList = new ArrayList<>();
    List<Post> postList = new ArrayList<>();
     List<Comment> commentList = new ArrayList<>();
    List<Like> likeList = new ArrayList<>();
    private LocalDateTime localDateTime;

    @BeforeEach
    void setUp() {
        user = new User(1, "chesca", "chesca@gmail.com", "user", "francisca", localDateTime, localDateTime, postList, commentList, likeList);
        post = new Post(1,"book title", "new book", "book-title", "image.jpg", localDateTime, localDateTime, user, commentList, likeList);
        comment = new Comment(1, "nice book", localDateTime, localDateTime, post, user);
        like = new Like(1, true, localDateTime, localDateTime, post, user);
        localDateTime = LocalDateTime.of(2022, SEPTEMBER,7,6,30,40,50000);
    }

    @Test
    void register() {
        UserDTO userDTO = new UserDTO("may june", "july@gmail.com", "user", "1234");
        when(userRepository.save(user)).thenReturn(user);
        RegisterResponse registerResponse = new RegisterResponse("success", localDateTime, user);
        var actual = userService.register(userDTO);
        actual.setTimeStamp(localDateTime);
        actual.getUser().setCreatedAt(localDateTime);
        actual.getUser().setUpdatedAt(localDateTime);
        actual.getUser().setId(1);
        assertEquals(registerResponse, actual);
    }

    @Test
    void UserLoginSuccessfully() {
        LoginDTO loginDTO = new LoginDTO("chesca@gmail.com", "francisca");
        when(userRepository.findUserByEmail("chesca@gmail.com")).thenReturn(Optional.ofNullable(user));
        LoginResponse loginResponse = new LoginResponse("success", localDateTime);
        var actual = userService.login(loginDTO);
        actual.setTimeStamp(localDateTime);
        assertEquals(loginResponse, actual);



    }

    @Test
    void loginIncorrectPassword(){
        LoginDTO loginDTO = new LoginDTO("chesca@gmail.com", "fran1234");
        when(userRepository.findUserByEmail("chesca@gmail")).thenReturn(Optional.ofNullable(user));
        LoginResponse loginResponse = new LoginResponse("wrong password", localDateTime);
        var actual = userService.login(loginDTO);
        actual.setTimeStamp(localDateTime);
        assertEquals(loginResponse, actual);
    }


    @Test
    void createPost() {
        when(userRepository.findById(1)).thenReturn(Optional.ofNullable(user));
        PostDTO postDTO = new PostDTO("changing race", "new book", "image.jpg",1);
        CreatePostResponse createPostResponse = new CreatePostResponse("book title", localDateTime, post);
        var actual = userService.createPost(postDTO);
        actual.setTimeStamp(localDateTime);
        actual.getPost().setCreatedAt(localDateTime);
        actual.getPost().setUpdatedAt(localDateTime);
        actual.getPost().setSlug("book-title");
        actual.getPost().setId(1);
        assertEquals(createPostResponse, actual);

    }

    @Test
    void comment() {
    }

    @Test
    void like() {
    }

    @Test
    void searchComment() {
    }

    @Test
    void searchPost() {
    }

    @Test
    void findUserById() {
        when(userRepository.findById(1)).thenReturn(Optional.ofNullable(user));
        assertEquals(user, userService.findUserById(1));
    }

    @Test
    void findUserByEmail(){
        when(userRepository.findById(1)).thenReturn(Optional.ofNullable(user));
        assertEquals(user, userService.findUserByEmail("chesca@gmail.com"));
    }


    @Test
    void findPostById() {
        when(postRepository.findById(1)).thenReturn(Optional.ofNullable(post));
        assertEquals(post, userService.findPostById(1));
    }


}