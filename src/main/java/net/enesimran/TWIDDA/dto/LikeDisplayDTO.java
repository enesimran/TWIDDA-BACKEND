package net.enesimran.TWIDDA.dto;

public class LikeDisplayDTO {
 
    private boolean liked;
    private long likes;

    public boolean isLiked() {
        return liked;
    }
    public void setLiked(boolean liked) {
        this.liked = liked;
    }
    public long getLikes() {
        return likes;
    }
    public void setLikes(long likes) {
        this.likes = likes;
    }

}
