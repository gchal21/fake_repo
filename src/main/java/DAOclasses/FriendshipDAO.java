package DAOclasses;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

//TODO FINISH THIS CLASS
public class FriendshipDAO {
    private final BasicDataSource dataSource;


    public FriendshipDAO(BasicDataSource source){
        this.dataSource = source;
    }


    public void sendFriendRequest(long senderId, long receiverId){
        try {
            Connection conn=dataSource.getConnection();
            String query = "INSERT INTO friendship (senderId, receiverId, status) VALUES (?, ?, 'pending');";
            //todo check the status (make it enum or something)
            PreparedStatement statement= conn.prepareStatement(query);
            statement.setLong(1,senderId);
            statement.setLong(2, receiverId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void acceptFriendRequest(long senderId, long receiverId){
        try {
            Connection conn=dataSource.getConnection();
            //String query =
            //todo check the status (make it enum or something)

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
