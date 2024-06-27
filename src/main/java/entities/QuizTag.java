package entities;

public class QuizTag {
    private Long quizId;
    private Integer tagId;

    // Constructors
    public QuizTag() {}

    public QuizTag(Long quizId, Integer tagId) {
        this.quizId = quizId;
        this.tagId = tagId;
    }

    // Getters and Setters
    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }
}