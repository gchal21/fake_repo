package DTOs;

public class FriendshipRequestDTO {
    private long getSenderId() {
        return SenderId;
    }

    public void setSenderId(long senderId) {
        SenderId = senderId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    private long SenderId;
    private String UserName;

}
