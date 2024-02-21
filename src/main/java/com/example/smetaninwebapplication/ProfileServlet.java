package com.example.smetaninwebapplication;

import dao.UserDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.User;

import java.io.IOException;
import java.rmi.ServerException;
import java.sql.SQLException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String editProfileInfoMode = (String) request.getParameter("editProfileInfoMode");
        String userModeToChange = (String) request.getParameter("userModeToChange");

        if (editProfileInfoMode != null) {
            switch (editProfileInfoMode) {
                case ("true"):
                    Cookie cookie = new Cookie("editProfileInfoMode", "true");
                    cookie.setMaxAge(7 * 24 * 60 * 60);
                    response.addCookie(cookie);
                    response.sendRedirect("/profile");
                    break;
                case ("false"):
                    cookie = new Cookie("editProfileInfoMode", "false");
                    cookie.setMaxAge(7 * 24 * 60 * 60);
                    response.addCookie(cookie);
                    response.sendRedirect("/profile");
                    break;
            }
        }

        if (userModeToChange != null) {
            switch (userModeToChange) {
                case ("student"):
                    Cookie cookie1 = new Cookie("userMode", "student");
                    cookie1.setMaxAge(7 * 24 * 60 * 60);
                    response.addCookie(cookie1);
                    response.sendRedirect("/profile");
                    break;
                case ("teacher"):
                    Cookie cookie2 = new Cookie("userMode", "teacher");
                    cookie2.setMaxAge(7 * 24 * 60 * 60);
                    response.addCookie(cookie2);
                    response.sendRedirect("/profile");
                    break;
            }
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserDao userDao = new UserDao();
        Cookie[] cookies = request.getCookies();

        int user_id = 0;
        String userMode = "";
        String editProfileInfoMode = "";
        for (Cookie cookie : cookies) {
            if (cookie != null) {
                if (cookie.getName().equals("user_id")) {
                    user_id = Integer.parseInt(cookie.getValue());
                    request.setAttribute("user_id", user_id);
                } else if (cookie.getName().equals("userMode")) {
                    userMode = cookie.getValue();
                    request.setAttribute("userMode", userMode);
                } else if (cookie.getName().equals("editProfileInfoMode")) {
                    editProfileInfoMode = cookie.getValue();
                    request.setAttribute("editProfileInfoMode", editProfileInfoMode);
                }
            }
        }

        try {
            if (userDao.exists(user_id)) {
                User user = userDao.getById(user_id);
                request.setAttribute("user", user);

                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/pages/profile.jsp");
                requestDispatcher.forward(request, response);
            } else {
                response.sendRedirect("/error?errorMessage=Пользователь с таким ID не найден :(");
            }

        } catch (ClassNotFoundException | ServletException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}