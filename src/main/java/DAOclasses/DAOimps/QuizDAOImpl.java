package DAOclasses.DAOimps;

import DAOclasses.DAOinterfaces.QuizDAO;
import entities.Quiz;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuizDAOImpl implements QuizDAO {

    @Override
    public Quiz getById(Long id) {
        String sql = "SELECT * FROM quizzes WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return extractQuizFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Quiz> getAll() {
        List<Quiz> quizzes = new ArrayList<>();
        String sql = "SELECT * FROM quizzes";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                quizzes.add(extractQuizFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quizzes;
    }

    @Override
    public List<Quiz> getByCreatorId(Long creatorId) {
        List<Quiz> quizzes = new ArrayList<>();
        String sql = "SELECT * FROM quizzes WHERE creatorId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, creatorId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                quizzes.add(extractQuizFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quizzes;
    }

    @Override
    public List<Quiz> getByCategoryId(Long categoryId) {
        List<Quiz> quizzes = new ArrayList<>();
        String sql = "SELECT * FROM quizzes WHERE categoryId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, categoryId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                quizzes.add(extractQuizFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quizzes;
    }

    @Override
    public void insert(Quiz quiz) {
        String sql = "INSERT INTO quizzes (creatorId, maxScore, createDate, categoryId, title, description) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setLong(1, quiz.getCreatorId());
            pstmt.setInt(2, quiz.getMaxScore());
            pstmt.setTimestamp(3, Timestamp.valueOf(quiz.getCreateDate()));
            pstmt.setLong(4, quiz.getCategoryId());
            pstmt.setString(5, quiz.getTitle());
            pstmt.setString(6, quiz.getDescription());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                quiz.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Quiz quiz) {
        String sql = "UPDATE quizzes SET creatorId = ?, maxScore = ?, createDate = ?, categoryId = ?, title = ?, description = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, quiz.getCreatorId());
            pstmt.setInt(2, quiz.getMaxScore());
            pstmt.setTimestamp(3, Timestamp.valueOf(quiz.getCreateDate()));
            pstmt.setLong(4, quiz.getCategoryId());
            pstmt.setString(5, quiz.getTitle());
            pstmt.setString(6, quiz.getDescription());
            pstmt.setLong(7, quiz.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM quizzes WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Quiz extractQuizFromResultSet(ResultSet rs) throws SQLException {
        return new Quiz(
                rs.getLong("id"),
                rs.getLong("creatorId"),
                rs.getInt("maxScore"),
                rs.getTimestamp("createDate").toLocalDateTime(),
                rs.getLong("categoryId"),
                rs.getString("title"),
                rs.getString("description")
        );
    }
}
