package DAOclasses.DAOimps;

import DAOclasses.DAOinterfaces.TagDAO;
import entities.Tag;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TagDAOImpl implements TagDAO {

    @Override
    public Tag getById(Integer id) {
        String sql = "SELECT * FROM tags WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return extractTagFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Tag> getAll() {
        List<Tag> tags = new ArrayList<>();
        String sql = "SELECT * FROM tags";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                tags.add(extractTagFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tags;
    }

    @Override
    public void insert(Tag tag) {
        String sql = "INSERT INTO tags (name) VALUES (?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, tag.getName());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                tag.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Tag tag) {
        String sql = "UPDATE tags SET name = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, tag.getName());
            pstmt.setInt(2, tag.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM tags WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Tag extractTagFromResultSet(ResultSet rs) throws SQLException {
        Tag tag = new Tag();
        tag.setId(rs.getInt("id"));
        tag.setName(rs.getString("name"));
        return tag;
    }
}