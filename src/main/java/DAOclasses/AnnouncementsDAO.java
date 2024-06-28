package DAOclasses;

import entities.Announcement;
import org.apache.commons.dbcp2.BasicDataSource;
import utils.DataSourceConfig;

import javax.servlet.http.HttpSession;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnnouncementsDAO {
    private final BasicDataSource dataSource;
    private final String ANNOUNCEMENTS_TABLE = "announcements";

//    public AnnouncementsDAO(BasicDataSource source) {
//        this.dataSource = source;
//    }
    private AnnouncementsDAO(BasicDataSource source) {
        this.dataSource = source;
    }

    //kind of looks like a singleton pattern (in every session AnnouncementDAO is only created once)
    public static AnnouncementsDAO getInstance(HttpSession session){
        AnnouncementsDAO announcementsDAO = (AnnouncementsDAO) session.getAttribute(AnnouncementsDAO.class.getSimpleName());
        if (announcementsDAO == null) {
            announcementsDAO = new AnnouncementsDAO(DataSourceConfig.getDataSource());
            session.setAttribute(AnnouncementsDAO.class.getSimpleName(), announcementsDAO);
        }
        return announcementsDAO;
    }

    public void addAnnouncement(long userId, String content) {
        try (Connection conn = dataSource.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(
//                     "INSERT INTO " + ANNOUNCEMENTS_TABLE +
//                             " (userId, content, createDate) VALUES (?, ?, ?)")) {
//            stmt.setLong(1, announcement.getUserId());
//            stmt.setString(2, announcement.getContent());
//
//
//            // todo: not sure about if this is needed maybe do not pass createDate so that it
//            //automatically sets current time in that column
//            stmt.setTimestamp(3, announcement.getCreateDate());
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO " + ANNOUNCEMENTS_TABLE +
                             " (userId, content) VALUES (?, ?)")) {
            stmt.setLong(1, userId);
            stmt.setString(2, content);


//            // todo: not sure about if this is needed maybe do not pass createDate so that it
//            //automatically sets current time in that column
//            stmt.setTimestamp(3, announcement.getCreateDate());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error adding announcement", e);
        }
    }

    public Announcement getAnnouncement(long id) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT * FROM " + ANNOUNCEMENTS_TABLE + " WHERE id = ?")) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Announcement(
                            rs.getLong("id"),
                            rs.getLong("userId"),
                            rs.getString("content"),
                            rs.getTimestamp("createDate")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving announcement", e);
        }
        return null;
    }

    public List<Announcement> getAllAnnouncements() {
        List<Announcement> announcements = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM " + ANNOUNCEMENTS_TABLE)) {
            while (rs.next()) {
                announcements.add(new Announcement(
                        rs.getLong("id"),
                        rs.getLong("userId"),
                        rs.getString("content"),
                        rs.getTimestamp("createDate")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all announcements", e);
        }
        return announcements;
    }
}