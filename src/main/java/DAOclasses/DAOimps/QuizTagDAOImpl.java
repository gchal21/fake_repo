package DAOclasses.DAOimps;

import DAOclasses.DAOinterfaces.QuizTagDAO;
import entities.QuizTag;
import entities.Tag;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuizTagDAOImpl implements QuizTagDAO {

    @Override
    public List<Tag> getTagsByQuizId(Long quizId) {
        List<Tag> tags = new ArrayList<>();
        String sql = "SELECT t.* FROM tags t JOIN quiz_tags qt ON t.id = qt.tagId WHERE qt.quizId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, quizId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Tag tag = new Tag();
                tag.setId(rs.getInt("id"));
                tag.setName(rs.getString("name"));
                tags.add(tag);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tags;
    }

    @Override
    public List<Long> getQuizIdsByTagId(Integer tagId) {
        List<Long> quizIds = new ArrayList<>();
        String sql = "SELECT quizId FROM quiz_tags WHERE tagId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, tagId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                quizIds.add(rs.getLong("quizId"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quizIds;
    }

    @Override
    public void insert(QuizTag quizTag) {
        String sql = "INSERT INTO quiz_tags (quizId, tagId) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, quizTag.getQuizId());
            pstmt.setInt(2, quizTag.getTagId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long quizId, Integer tagId) {
        String sql = "DELETE FROM quiz_tags WHERE quizId = ? AND tagId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, quizId);
            pstmt.setInt(2, tagId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}