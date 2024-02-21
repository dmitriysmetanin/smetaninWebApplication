package com.example.smetaninwebapplication;

import dao.UserDao;
import models.User;
import dao.CourseDao;
import models.Course;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/courses")
public class CoursesServlet extends HttpServlet {
    private String message;

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        CourseDao courseDao = new CourseDao();
        UserDao userDao = new UserDao();
        Cookie[] cookies = request.getCookies();
        Integer user_id = 0;
        for (Cookie cookie: cookies){
            if (cookie.getName().equals("user_id")) {
                user_id = Integer.valueOf(cookie.getValue());
                request.setAttribute("user_id", user_id);
            }
        }
        try {
            User user = userDao.getById(user_id);
            ArrayList<Course> courses = user.getSubscribedCourses();
            request.setAttribute("courses", courses);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/pages/courses.jsp");
    requestDispatcher.forward(request, response);
    }

    public void destroy() {
    }
}