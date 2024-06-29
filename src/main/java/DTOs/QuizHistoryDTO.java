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

    public QuizHistoryDTO(long userId, String userName, Date writeDate, int score, double time) {
        this.UserId = userId;
        this.UserName = userName;
        this.WriteDate = writeDate;
        this.Score = score;
        this.Time = time;
    }


    private Date WriteDate;
    private int Score;
    private double Time;
}
