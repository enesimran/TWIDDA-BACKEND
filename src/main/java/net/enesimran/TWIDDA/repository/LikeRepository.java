package net.enesimran.TWIDDA.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.enesimran.TWIDDA.model.Like;
import net.enesimran.TWIDDA.model.LikeId;

@Repository
public interface LikeRepository extends JpaRepository<Like, LikeId> {
    List<Like> findByIdPostId(String postId);
    List<Like> findByIdUserId(String userId);
    
}
