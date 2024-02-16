package net.enesimran.TWIDDA.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.enesimran.TWIDDA.dto.postDisplayDTO;
import net.enesimran.TWIDDA.model.Post;
import net.enesimran.TWIDDA.repository.PostRepository;

@Service
public class PostService {
    
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public boolean doesPostExist(String postId) {
        return postRepository.existsById(postId);
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public Optional<Post> getPostById(String postId) {
        return postRepository.findById(postId);
    }

    public Page<postDisplayDTO> getPageablePosts(Pageable pageable, String userId) {
        return postRepository.findPostsWithUserInfo(pageable, userId);
    }

}
