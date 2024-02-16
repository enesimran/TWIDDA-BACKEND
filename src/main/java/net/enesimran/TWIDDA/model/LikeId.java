package net.enesimran.TWIDDA.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class LikeId implements Serializable {
    @Column(name = "USERID")
    private String userId; // entspricht dem Fremdschlüssel USERID
    @Column(name = "POSTID")
    private String postId; // entspricht dem Fremdschlüssel POSTID

    public LikeId() {}

    public LikeId(String userId, String postId) {
        this.userId = userId;
        this.postId = postId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LikeId likeId = (LikeId) o;
        return Objects.equals(userId, likeId.userId) &&
               Objects.equals(postId, likeId.postId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, postId);
    }
}
