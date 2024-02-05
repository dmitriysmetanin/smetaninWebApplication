package com.example.smetaninwebapplication;

import com.example.smetaninwebapplication.registration.dao.UserDao;
import com.example.smetaninwebapplication.registration.model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
    private final UserDao userDao = new UserDao();

    public RegistrationServlet() {
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

        Cookie cookie = new Cookie("user_id", name);
        cookie.setMaxAge(7*24*60*60);
        response.addCookie(cookie);

//        HttpSession session = request.getSession();
//        session.setAttribute("name", name);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("welcome.jsp");
        requestDispatcher.forward(request, response);
    }

}