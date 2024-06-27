package servlets.user;

import entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;


@WebServlet("/user")
public class UserServlet extends HttpServlet {

    public void init() throws ServletException {
        testUser = new User(1, "furer", "hash", "hitl@gmail.com", new Timestamp(System.currentTimeMillis()), 1);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("currentUser", testUser);
        request.getRequestDispatcher("/WEB-INF/user/user.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("currentUser", testUser);
        request.getRequestDispatcher("/WEB-INF/user/user.jsp").forward(request, response);
    }

    User testUser;

}
