package servlets.authorization;

import services.UserManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.http.HttpServlet;
import java.io.IOException;


@WebServlet("/login")
public class loginServlet extends HttpServlet {

    public void init() throws ServletException {}
    private UserManager userManager;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        userManager = UserManager.GetInstance(request.getSession());

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String token = userManager.login(username, password);
        if(token != null){
            request.getSession().setAttribute("jwt", token);
            response.sendRedirect(request.getContextPath() + "/home");
        }else{
            //failed login
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/authorization/login.jsp").forward(request, response);
    }

    public void destroy() {
        // do nothing.
    }
}
