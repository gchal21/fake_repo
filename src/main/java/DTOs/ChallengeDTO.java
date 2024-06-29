package DTOs;

public class ChallengeDTO {
    public long SenderId;

    public long getSenderId() {
        return SenderId;
    }

    public void setSenderId(long senderId) {
        SenderId = senderId;
    }

    public String getSenderName() {
        return SenderName;
    }

    public void setSenderName(String senderName) {
        SenderName = senderName;
    }

    public long getQuizId() {
        return QuizId;
    }

    public void setQuizId(long quizId) {
        QuizId = quizId;
    }

    public String getQuizName() {
        return QuizName;
    }

    public void setQuizName(String quizName) {
        QuizName = quizName;
    }

    public int getSenderMaxScore() {
        return SenderMaxScore;
    }

    public void setSenderMaxScore(int senderMaxScore) {
        SenderMaxScore = senderMaxScore;
    }

    private String SenderName;
    private long QuizId;
    private String QuizName;
    private int SenderMaxScore;
}
