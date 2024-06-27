package servlets.home;

import entities.Achievement;
import entities.Announcement;
import entities.Message;
import entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet("/home")
public class homeServlet extends HttpServlet {
    //should come from back
    private String userName;
    private List<Achievement> achievements;

    //handled by front
    private String currentSection;

    public void init() throws ServletException {
        currentSection = "Announcements";

        //DUMMY DATA
        //userName = getUserName() -- from back
        userName = "Aslan Abashidze";
        createUsersDummyData();
        createAchievementDummyData();
        createTablesDummyData();
        createMessagesDummyData();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        manageSection(request, response);

        request.setAttribute("currentSection", currentSection);
        request.setAttribute("userName", userName);
        request.setAttribute("achievements", achievements);
        request.setAttribute("messages", messages);
        request.getRequestDispatcher("/WEB-INF/home/home.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("currentSection", currentSection);
        manageSection(request, response);
        request.setAttribute("userName", userName);
        request.setAttribute("achievements", achievements);
        request.setAttribute("messages", messages);
        request.getRequestDispatcher("/WEB-INF/home/home.jsp").forward(request, response);
    }


    private List<Message> messages;
    private List<Announcement> announcements;
    private List<String> popularQuizzes;
    private List<String> friendsActivities;
    private List<String> recentlyCreatedQuizzes;
    private List<String> myRecentlyTakenQuizzes;
    private List<String> myRecentlyCreatedQuizzes;

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
                break;
            case "My Recently Created Quizzes":
                request.setAttribute("myRecentlyCreatedQuizzesData", myRecentlyCreatedQuizzes);
                break;
            case "Recently Created Quizzes":
                request.setAttribute("recentlyCreatedQuizzesData", recentlyCreatedQuizzes);
                break;
            case "Friends Activities":
                request.setAttribute("friendsActivitiesData", friendsActivities);
                break;
            case "My Recently Taken Quizzes":
                request.setAttribute("myRecentlyTakenQuizzesData", myRecentlyTakenQuizzes);
                break;
            default:
                request.setAttribute("announcementsData", announcements);
                request.setAttribute("userData", users);
        }
    }

    private void createUsersDummyData(){
        User u1 = new User(1, "Leonel Messi", "abc", "messi@freeuni.edu.ge", new Timestamp(System.currentTimeMillis()), 1);
        User u2 = new User(2, "Raphael Leao", "def", "leao@freeuni.edu.ge", new Timestamp(System.currentTimeMillis()), 1);
        this.users = Arrays.asList(u1, u2);
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
        this.popularQuizzes = new ArrayList<>();
        this.announcements = new ArrayList<>();
        this.myRecentlyCreatedQuizzes = new ArrayList<>();
        this.recentlyCreatedQuizzes = new ArrayList<>();
        this.friendsActivities = new ArrayList<>();
        this.myRecentlyTakenQuizzes =  new ArrayList<>();

        Announcement a1 = new Announcement(1, 1, "This is test announcement 1", new Timestamp(System.currentTimeMillis()));
        Announcement a2 = new Announcement(2, 1, "This is test announcement 2", new Timestamp(System.currentTimeMillis()));
        Announcement a3 = new Announcement(3, 1, "This is test announcement 3 big announcement this is a test announcement 3 big announcement this is a test announcement big announcement this is a test announcement big announcement this is a test announcement big announcement this is a test announcement big announcement", new Timestamp(System.currentTimeMillis()));
        Announcement a4 = new Announcement(4, 2, "This is test announcement 4", new Timestamp(System.currentTimeMillis()));
        Announcement a5 = new Announcement(5, 1, "This is test announcement 5", new Timestamp(System.currentTimeMillis()));
        Announcement a6 = new Announcement(6, 1, "This is test announcement 6", new Timestamp(System.currentTimeMillis()));
        Announcement a7 = new Announcement(7, 1, "This is test announcement 7", new Timestamp(System.currentTimeMillis()));
        Announcement a8 = new Announcement(8, 2, "This is test announcement 8", new Timestamp(System.currentTimeMillis()));
        Announcement a9 = new Announcement(9, 2, "This is test announcement 9", new Timestamp(System.currentTimeMillis()));
        Announcement a10 = new Announcement(10, 1, "This is test announcement 10", new Timestamp(System.currentTimeMillis()));

        announcements = Arrays.asList(a1, a2, a3, a4, a5,
                a6, a7, a8, a9, a10
                );
    }

    private void createMessagesDummyData(){
        Message m1 = new Message("Tengiz Kitovani", "Rafer xar", new Timestamp(System.currentTimeMillis()));
        Message m2 = new Message("Eduard Shevardnadze", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", new Timestamp(System.currentTimeMillis()));
        Message m3 = new Message("Mikheil Saakashvili", "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters.", new Timestamp(System.currentTimeMillis()));
        Message m4 = new Message("Gogita Gogolidze", "The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from \"de Finibus Bonorum et Malorum\" by Cicero are also reproduced in their exact original form.", new Timestamp(System.currentTimeMillis()));
        Message m5 = new Message("Sasha Vereli", "Saghol chemi", new Timestamp(System.currentTimeMillis()));

        Message m6 = new Message("Tengiz Kitovani", "Rafer xar", new Timestamp(System.currentTimeMillis()));
        Message m7 = new Message("Eduard Shevardnadze", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", new Timestamp(System.currentTimeMillis()));
        Message m8 = new Message("Mikheil Saakashvili", "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters.", new Timestamp(System.currentTimeMillis()));
        Message m9 = new Message("Gogita Gogolidze", "The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from \"de Finibus Bonorum et Malorum\" by Cicero are also reproduced in their exact original form.", new Timestamp(System.currentTimeMillis()));
        Message m10 = new Message("Sasha Vereli", "Saghol chemi", new Timestamp(System.currentTimeMillis()));

        messages = Arrays.asList(new Message[]{m1, m2, m3, m4, m5, m6, m7, m8, m9, m10});
//        messages = Arrays.asList(new Message[]{m1, m2, m3, m4});
//        messages = new ArrayList<>();
    }
}
