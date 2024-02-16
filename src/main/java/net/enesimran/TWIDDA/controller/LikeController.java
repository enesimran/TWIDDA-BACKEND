package net.enesimran.TWIDDA.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.enesimran.TWIDDA.dto.LikeDisplayDTO;
import net.enesimran.TWIDDA.service.LikeService;
import net.enesimran.TWIDDA.service.PostService;
import net.enesimran.TWIDDA.service.UserService;

@RestController
@RequestMapping("/api/likes")
public class LikeController {

    private final LikeService likeService;
    private final UserService userService;
    private final PostService postService;

    @Autowired
    public LikeController(LikeService likeService, UserService userService, PostService postService) {
        this.likeService = likeService;
        this.userService = userService;
        this.postService = postService;
    }

    @PostMapping("/toggle")
    public ResponseEntity<?> toggleLike(@RequestParam String postId) {
        String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        LikeDisplayDTO displayDTO = new LikeDisplayDTO();
        if (postService.doesPostExist(postId) && userService.doesUserExistById(userId)) {
            if (likeService.isLiked(postId, userId)) {
                likeService.removeLike(userId, postId);
                displayDTO.setLikes(likeService.countLikesForPost(postId));
                displayDTO.setLiked(likeService.isLiked(postId, userId));
                return ResponseEntity.ok(displayDTO);
            } else {
                likeService.addLike(userId, postId);
                displayDTO.setLikes(likeService.countLikesForPost(postId));
                displayDTO.setLiked(likeService.isLiked(postId, userId));
                return ResponseEntity.ok(displayDTO);
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post nicht gefunden.");
        }
    }

    @PostMapping("/post")
    public ResponseEntity<?> getPostLikes(@RequestParam String postId) {
        if (postService.doesPostExist(postId)) {
            return ResponseEntity.ok(likeService.getLikesForPost(postId));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post nicht gefunden.");
        }
    }

}
