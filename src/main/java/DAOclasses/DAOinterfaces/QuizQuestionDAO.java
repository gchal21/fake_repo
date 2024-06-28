package DAOclasses.DAOinterfaces;

import entities.QuizQuestion;

import java.util.List;

public interface QuizQuestionDAO {
    QuizQuestion getById(Long id);
    List<QuizQuestion> getByQuizId(Long quizId);
    void insert(QuizQuestion quizQuestion);
    void update(QuizQuestion quizQuestion);
    void delete(Long id);
}
