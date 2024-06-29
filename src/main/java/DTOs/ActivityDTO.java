package DTOs;

import entities.Achievement;
import entities.Quiz;
import enums.ActivityType;

import java.util.Date;

public class ActivityDTO {
    private ActivityType Type;

    public ActivityType getType() {
        return Type;
    }

    public void setType(ActivityType type) {
        Type = type;
    }

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

    public Date getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(Date createDate) {
        CreateDate = createDate;
    }

    public entities.Achievement getAchievement() {
        return Achievement;
    }

    public void setAchievement(entities.Achievement achievement) {
        Achievement = achievement;
    }

    public QuizDTO getQuiz() {
        return Quiz;
    }

    public void setQuiz(QuizDTO quiz) {
        Quiz = quiz;
    }

    private long UserId;
    private String UserName;
    private Date CreateDate;
    private Achievement Achievement;
    private QuizDTO Quiz;
}
