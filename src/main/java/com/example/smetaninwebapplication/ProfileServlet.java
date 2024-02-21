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
    private String message;

    public void init() {
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String editUserInfo = request.getParameter("editUserInfo");
        String userModeToChange = (String) request.getParameter("userModeToChange");


        switch (userModeToChange) {
            case (userModeToChange == null):
                break;
            case ("teacher".equals(userModeToChange)):
                break;

        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserDao userDao = new UserDao();
        Cookie[] cookies = request.getCookies();

        Integer user_id = 0;
        String userMode = "";
        for (Cookie cookie : cookies) {
            if (cookie != null) {
                if (cookie.getName().equals("user_id")) {
                    user_id = Integer.valueOf(cookie.getValue());
                    request.setAttribute("user_id", user_id);
                } else if (cookie.getName().equals("userMode")) {
                    userMode = cookie.getValue();
                    request.setAttribute("userMode", userMode);
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
            ;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }


    }

    public void destroy() {
    }
}