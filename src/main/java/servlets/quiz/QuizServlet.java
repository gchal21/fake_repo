package servlets.quiz;

import DTOs.QuizHistoryDTO;
import DTOs.QuizStatsDTO;
import entities.Quiz;
import entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Array;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@WebServlet("/quizSummary")
public class QuizServlet extends HttpServlet {

    private Quiz quiz;
    private User author;
    private QuizStatsDTO quizStatsDTO;
    private List<QuizHistoryDTO> myPerformace, highestPerformances, lastDayHighestPerformances, mostRecentPerformances;

    @Override
    public void init() throws ServletException {
        //DUMMY DATA
        quiz = new Quiz(0L, 0L, 0, LocalDateTime.now(), 0L, "Which President Are You", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed suscipit massa eu volutpat maximus. Praesent est eros, maximus eu risus quis, condimentum viverra mauris. Sed at pellentesque massa. Sed maximus pretium risus sed tristique. Cras eget tempor tortor, quis consectetur tellus. Aenean malesuada, risus maximus pellentesque sagittis, metus justo commodo leo, non condimentum enim erat a libero. Aenean nec finibus sapien, ut ultricies purus. Nam venenatis tortor vehicula iaculis tincidunt.");
        author = new User(0L, "John Doe", "134", "John@gmail.com", new Timestamp(System.currentTimeMillis()), 1);

        QuizHistoryDTO q1 = new QuizHistoryDTO(0, "John Doe", new Date(1776-1900, 6, 4), 20, 1000); // American Independence Day, July 4, 1776
        QuizHistoryDTO q2 = new QuizHistoryDTO(1, "Klara Zetkin", new Date(1917-1900, 9, 25), 80, 6536); // October Revolution, October 25, 1917
        QuizHistoryDTO q3 = new QuizHistoryDTO(2, "Sam Black", new Date(1969-1900, 6, 20), 34, 2335); // Moon Landing, July 20, 1969
        QuizHistoryDTO q4 = new QuizHistoryDTO(3, "Daenerys Storm-born", new Date(1989-1900, 10, 9), 14, 875); // Fall of the Berlin Wall, November 9, 1989
        QuizHistoryDTO q5 = new QuizHistoryDTO(4, "George R.R.Martin", new Date(1945-1900, 4, 8), 460, 2342); // VE Day, May 8, 1945
        QuizHistoryDTO q6 = new QuizHistoryDTO(5, "John Smith", new Date(1492-1900, 9, 12), 54, 24); // Columbus Discovers America, October 12, 1492
        QuizHistoryDTO q7 = new QuizHistoryDTO(6, "Smith John", new Date(1912-1900, 3, 15), 3, 24); // Sinking of the Titanic, April 15, 1912
        QuizHistoryDTO q8 = new QuizHistoryDTO(7, "Adam Marx", new Date(2001-1900, 8, 11), 6, 256436); // September 11 Attacks, September 11, 2001

        myPerformace = Arrays.asList(q1, q2, q3, q4);
        highestPerformances = Arrays.asList(q4, q5, q6);
        lastDayHighestPerformances = Arrays.asList(q7, q8, q1, q2, q3);
        mostRecentPerformances = Arrays.asList(q1, q2);

        quizStatsDTO = new QuizStatsDTO(1034, 173.7);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String quizId = request.getParameter("id");

        request.setAttribute("currentQuiz", quiz);
        request.setAttribute("author", author);
        request.setAttribute("myPerformance", myPerformace);
        request.setAttribute("highestPerformances", highestPerformances);
        request.setAttribute("lastDayHighestPerformances", lastDayHighestPerformances);
        request.setAttribute("mostRecentPerformances", mostRecentPerformances);
        request.setAttribute("quizStats", quizStatsDTO);

        request.getRequestDispatcher("/WEB-INF/quizSummary/quizLandingPage.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.getRequestDispatcher("/WEB-INF/quiz/quizLandingPage.jsp").forward(request, response);
    }
}
