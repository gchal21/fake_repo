package DAOclasses;

import entities.User;
import org.apache.commons.dbcp2.BasicDataSource;
import services.UserManager;
import utils.DataSourceConfig;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


//todo maybe add something like a password changing method for the user (as an extension might be useful)
//todo ADD FILTERING METHOD FOR SEARCHING (USE 'IS LIKE' IN SQL)
public class UsersDAO {
    private final BasicDataSource dataSource;
    private final String USERS_TABLE = "users";
    private UsersDAO(BasicDataSource source){
        this.dataSource = source;
    }

    public static UsersDAO getInstance(HttpSession session){
        UsersDAO usersDAO = (UsersDAO) session.getAttribute(UsersDAO.class.getSimpleName());
        if (usersDAO == null) {
            usersDAO = new UsersDAO(DataSourceConfig.getDataSource());
            session.setAttribute(UsersDAO.class.getSimpleName(), usersDAO);
        }
        return usersDAO;
    }


    /**
        adds given user to the database
        (id of the given User is ignored cause table has auto generated id as primary key,
        also create date is ignored cause we let the table add the current time by default)

        NOTE: client is responsible to call getUser by username or email before calling AddUser
        to see if that user already exists, else we throw runtime exception

     */
    public void addUser(User newUser){
        try {
            Connection conn = dataSource.getConnection();
            String query= "INSERT INTO "+USERS_TABLE+"(username, password, email, roleId) VALUES(?,?,?,?);";
            PreparedStatement statement =
                    conn.prepareStatement(query);
            statement.setString(1, newUser.getUsername());
            statement.setString(2, newUser.getHashedPassword());
            statement.setString(3, newUser.getEmail());
            statement.setInt(4, newUser.getRoleId());
            statement.execute();
        }
//        catch (java.sql.SQLIntegrityConstraintViolationException e) {
//            // Handle the specific case where the username or email already exists
//            if (e.getMessage().toLowerCase().contains("users.username")) {
//                System.out.println("The given username already exists.");
//            } else if (e.getMessage().toLowerCase().contains("users.email")) {
//                System.out.println("The given email already exists.");
//            } else {
//                // Print the general constraint violation message
//                System.out.println("Unique constraint violation: " + e.getMessage());
//            }
//        }
        catch (SQLException e) {

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
            String query="SELECT u.id, u.username, u.password, u.email, u.createDate, u.roleId" +
                    " FROM "+USERS_TABLE+" AS u WHERE username=? OR email=?;";
            PreparedStatement statement =
                    conn.prepareStatement(query);  //todo check: OR

            //todo replace * with column names
            statement.setString(1, usernameOrMail);
            statement.setString(2, usernameOrMail);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                user = new User(rs.getLong("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getTimestamp("createDate"),
                        rs.getInt("roleId"));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    /**
     * returns list of users if their username or email starts with the given prefix
     * @param usernameOrMailPrefix
     * @return
     */
    public ArrayList<User> getUserByPrefix(String usernameOrMailPrefix){
        ArrayList<User> results=new ArrayList<>();
        try {
            Connection conn =dataSource.getConnection();
            String query = "SELECT u.id, u.username, u.password, u.email, u.createDate, u.roleId" +
                    " FROM "+USERS_TABLE+" AS u WHERE u.username LIKE ? OR u.email LIKE ? ;";


            PreparedStatement statement =
                    conn.prepareStatement(query);

            statement.setString(1, usernameOrMailPrefix + "%");
            statement.setString(2, usernameOrMailPrefix + "%");
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                User user = new User(rs.getLong("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getTimestamp("createDate"),
                        rs.getInt("roleId"));

                results.add(user);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return results;
    }

    /**
     * returns all the users from database
     * @return
     */
    public ArrayList<User> getAllUsers(){
        ArrayList<User> allUsers=new ArrayList<>();
        try {
            Connection conn =dataSource.getConnection();
            String query="SELECT u.id, u.username, u.password, u.email, u.createDate, u.roleId" +
                    " FROM "+USERS_TABLE+" AS u; ";
            PreparedStatement statement =
                    conn.prepareStatement(query);

            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                User user = new User(rs.getLong("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getTimestamp("createDate"),
                        rs.getInt("roleId"));

                allUsers.add(user);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allUsers;
    }

    /**
     * returns true if the given user (id) has admin privileges
     * @param userId
     * @return
     */
    //todo check this later and make more convenient (roleId enum or something is needed i guess)
    public boolean isAdmin(long userId){
        boolean isAdmin=false;
        try {
            Connection conn =dataSource.getConnection();
            String query="SELECT u.roleId" +
                    " FROM "+USERS_TABLE+" AS u WHERE u.id = ? ;";
            PreparedStatement statement =
                    conn.prepareStatement(query);


            statement.setLong(1, userId);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                int roleId = rs.getInt("roleId");
                if(roleId==1){ //1 is admin and 2 is standard-user
                    isAdmin=true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return isAdmin;
    }

}
