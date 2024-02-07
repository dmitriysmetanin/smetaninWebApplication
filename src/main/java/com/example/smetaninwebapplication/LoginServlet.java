package com.example.smetaninwebapplication;

import com.example.smetaninwebapplication.registration.dao.UserDao;
import com.example.smetaninwebapplication.registration.model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final UserDao userDao = new UserDao();

    public LoginServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login.jsp");
        requestDispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");

        User user = new User();
        user.setEmail(email);
        user.setPass(pass);

        try {
            if (user.login() == 1){
                HttpSession session = request.getSession();
                int user_id = user.getId();
//                session.setAttribute("user_id", String.valueOf(user_id));
                
                Cookie cookie = new Cookie("user_id",String.valueOf(user_id));
                cookie.setMaxAge(7*24*60*60);
                response.addCookie(cookie);
//                response.sendRedirect("/");

                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/index.jsp");
                requestDispatcher.forward(request, response);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


//        try {
//            int users_count = userDao.login(user);
//
//            HttpSession session = request.getSession();
//            session.setAttribute("login_result", String.valueOf(users_count));
//            RequestDispatcher requestDispatcher2 = getServletContext().getRequestDispatcher("/WEB-INF/index.jsp");
//            requestDispatcher2.forward(request, response);
//
//        } catch (ClassNotFoundException e){
//            throw new RuntimeException(e);
//        }
//        user.setPass(pass);
//
//        try {
//            userDao.registerUser(user);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//
//        Cookie cookie = new Cookie("user_id", name);
//        cookie.setMaxAge(7*24*60*60);
//        response.addCookie(cookie);

//        HttpSession session = request.getSession();
//        session.setAttribute("name", name);

//        RequestDispatcher requestDispatcher = request.getRequestDispatcher("welcome.jsp");
//        requestDispatcher.forward(request, response);
    }

}