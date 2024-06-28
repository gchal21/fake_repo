package DAOclasses.DAOinterfaces;

import entities.Quiz;
import java.util.List;

public interface QuizDAO {
    Quiz getById(Long id);
    List<Quiz> getAll();
    List<Quiz> getByCreatorId(Long creatorId);
    List<Quiz> getByCategoryId(Long categoryId);
    void insert(Quiz quiz);
    void update(Quiz quiz);
    void delete(Long id);
}
