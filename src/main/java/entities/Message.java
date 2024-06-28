package entities;

import java.sql.Timestamp;

public class Message {
    //todo add sender id
    private long senderId;
    private String senderUsername;
    private String content;
    private Timestamp sendDate;

    public Message(long senderId,String senderUsername, String content, Timestamp sendDate) {
        this.senderId = senderId;
        this.senderUsername = senderUsername;
        this.content = content;
        this.sendDate = sendDate;

    }

    public long getSenderId() {
        return senderId;
    }
    public void setSenderId(long senderId) {
        this.senderId = senderId;
    }
    public String getSenderUsername() {
        return senderUsername;
    }
    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Timestamp getSendDate() {
        return sendDate;
    }
    public void setSendDate(Timestamp sendDate) {
        this.sendDate = sendDate;
    }

    @Override
    public String toString() {
        return "SENDER: " + senderUsername + " CONTENT: " + content + " SEND DATE: " + sendDate;
    }
}
