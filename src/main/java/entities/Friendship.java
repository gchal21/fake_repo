package entities;

import java.sql.Timestamp;

//TODO FINISH THIS CLASS
public class Friendship {
    private long requestSenderId;
    private String senderUsername;
    private long requestReceiverId;
    private String requestStatus;
    private Timestamp sendDate;
    private Timestamp respondDate;

    public Friendship(long requestSenderId, String senderUsername, long requestReceiverId, String requestStatus, Timestamp sendDate, Timestamp respondDate) {
        this.requestSenderId = requestSenderId;
        this.senderUsername = senderUsername;
        this.requestReceiverId = requestReceiverId;
        this.requestStatus = requestStatus;
        this.sendDate = sendDate;
        this.respondDate = respondDate;
    }
    public String getSenderUsername() {
        return senderUsername;
    }
    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }
    public long getRequestSenderId() {
        return requestSenderId;
    }
    public void setRequestSenderId(long requestSenderId) {
        this.requestSenderId = requestSenderId;
    }
    public long getRequestReceiverId() {
        return requestReceiverId;
    }
    public void setRequestReceiverId(long requestReceiverId) {
        this.requestReceiverId = requestReceiverId;
    }
    public String getRequestStatus() {
        return requestStatus;
    }
    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }
    public Timestamp getSendDate() {
        return sendDate;
    }
    public void setSendDate(Timestamp sendDate) {
        this.sendDate = sendDate;
    }
    public Timestamp getRespondDate() {
        return respondDate;
    }
    public void setRespondDate(Timestamp respondDate) {
        this.respondDate = respondDate;
    }

}

