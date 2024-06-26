package servlets.home;

import entities.Achievement;

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
        userName = "Mikheil Zghenti";

        Achievement a1 = new Achievement("Amateur Author", "You Have Created A Quiz", "/images/achievements/amateurAuthor.png", new Timestamp(System.currentTimeMillis()));
        Achievement a2 = new Achievement("Prolific Author", "You Have Created 5 Quiz", "/images/achievements/prolificAuthor.png", new Timestamp(System.currentTimeMillis()));
        Achievement a3 = new Achievement("Prodigious Author", "You Have Created 10 Quiz", "/images/achievements/prodigiousAuthor.png", new Timestamp(System.currentTimeMillis()));
        Achievement a4 = new Achievement("Quiz Machine", "You Took 10 Quizzes", "/images/achievements/quizMachine.png", new Timestamp(System.currentTimeMillis()));
        Achievement a5 = new Achievement("I Am The Greatest", "You Had Highest Score", "/images/achievements/iAmTheGreatest.png", new Timestamp(System.currentTimeMillis()));
        Achievement a6 = new Achievement("Practice Makes Perfect", "You Have Practiced", "/images/achievements/practiceMakesPerfect.png", new Timestamp(System.currentTimeMillis()));
//

        //achievements should not be null
        achievements = Arrays.asList(new Achievement[]{
                a1, a2, a3, a4, a5, a6});

        this.popularQuizzes = new ArrayList<>();
        this.announcements = new ArrayList<>();
        this.myRecentlyCreatedQuizzes = new ArrayList<>();
        this.recentlyCreatedQuizzes = new ArrayList<>();
        this.friendsActivities = new ArrayList<>();
        this.myRecentlyTakenQuizzes =  new ArrayList<>();
        announcements.add("Announcement 1");
        announcements.add("Announcement 2");
        popularQuizzes.add("Quiz 1");
        popularQuizzes.add("Quiz 2");
        friendsActivities.add("Friend 1 took Quiz 3");
        friendsActivities.add("Friend 2 created Quiz 4");
        recentlyCreatedQuizzes.add("Quiz 5");
        recentlyCreatedQuizzes.add("Quiz 6");
        myRecentlyTakenQuizzes.add("Quiz 7");
        myRecentlyTakenQuizzes.add("Quiz 8");
        myRecentlyCreatedQuizzes.add("Quiz 9");
        myRecentlyCreatedQuizzes.add("Quiz 10");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        manageSection(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("currentSection", currentSection);
        request.setAttribute("userName", userName);
        request.setAttribute("achievements", achievements);
        request.getRequestDispatcher("/WEB-INF/home/home.jsp").forward(request, response);
    }


    private List<String> announcements;
    private List<String> popularQuizzes;
    private List<String> friendsActivities;
    private List<String> recentlyCreatedQuizzes;
    private List<String> myRecentlyTakenQuizzes;
    private List<String> myRecentlyCreatedQuizzes;


    private void manageSection(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String section = request.getParameter("section");
        if (section != null) {
            currentSection = section;
        }else{
            currentSection = "Announcements";
        }

        switch (currentSection) {
            case "Popular Quizzes":
                request.setAttribute("Popular Quizzes", popularQuizzes);
                break;
            case "My Recently Created Quizzes":
                request.setAttribute("My Recently Created Quizzes", myRecentlyCreatedQuizzes);
                break;
            case "Recently Created Quizzes":
                request.setAttribute("Recently Created Quizzes", recentlyCreatedQuizzes);
                break;
            case "Friends Activities":
                request.setAttribute("Friends Activities", friendsActivities);
                break;
            case "My Recently Taken Quizzes":
                request.setAttribute("My Recently Taken Quizzes", myRecentlyTakenQuizzes);
                break;
            default:
                request.setAttribute("Announcements", announcements);
        }

        String ajaxRequest = request.getHeader("X-Requested-With");
        if ("XMLHttpRequest".equals(ajaxRequest)) {
            response.setContentType("application/json");
            response.getWriter().write("{\"currentSection\":\"" + currentSection + "\"}");
        } else {
            request.getRequestDispatcher("/WEB-INF/home/home.jsp").forward(request, response);
        }
    }

}
