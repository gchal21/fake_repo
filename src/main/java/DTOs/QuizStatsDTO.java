package DTOs;

public class QuizStatsDTO {
    private int UserCount;

    public int getUserCount() {
        return UserCount;
    }

    public void setUserCount(int userCount) {
        UserCount = userCount;
    }

    public double getAverageScore() {
        return AverageScore;
    }

    public void setAverageScore(double averageScore) {
        AverageScore = averageScore;
    }

    public QuizStatsDTO(int userCount, double averageScore) {
        this.UserCount = userCount;
        this.AverageScore = averageScore;
    }


    private double AverageScore;
}
