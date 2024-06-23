package entities;

public class User {
    private final long id;
    private final String username;
    private final String hashedPassword;
    private final String email;
    private final int roleId;

    public User(long id, String username, String hashedPassword, String email, int roleId) {
        this.id = id;
        this.username = username;
        this.hashedPassword = hashedPassword;
        this.email = email;
        this.roleId = roleId;
    }


    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    /**
     * NOTE:
     * @return hashed password related to the user
     */
    public String getHashedPassword(){
        return hashedPassword;
    }

    public String getEmail() {
        return email;
    }
    public int getRoleId() {
        return roleId;
    }


}
