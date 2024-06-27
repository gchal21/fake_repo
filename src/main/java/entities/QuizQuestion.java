package entities;

import java.util.List;

public class QuizQuestion {
    private Long id;
    private Long quizId;
    private String content;
    private String type;
    private int maxScore;
    private List<QuestionAnswer> answers;

    // Constructors
    public QuizQuestion() {}

    public QuizQuestion(Long id, Long quizId, String content, String type, int maxScore) {
        this.id = id;
        this.quizId = quizId;
        this.content = content;
        this.type = type;
        this.maxScore = maxScore;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

    public List<QuestionAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<QuestionAnswer> answers) {
        this.answers = answers;
    }
}
