package DAOclasses;

import entities.User;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UsersDAO {
    private final BasicDataSource dataSource;

    public UsersDAO(BasicDataSource source){
        this.dataSource = source;
    }


    /**
        adds given user to the database
        (id of the given User is ignored cause table has auto generated id as primary key)
     */
    public void addUser(User newUser){
        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement statement =
                    conn.prepareStatement("insert into users(username, password, email, roleId) values(?,?,?,?);");
            statement.setString(1, newUser.getUsername());
            statement.setString(2, newUser.getHashedPassword());
            statement.setString(3, newUser.getEmail());
            statement.setInt(4, newUser.getRoleId());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     *  returns User object related to the given username/email
     *  returns null if nothing could be found in the database
     */
    //todo: add email cause we will be prolly getting username or email when user's signing in
    public User getUser(String usernameOrMail){
        User user=null;
        try {
            Connection conn =dataSource.getConnection();
            PreparedStatement statement =
                    conn.prepareStatement("select * from users where username=? or email=?;");  //todo check: OR

            //todo replace * with column names
            statement.setString(1, usernameOrMail);
            statement.setString(2, usernameOrMail);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                user = new User(rs.getLong("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getInt("roleId"));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }








}
