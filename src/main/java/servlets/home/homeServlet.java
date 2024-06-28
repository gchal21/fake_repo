package servlets.home;

import entities.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet("/home")
public class homeServlet extends HttpServlet {
    //should come from back
    private User currentUser;

    //handled by front
    private String currentSection;

    public void init() throws ServletException {
        currentSection = "Announcements";

        //DUMMY DATA
        currentUser = new User(0L, "Aslan Abashidze", "fajf", "aslani@freeuni.edu.ge", new Timestamp(System.currentTimeMillis()), 1);
        createUsersDummyData();
        createAchievementDummyData();
        createTablesDummyData();
        createMessagesDummyData();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        manageSection(request, response);

        request.setAttribute("currentSection", currentSection);
        request.setAttribute("currentUser", currentUser);
        request.setAttribute("achievements", achievements);
        request.setAttribute("messages", messages);
        request.getRequestDispatcher("/WEB-INF/home/home.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("currentSection", currentSection);
        manageSection(request, response);
        request.setAttribute("currentUser", currentUser);
        request.setAttribute("achievements", achievements);
        request.setAttribute("messages", messages);
        request.getRequestDispatcher("/WEB-INF/home/home.jsp").forward(request, response);
    }

    private List<Achievement> achievements;
    private List<Message> messages;
    private List<Announcement> announcements;
    private List<Quiz> popularQuizzes;
    private List<String> friendsActivities;
    private List<Quiz> recentlyCreatedQuizzes;
    private List<Quiz> myRecentlyTakenQuizzes;
    private List<Quiz> myRecentlyCreatedQuizzes;
    private List<Category> categories;

    private List<User> users;


    private void manageSection(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String section = request.getParameter("section");

        if (section != null) {
            currentSection = section;
        } else {
            currentSection = "Announcements";
        }

        switch (currentSection) {
            case "Popular Quizzes":
                request.setAttribute("popularQuizzesData", popularQuizzes);
                request.setAttribute("categoriesData", categories);
                request.setAttribute("usersData", users);
                break;
            case "My Recently Created Quizzes":
                request.setAttribute("myRecentlyCreatedQuizzesData", myRecentlyCreatedQuizzes);
                request.setAttribute("categoriesData", categories);
                request.setAttribute("usersData", users);
                break;
            case "Recently Created Quizzes":
                request.setAttribute("recentlyCreatedQuizzesData", recentlyCreatedQuizzes);
                request.setAttribute("categoriesData", categories);
                request.setAttribute("usersData", users);
                break;
            case "Friends Activities":
                request.setAttribute("friendsActivitiesData", friendsActivities);
                break;
            case "My Recently Taken Quizzes":
                request.setAttribute("myRecentlyTakenQuizzesData", myRecentlyTakenQuizzes);
                request.setAttribute("categoriesData", categories);
                request.setAttribute("usersData", users);
                break;
            default:
                request.setAttribute("announcementsData", announcements);
                request.setAttribute("usersData", users);
        }
    }

    private void createUsersDummyData(){
        User u1 = new User(1, "Leonel Messi", "abc", "messi@freeuni.edu.ge", new Timestamp(System.currentTimeMillis()), 1);
        User u2 = new User(2, "Raphael Leao", "def", "leao@freeuni.edu.ge", new Timestamp(System.currentTimeMillis()), 1);
        User u3 = new User(3, "Leonardo Dicaprio", "goal", "leo@freeuni.edu.ge", new Timestamp(System.currentTimeMillis()), 1);
        User u4 = new User(4, "Davit the Builder", "king", "dideba@freeuni.edu.ge", new Timestamp(System.currentTimeMillis()), 1);
        this.users = Arrays.asList(u1, u2, u3, u4);
    }

    private void createAchievementDummyData(){
        Achievement a1 = new Achievement("Amateur Author", "You Have Created A Quiz", "/images/achievements/amateurAuthor.png", new Timestamp(System.currentTimeMillis()));
        Achievement a2 = new Achievement("Prolific Author", "You Have Created 5 Quiz", "/images/achievements/prolificAuthor.png", new Timestamp(System.currentTimeMillis()));
        Achievement a3 = new Achievement("Prodigious Author", "You Have Created 10 Quiz", "/images/achievements/prodigiousAuthor.png", new Timestamp(System.currentTimeMillis()));
        Achievement a4 = new Achievement("Quiz Machine", "You Took 10 Quizzes", "/images/achievements/quizMachine.png", new Timestamp(System.currentTimeMillis()));
        Achievement a5 = new Achievement("I Am The Greatest", "You Had Highest Score", "/images/achievements/iAmTheGreatest.png", new Timestamp(System.currentTimeMillis()));
        Achievement a6 = new Achievement("Practice Makes Perfect", "You Have Practiced", "/images/achievements/practiceMakesPerfect.png", new Timestamp(System.currentTimeMillis()));

        //achievements should not be null
        achievements = Arrays.asList(new Achievement[]{
                a1, a2, a3, a4, a5, a6});
    }

    private void createTablesDummyData(){
        this.friendsActivities = new ArrayList<>();


        createDummyAnnouncementsData();
        createDummyQuizzesData();
        createDummyCategoriesData();
    }

    private void createDummyAnnouncementsData(){
        Announcement a1 = new Announcement(1, 1, "This is test announcement 1", randomTimeStampMinutesAgo(10));
        Announcement a2 = new Announcement(2, 1, "This is test announcement 2", randomTimeStampMinutesAgo(50));
        Announcement a3 = new Announcement(3, 3, "This is test announcement 3 This is test announcement 3 This is test announcement 3 This is test announcement 3 This is test announcement 3 This is test announcement 3 This is test announcement 3 This is test announcement 3 This is test announcement 3 ", getRandomTimestampWithinDays(3));
        Announcement a4 = new Announcement(4, 2, "This is test announcement 4", getRandomTimestampWithinDays(7));
        Announcement a5 = new Announcement(5, 1, "This is test announcement 5", getRandomTimestampWithinDays(10));
        Announcement a6 = new Announcement(6, 4, "This is test announcement 6", getRandomTimestampWithinDays(15));
        Announcement a7 = new Announcement(7, 4, "This is test announcement 7", getRandomTimestampWithinDays(20));
        Announcement a8 = new Announcement(8, 2, "This is test announcement 8", getRandomTimestampWithinDays(30));
        Announcement a9 = new Announcement(9, 2, "This is test announcement 9", getRandomTimestampWithinDays(60));
        Announcement a10 = new Announcement(10, 1, "This is test announcement 10", getRandomTimestampWithinDays(90));

        announcements = Arrays.asList(a1, a2, a3, a4, a5,
                a6, a7, a8, a9, a10
        );
    }

    private void createDummyQuizzesData(){
        Quiz q1 = new Quiz(1L, 1L, 100, randomTimeStampMinutesAgo(10).toLocalDateTime(), 1L, "Which Country are you?", "This is the best quiz ever");
        Quiz q2 = new Quiz(2L, 1L, 20, randomTimeStampMinutesAgo(20).toLocalDateTime(), 2L, "Which Cheese are you?", "This is the best quiz ever");
        Quiz q3 = new Quiz(3L, 2L, 500, randomTimeStampMinutesAgo(100).toLocalDateTime(), 3L, "Which Car are you?", "This is the best quiz ever This is the best quiz ever This is the best quiz ever This is the best quiz ever This is the best quiz ever This is the best quiz ever This is the best quiz ever This is the best quiz ever");
        Quiz q4 = new Quiz(4L, 3L, 5, randomTimeStampMinutesAgo(1000).toLocalDateTime(), 4L, "Which Color are you?", "This is the best quiz ever");
        Quiz q5 = new Quiz(5L, 4L, 10, randomTimeStampMinutesAgo(16).toLocalDateTime(), 1L, "Which Christmas Carol are you?", "This is the best quiz ever");
        Quiz q6 = new Quiz(6L, 1L, 100, randomTimeStampMinutesAgo(100).toLocalDateTime(), 2L, "Which Cat are you?", "This is the best quiz ever");
        Quiz q7 = new Quiz(7L, 2L, 50, randomTimeStampMinutesAgo(100).toLocalDateTime(), 2L, "Which City are you?", "This is the best quiz ever");
        Quiz q8 = new Quiz(8L, 4L, 100, randomTimeStampMinutesAgo(300).toLocalDateTime(), 1L, "Which Camel are you?", "This is the best quiz ever");

        popularQuizzes = Arrays.asList(q1, q2, q3, q4, q5, q6, q7, q8);
        myRecentlyCreatedQuizzes = Arrays.asList(q1, q4, q6);
        recentlyCreatedQuizzes = Arrays.asList(q4, q6);
        myRecentlyTakenQuizzes = Arrays.asList(q2, q3, q4);
    }

    private void createDummyCategoriesData(){
        Category c1 = new Category(1, "Geography");
        Category c2 = new Category(2, "STEM");
        Category c3 = new Category(3, "Linguistics");
        Category c4 = new Category(4, "History");
        categories = Arrays.asList(c1, c2, c3, c4);
    }

    private void createMessagesDummyData(){
        Message m1 = new Message(6L, "Tengiz Kitovani", "Rafer xar", new Timestamp(System.currentTimeMillis()));
        Message m2 = new Message(7L, "Eduard Shevardnadze", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", new Timestamp(System.currentTimeMillis()));
        Message m3 = new Message(8L, "Mikheil Saakashvili", "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters.", new Timestamp(System.currentTimeMillis()));
        Message m4 = new Message(9L, "Gogita Gogolidze", "The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from \"de Finibus Bonorum et Malorum\" by Cicero are also reproduced in their exact original form.", new Timestamp(System.currentTimeMillis()));
        Message m5 = new Message(10L,"Sasha Vereli", "Saghol chemi", new Timestamp(System.currentTimeMillis()));

        Message m6 = new Message(6L, "Tengiz Kitovani", "Rafer xar", new Timestamp(System.currentTimeMillis()));
        Message m7 = new Message(7L, "Eduard Shevardnadze", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", new Timestamp(System.currentTimeMillis()));
        Message m8 = new Message(8L, "Mikheil Saakashvili", "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters.", new Timestamp(System.currentTimeMillis()));
        Message m9 = new Message(9L, "Gogita Gogolidze", "The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from \"de Finibus Bonorum et Malorum\" by Cicero are also reproduced in their exact original form.", new Timestamp(System.currentTimeMillis()));
        Message m10 = new Message(10L, "Sasha Vereli", "Saghol chemi", new Timestamp(System.currentTimeMillis()));

        messages = Arrays.asList(new Message[]{m1, m2, m3, m4, m5, m6, m7, m8, m9, m10});
//        messages = Arrays.asList(new Message[]{m1, m2, m3, m4});
//        messages = new ArrayList<>();
    }


    //HELPERS
    private static Timestamp randomTimeStampMinutesAgo(int minutesAgo) {
        return new Timestamp(Timestamp.valueOf(LocalDateTime.now().minusMinutes(minutesAgo)).getTime());
    }

    // Generate a random timestamp within the last few hours
    private static Timestamp getRandomTimestampWithinHours(int hoursAgo) {
        long offset = Timestamp.valueOf(LocalDateTime.now().minusHours(hoursAgo)).getTime();
        long end = Timestamp.valueOf(LocalDateTime.now()).getTime();
        long diff = end - offset + 1;
        return new Timestamp(offset + (long)(Math.random() * diff));
    }

    // Generate a random timestamp within the last few days
    private static Timestamp getRandomTimestampWithinDays(int daysAgo) {
        long offset = Timestamp.valueOf(LocalDateTime.now().minusDays(daysAgo)).getTime();
        long end = Timestamp.valueOf(LocalDateTime.now()).getTime();
        long diff = end - offset + 1;
        return new Timestamp(offset + (long)(Math.random() * diff));
    }
}
