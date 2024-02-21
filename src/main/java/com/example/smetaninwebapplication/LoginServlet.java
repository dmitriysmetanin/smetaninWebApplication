package com.example.smetaninwebapplication;

import dao.UserDao;
import models.User;
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
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/pages/login.jsp");
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
                int user_id = user.getId();
                Cookie cookieAuthorMode = new Cookie("userMode", "student");
                cookieAuthorMode.setMaxAge(7 * 24 * 60 * 60);
                response.addCookie(cookieAuthorMode);

                Cookie cookie = new Cookie("user_id",String.valueOf(user_id));
                cookie.setMaxAge(7*24*60*60);
                response.addCookie(cookie);

                response.sendRedirect("/");
            } else {
                response.sendRedirect("/login?redirect_after=incorrect_credentials");
            }

        } catch (ClassNotFoundException e) {
            System.out.println(e.getStackTrace());
            throw new RuntimeException(e);
        } catch (SQLException e) {
            System.out.println(e.getStackTrace());
            throw new RuntimeException(e);
        }
    }

}