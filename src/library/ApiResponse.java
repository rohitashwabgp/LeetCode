package library;

public class ApiResponse {
    private Long userId;
    private String userName;

    public ApiResponse(Long userId, String userName, Long totalPosts) {
        this.userId = userId;
        this.userName = userName;
        this.totalPosts = totalPosts;
    }

    private Long totalPosts;

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setTotalPosts(Long totalPosts) {
        this.totalPosts = totalPosts;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public Long getTotalPosts() {
        return totalPosts;
    }
}
