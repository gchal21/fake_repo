package DAOclasses;

import entities.Message;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;

public class MessageDAO {
    private final BasicDataSource dataSource;


    public MessageDAO(BasicDataSource source){
        this.dataSource = source;
    }

    /**
     * returns list of Message objects related to the given user
     * (Message object contains who sent it, what was sent, when was it sent)
     * @param receiverId
     * @return
     */
    public ArrayList<Message> getMessages(long receiverId){
        ArrayList<Message> messages = new ArrayList<Message>();
        try {
            Connection conn=dataSource.getConnection();
            String query = "SELECT u.username AS senderUsername, m.content AS content, m.sendDate AS date " +
                    "FROM messages m " +
                    "JOIN users u ON m.senderId = u.id " +
                    "WHERE m.receiverId = ?;";
            PreparedStatement statement= conn.prepareStatement(query);
            statement.setLong(1, receiverId);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                String senderUsername = rs.getString("senderUsername");
                String content = rs.getString("content");
                Timestamp sendDate = rs.getTimestamp("date");
                messages.add(new Message(senderUsername, content, sendDate));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return messages;
    }
}
