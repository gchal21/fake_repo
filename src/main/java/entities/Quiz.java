package entities;

import java.time.LocalDateTime;

public class Quiz {
    private Long id;
    private Long creatorId;
    private Integer maxScore;
    private LocalDateTime createDate;
    private Long categoryId;
    private String title;

    private String description;

    public Quiz(Long id, Long creatorId, Integer maxScore, LocalDateTime createDate, Long categoryId, String title,String description) {
        this.id = id;
        this.creatorId = creatorId;
        this.maxScore = maxScore;
        this.createDate = createDate;
        this.categoryId = categoryId;
        this.title = title;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Integer getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(Integer maxScore) {
        this.maxScore = maxScore;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
