package DAOclasses;

import enums.FriendshipStatus;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//TODO FINISH THIS CLASS AND MERGE DAO CLASSES RELATED TO USERS INTO ONE CLASS
//TODO TEST THIS CLASS
public class FriendshipDAO {
    private final BasicDataSource dataSource;


    public FriendshipDAO(BasicDataSource source){
        this.dataSource = source;
    }


    /**
     * send friend request from senderId to receiverId (status of the request is 'PENDING')
     * @param senderId
     * @param receiverId
     */
    public void sendFriendRequest(long senderId, long receiverId){
        try {
            Connection conn=dataSource.getConnection();
            String query = "INSERT INTO friendship (senderId, receiverId, status) VALUES (?, ?, ?);";
            //todo check the status (make it enum or something)
            PreparedStatement statement= conn.prepareStatement(query);
            statement.setLong(1,senderId);
            statement.setLong(2, receiverId);
            statement.setString(3, FriendshipStatus.PENDING.name()); //todo check this

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    /**
     * accepts the request or declines it depending on how isAccepted parameter is set
     * @param senderId
     * @param receiverId
     * @param isAccepted
     */
    public void acceptOrDeclineFriendRequest(long senderId, long receiverId, boolean isAccepted){
        try {
            Connection conn=dataSource.getConnection();

            String query = "UPDATE friendship SET status = ? WHERE senderId = ? AND receiverId = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            if(isAccepted){
                statement.setString(1, FriendshipStatus.ACCEPTED.name());

            }else{
                statement.setString(1, FriendshipStatus.DECLINED.name());

            }
            statement.setLong(2, senderId);
            statement.setLong(3, receiverId);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
