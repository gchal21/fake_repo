package servlets.authorization;

import services.UserManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class registerServlet extends HttpServlet {

    public void init() throws ServletException {}
    private UserManager userManager;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        userManager = UserManager.getInstance(request.getSession());
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        if(userManager.registerUser(username, email, password)){
            response.sendRedirect(request.getContextPath() + "/login");
        }
        else {
            //failed registration
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/authorization/register.jsp").forward(request, response);
    }

    public void destroy() {
        // do nothing.
    }
}
