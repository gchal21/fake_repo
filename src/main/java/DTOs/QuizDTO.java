package DTOs;

import com.sun.xml.internal.bind.v2.model.annotation.Quick;

public class QuizDTO {


    public QuizDTO(long id, String name, String creatorName, long creatorId){
        this.Id = id;
        this.Name = name;
        this.CreatorName = creatorName;
        this.CreatorId = creatorId;
    }
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
