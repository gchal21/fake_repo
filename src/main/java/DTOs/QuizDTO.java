package DTOs;

public class QuizDTO {
    private long Id;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCreatorName() {
        return CreatorName;
    }

    public void setCreatorName(String creatorName) {
        CreatorName = creatorName;
    }

    public long getCreatorId() {
        return CreatorId;
    }

    public void setCreatorId(long creatorId) {
        CreatorId = creatorId;
    }

    private String Name;
    private String CreatorName;
    private long CreatorId;

}
