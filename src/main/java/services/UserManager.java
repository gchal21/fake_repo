package services;

import DAOclasses.UsersDAO;
import entities.User;
import exceptions.InvalidTokenException;
import exceptions.TokenExpiredException;
import utils.JwtUtil;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

public class UserManager {
    private final UsersDAO usersDAO;

    private UserManager(UsersDAO usersDAO) {
        this.usersDAO = usersDAO;
    }

    public static UserManager getInstance(HttpSession session){
        UserManager userManager = (UserManager) session.getAttribute(UserManager.class.getSimpleName());
        if (userManager == null) {
            userManager = new UserManager(UsersDAO.getInstance(session));
            session.setAttribute(UserManager.class.getSimpleName(), userManager);
        }
        return userManager;
    }


    public boolean registerUser(String username, String email, String password) {
        if (usersDAO.getUser(username) != null || usersDAO.getUser(email) != null) {
            return false;
        }

        String hashedPassword = hashPassword(password);

        User newUser = new User(0, username, hashedPassword, email, new Timestamp(System.currentTimeMillis()), 1);
        usersDAO.addUser(newUser);

        return true;
    }

    public String login(String usernameOrEmail, String password) {
        User user = usersDAO.getUser(usernameOrEmail);

        if (user != null && verifyPassword(password, user.getHashedPassword())) {
            return JwtUtil.generateToken(user.getId(), user.getUsername(), user.getRoleId());
        }

        return null;
    }

    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    private boolean verifyPassword(String inputPassword, String storedHash) {
        String inputHash = hashPassword(inputPassword);
        return inputHash.equals(storedHash);
    }

    public static void validateToken(String token) throws InvalidTokenException, TokenExpiredException {
        Map<String, Object> claims = JwtUtil.verifyToken(token);

        if (claims == null) {
            throw new InvalidTokenException("Token is invalid");
        }

        long expiration = Long.parseLong(claims.get("exp").toString());
        if (expiration < new Date().getTime() / 1000) {
            throw new TokenExpiredException("Token has expired");
        }

    }

    public static long getUserIdFromToken(String token) throws InvalidTokenException {
        Map<String, Object> claims = JwtUtil.verifyToken(token);

        if (claims == null) {
            throw new InvalidTokenException("Token is invalid");
        }

        return Long.parseLong(claims.get("userId").toString());
    }

}