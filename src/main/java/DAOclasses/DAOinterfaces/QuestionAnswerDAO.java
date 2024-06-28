package DAOclasses.DAOinterfaces;

import entities.QuestionAnswer;

import java.util.List;

public interface QuestionAnswerDAO {
    QuestionAnswer getById(Long id);
    List<QuestionAnswer> getByQuestionId(Long questionId);
    void insert(QuestionAnswer questionAnswer);
    void update(QuestionAnswer questionAnswer);
    void delete(Long id);
}