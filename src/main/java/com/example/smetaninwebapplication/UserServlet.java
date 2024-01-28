package com.example.smetaninwebapplication;

import com.example.smetaninwebapplication.registration.dao.UserDao;
import com.example.smetaninwebapplication.registration.model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/register")
public class UserServlet extends HttpServlet {
    private final UserDao userDao = new UserDao();

    public UserServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/register.jsp");
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

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("welcome.jsp");
        requestDispatcher.forward(request, response);
    }

}