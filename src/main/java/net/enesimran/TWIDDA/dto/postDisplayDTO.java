package net.enesimran.TWIDDA.dto;

import java.time.LocalDateTime;

public class postDisplayDTO {
    private String id;
    private String content;
    private String username;
    private String userId;
    private LocalDateTime timestamp;
    private long likes;
    private boolean liked;

    public postDisplayDTO() {}

    public postDisplayDTO(String postId, String content, String userId, String username, LocalDateTime timestamp, long likes, boolean liked) {
        this.id = postId;
        this.content = content;
        this.userId = userId;
        this.username = username;
        this.timestamp = timestamp;
        this.likes = likes;
        this.liked = liked;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    public long getLikes() {
        return likes;
    }
    public void setLikes(long likes) {
        this.likes = likes;
    }
    public boolean isLiked() {
        return liked;
    }
    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    

}
