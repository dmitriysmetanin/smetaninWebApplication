package com.example.smetaninwebapplication;

import dao.UserDao;
import models.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
    private final UserDao userDao = new UserDao();

    public RegistrationServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/pages/register.jsp");
        requestDispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPass(pass);

        try {
            userDao.registerUser(user);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        Cookie cookie = null;
        try {
            cookie = new Cookie("user_id", Integer.toString(user.getId()));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        cookie.setMaxAge(7*24*60*60);
        response.addCookie(cookie);
        response.sendRedirect("/");

    }

}