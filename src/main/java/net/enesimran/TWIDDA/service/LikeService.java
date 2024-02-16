package net.enesimran.TWIDDA.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.enesimran.TWIDDA.model.Like;
import net.enesimran.TWIDDA.model.LikeId;
import net.enesimran.TWIDDA.repository.LikeRepository;

@Service
public class LikeService {

    private final LikeRepository likeRepository;

    @Autowired
    public LikeService(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    public List<Like> getLikesForPost(String postId) {
        return likeRepository.findByIdPostId(postId);
    }

    public List<Like> getLikesForUser(String userId) {
        return likeRepository.findByIdUserId(userId);
    }

    public Like addLike(String userId, String postId) {
        LikeId likeId = new LikeId(userId, postId);
        Like like = new Like();
        like.setId(likeId);
        return likeRepository.save(like);
    }

    public void removeLike(String userId, String postId) {
        LikeId likeId = new LikeId(userId, postId);
        likeRepository.deleteById(likeId);
    }

    public boolean isLiked(String postId, String userId) {
        LikeId likeId = new LikeId(userId, postId);
        return likeRepository.existsById(likeId);
    }

    public long countLikesForPost(String postId) {
        List<Like> likes = likeRepository.findByIdPostId(postId);
        return likes.size();
    }
     public long countLikesForUser(String userId) {
        List<Like> likes = likeRepository.findByIdUserId(userId);
        return likes.size();
    }

}
