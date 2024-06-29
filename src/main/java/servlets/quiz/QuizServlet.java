package servlets.quiz;

import entities.Quiz;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;


@WebServlet("/quiz")
public class QuizServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String quizId = request.getParameter("id");
        // Fetch the quiz's information from the database using the quizId
        //Quiz quiz = getQuizFromDatabase(quizId);
        Quiz quiz = new Quiz(0L, 0L, 0, LocalDateTime.now(), 0L, "testTitle", "magari quizi");
        request.setAttribute("currentQuiz", quiz);
        request.getRequestDispatcher("/WEB-INF/quiz/quizLandingPage.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.getRequestDispatcher("/WEB-INF/quiz/quizLandingPage.jsp").forward(request, response);
    }
}
