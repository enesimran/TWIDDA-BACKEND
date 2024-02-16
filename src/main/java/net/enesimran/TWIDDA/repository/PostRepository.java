package net.enesimran.TWIDDA.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.enesimran.TWIDDA.dto.postDisplayDTO;
import net.enesimran.TWIDDA.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, String>{

//Query in Beihilfe von ChatGPT erstellt.
@Query("SELECT new net.enesimran.TWIDDA.dto.postDisplayDTO(" +
       "p.id, p.content, p.user.id, p.user.username, p.timestamp, " +
       "(SELECT COUNT(l) FROM Like l WHERE l.id.postId = p.id), " +
       "(CASE WHEN (SELECT COUNT(l) FROM Like l WHERE l.id.postId = p.id AND l.id.userId = :currentUserId) > 0 THEN true ELSE false END) " +
       ") FROM Post p JOIN p.user u  " +
       "GROUP BY p.id, p.content, p.timestamp, u.id, u.username")
Page<postDisplayDTO> findPostsWithUserInfo(Pageable pageable, @Param("currentUserId") String currentUserId);

}
