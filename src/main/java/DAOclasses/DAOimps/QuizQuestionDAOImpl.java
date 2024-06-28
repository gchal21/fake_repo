package DAOclasses.DAOimps;

import DAOclasses.DAOinterfaces.QuizQuestionDAO;
import entities.QuizQuestion;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuizQuestionDAOImpl implements QuizQuestionDAO {

    @Override
    public QuizQuestion getById(Long id) {
        String sql = "SELECT * FROM quiz_questions WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return extractQuizQuestionFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<QuizQuestion> getByQuizId(Long quizId) {
        List<QuizQuestion> questions = new ArrayList<>();
        String sql = "SELECT * FROM quiz_questions WHERE quizId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, quizId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                questions.add(extractQuizQuestionFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questions;
    }

    @Override
    public void insert(QuizQuestion quizQuestion) {
        String sql = "INSERT INTO quiz_questions (quizId, content, type, maxScore) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setLong(1, quizQuestion.getQuizId());
            pstmt.setString(2, quizQuestion.getContent());
            pstmt.setString(3, quizQuestion.getType());
            pstmt.setInt(4, quizQuestion.getMaxScore());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                quizQuestion.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(QuizQuestion quizQuestion) {
        String sql = "UPDATE quiz_questions SET quizId = ?, content = ?, type = ?, maxScore = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, quizQuestion.getQuizId());
            pstmt.setString(2, quizQuestion.getContent());
            pstmt.setString(3, quizQuestion.getType());
            pstmt.setInt(4, quizQuestion.getMaxScore());
            pstmt.setLong(5, quizQuestion.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM quiz_questions WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private QuizQuestion extractQuizQuestionFromResultSet(ResultSet rs) throws SQLException {
        QuizQuestion quizQuestion = new QuizQuestion();
        quizQuestion.setId(rs.getLong("id"));
        quizQuestion.setQuizId(rs.getLong("quizId"));
        quizQuestion.setContent(rs.getString("content"));
        quizQuestion.setType(rs.getString("type"));
        quizQuestion.setMaxScore(rs.getInt("maxScore"));
        return quizQuestion;
    }
}
