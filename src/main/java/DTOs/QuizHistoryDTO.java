package DTOs;

import java.util.Date;

public class QuizHistoryDTO {
    private long UserId;
    private String UserName;

    public long getUserId() {
        return UserId;
    }

    public void setUserId(long userId) {
        UserId = userId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public Date getWriteDate() {
        return WriteDate;
    }

    public void setWriteDate(Date writeDate) {
        WriteDate = writeDate;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }

    public double getTime() {
        return Time;
    }

    public void setTime(double time) {
        Time = time;
    }

    private Date WriteDate;
    private int Score;
    private double Time;
}
