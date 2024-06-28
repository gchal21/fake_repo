package DAOclasses.DAOimps;

import DAOclasses.DAOinterfaces.QuestionAnswerDAO;
import entities.QuestionAnswer;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionAnswerDAOImpl implements QuestionAnswerDAO {

    @Override
    public QuestionAnswer getById(Long id) {
        String sql = "SELECT * FROM question_answers WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return extractQuestionAnswerFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<QuestionAnswer> getByQuestionId(Long questionId) {
        List<QuestionAnswer> answers = new ArrayList<>();
        String sql = "SELECT * FROM question_answers WHERE questionId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, questionId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                answers.add(extractQuestionAnswerFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answers;
    }

    @Override
    public void insert(QuestionAnswer questionAnswer) {
        String sql = "INSERT INTO question_answers (questionId, content, isCorrect) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setLong(1, questionAnswer.getQuestionId());
            pstmt.setString(2, questionAnswer.getContent());
            pstmt.setBoolean(3, questionAnswer.isCorrect());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                questionAnswer.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(QuestionAnswer questionAnswer) {
        String sql = "UPDATE question_answers SET questionId = ?, content = ?, isCorrect = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, questionAnswer.getQuestionId());
            pstmt.setString(2, questionAnswer.getContent());
            pstmt.setBoolean(3, questionAnswer.isCorrect());
            pstmt.setLong(4, questionAnswer.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM question_answers WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private QuestionAnswer extractQuestionAnswerFromResultSet(ResultSet rs) throws SQLException {
        QuestionAnswer questionAnswer = new QuestionAnswer();
        questionAnswer.setId(rs.getLong("id"));
        questionAnswer.setQuestionId(rs.getLong("questionId"));
        questionAnswer.setContent(rs.getString("content"));
        questionAnswer.setCorrect(rs.getBoolean("isCorrect"));
        return questionAnswer;
    }
}