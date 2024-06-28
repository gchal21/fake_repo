package DAOclasses.DAOinterfaces;

import entities.QuizTag;
import entities.Tag;

import java.util.List;

public interface QuizTagDAO {
    List<Tag> getTagsByQuizId(Long quizId);
    List<Long> getQuizIdsByTagId(Integer tagId);
    void insert(QuizTag quizTag);
    void delete(Long quizId, Integer tagId);
}