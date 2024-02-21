package com.example.smetaninwebapplication;

import dao.UserDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Course;
import models.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/author/created_courses")
public class CreatedCoursesServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/pages/created_courses.jsp");
        requestDispatcher.forward(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        UserDao userDao = new UserDao();
        Cookie[] cookies = request.getCookies();

        Integer user_id = 0;
        for (Cookie cookie: cookies){
            if (cookie.getName().equals("user_id")) {
                user_id = Integer.valueOf(cookie.getValue());
                request.setAttribute("user_id", user_id);
            }
        }


        User user = null;
        try {
            user = userDao.getById(user_id);
            ArrayList<Course> courses = user.getCreatedCourses();
            request.setAttribute("courses", courses);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/pages/created_courses.jsp");
        requestDispatcher.forward(request, response);
    }
}