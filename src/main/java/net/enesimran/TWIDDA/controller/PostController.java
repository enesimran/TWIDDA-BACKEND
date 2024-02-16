package net.enesimran.TWIDDA.controller;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.enesimran.TWIDDA.dto.postDisplayDTO;
import net.enesimran.TWIDDA.model.Post;
import net.enesimran.TWIDDA.model.User;
import net.enesimran.TWIDDA.service.LikeService;
import net.enesimran.TWIDDA.service.PostService;
import net.enesimran.TWIDDA.service.UserService;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;
    private final UserService userService;
    private final LikeService likeService;
    
    @Autowired
    public PostController(PostService postService, UserService userService, LikeService likeService) {
        this.postService = postService;
        this.userService = userService;
        this.likeService = likeService;
        
    }


    @PostMapping("/create")
    public ResponseEntity<?> createPost(@RequestParam String content) {

        String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Optional<User> returnObject = userService.getFullUserById(userId);

        Post newPost = new Post();
        User author = new User();
        author.setId(returnObject.get().getId());
        newPost.setContent(content);
        newPost.setUser(author);

        while (postService.doesPostExist(newPost.getId())) {
            newPost.setId(UUID.randomUUID().toString());
        }
        LocalDateTime timestamp = LocalDateTime.now();
        newPost.setTimestamp(timestamp);

        try {
            Post createdPost = postService.createPost(newPost);
            return ResponseEntity.ok(createdPost);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/info/{postId}")
    public ResponseEntity<?> getPostInfo(@PathVariable String postId) {
        
        if (postService.doesPostExist(postId)) {
            postDisplayDTO returnDTO = new postDisplayDTO();
            Optional<Post> post = postService.getPostById(postId);
            returnDTO.setContent(post.get().getContent());
            returnDTO.setId(post.get().getId());
            returnDTO.setTimestamp(post.get().getTimestamp());
            returnDTO.setUserId(post.get().getUser().getId());
            returnDTO.setUsername(post.get().getUser().getUsername());
            returnDTO.setLiked(likeService.isLiked(post.get().getId(), post.get().getUser().getId()));
            returnDTO.setLikes(likeService.countLikesForPost(postId));
            return ResponseEntity.ok(returnDTO);
        } else {
            return ResponseEntity.badRequest().body("Dieser Post existiert nicht.");
        }
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<postDisplayDTO>> getLazyPosts(Pageable pageable) {
        String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Page<postDisplayDTO> pageablePosts = postService.getPageablePosts(pageable, userId);
        if (pageablePosts != null) {
            return ResponseEntity.ok(pageablePosts);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
}
