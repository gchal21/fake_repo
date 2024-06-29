package servlets.user;

import entities.Achievement;
import entities.Friendship;
import entities.User;
import servlets.FriendshipStatus;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import static servlets.FriendshipStatus.Pending;


@WebServlet("/user")
public class UserServlet extends HttpServlet {

    private User profile;
    private List<Achievement> achievements;
    private List<Friendship> friendRequests;

    public void init() throws ServletException {}

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("profile", profile);
        request.setAttribute("achievements", achievements);
        request.setAttribute("friendRequests", friendRequests);
        request.getRequestDispatcher("/WEB-INF/user/user.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getProfile(request);

        request.setAttribute("profile", profile);
        request.setAttribute("achievements", achievements);
        request.setAttribute("friendRequests", friendRequests);
        request.getRequestDispatcher("/WEB-INF/user/user.jsp").forward(request, response);
    }

    private void getProfile(HttpServletRequest request) {
        String userId = request.getParameter("id");
        //System.out.println("CURRENT USER: " + userId);

        //DUMMY DATA
        profile = new User(43L, "John Doe", "TAV", "johnDoe@gmail.com", new Timestamp(System.currentTimeMillis()), 1);

        Achievement a1 = new Achievement("Amateur Author", "Congratulations on creating your first quiz! You've taken the first step in becoming a quiz master. Keep going and continue to create more amazing quizzes!", "/images/achievements/amateurAuthor.png", new Timestamp(System.currentTimeMillis()));
        Achievement a2 = new Achievement("Prolific Author", "Amazing work! You've created 5 quizzes, showcasing your dedication and creativity. Keep it up and continue to inspire others with your fantastic quizzes!", "/images/achievements/prolificAuthor.png", new Timestamp(System.currentTimeMillis()));
        Achievement a3 = new Achievement("Prodigious Author", "Outstanding achievement! You've created 10 quizzes, demonstrating your passion and creativity. Keep creating and continue to engage and inspire others with your quizzes!", "/images/achievements/prodigiousAuthor.png", new Timestamp(System.currentTimeMillis()));
        Achievement a4 = new Achievement("Quiz Machine", "Fantastic! You've taken 10 quizzes, showing your enthusiasm and curiosity. Keep going and continue to challenge yourself with more quizzes!", "/images/achievements/quizMachine.png", new Timestamp(System.currentTimeMillis()));
        Achievement a5 = new Achievement("I Am The Greatest", "Congratulations! You've achieved the highest score, proving your exceptional knowledge and skill. Keep striving for greatness and setting new records!", "/images/achievements/iAmTheGreatest.png", new Timestamp(System.currentTimeMillis()));
        Achievement a6 = new Achievement("Practice Makes Perfect", "Great job! You've taken a quiz in practice mode, showing your dedication to improving and learning. Keep practicing and mastering your skills!", "/images/achievements/practiceMakesPerfect.png", new Timestamp(System.currentTimeMillis()));
        achievements = Arrays.asList(new Achievement[]{
                a1, a2, a3, a4, a5, a6});


        Friendship f1 = new Friendship(1L, "Gogita Gogolidze", 43L, "Pending", new Timestamp(System.currentTimeMillis()), null);
        Friendship f2 = new Friendship(2L, "Sandro Kereselidze", 43L, "Pending", new Timestamp(System.currentTimeMillis()), null);
        Friendship f3 = new Friendship(3L, "Mamuka Jugheli", 43L, "Pending", new Timestamp(System.currentTimeMillis()), null);

        friendRequests = Arrays.asList(f1, f2, f3);
    }
}
