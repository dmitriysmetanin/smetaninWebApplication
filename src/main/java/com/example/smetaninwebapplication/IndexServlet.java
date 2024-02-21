package com.example.smetaninwebapplication;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.UserDao;
import models.User;
import dao.CourseDao;
import models.Course;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/")
public class IndexServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer courseToAddId = Integer.valueOf(request.getParameter("courseToAddId"));
        CourseDao courseDao = new CourseDao();
        UserDao userDao = new UserDao();

        Cookie[] cookies = request.getCookies();
        int user_id = 0;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("user_id")) {
                user_id = Integer.parseInt(cookie.getValue());
                System.out.println(user_id);
                request.setAttribute("user_id", user_id);
            }
        }

        try {
            Course course = courseDao.getById(courseToAddId);
            User user = userDao.getById(user_id);
            if (course != null) {
                userDao.subscribe(course, user);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("/");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Cookie[] cookies = request.getCookies();
        UserDao userDao = new UserDao();
        for (Cookie cookie : cookies) {
            if (cookie != null) {
                switch (cookie.getName()) {
                    case ("user_id"):
                        request.setAttribute("user_id", Integer.valueOf(cookie.getValue()));
                        break;
                    case ("userMode"):
                        request.setAttribute("userMode", cookie.getValue());
                    case ("editProfileInfoMode"):
                        request.setAttribute("editProfileInfoMode", cookie.getValue());
                }
            }
        }

        if (request.getAttribute("user_id") == null) {
            response.sendRedirect("/login");
        } else {
            if (request.getAttribute("userMode").equals("student")) {
                try {
                    User user = userDao.getById((Integer) request.getAttribute("user_id"));
                    ArrayList<Course> subscribedCourses = user.getSubscribedCourses();
                    request.setAttribute("subscribedCourses", subscribedCourses);
                } catch (ClassNotFoundException | SQLException e) {
                    throw new RuntimeException(e);
                }
            } else if (request.getAttribute("userMode").equals("teacher")) {
                ArrayList<Course> createdCourses = new ArrayList<>();
                request.setAttribute("createdCourses", createdCourses);
            }

            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/pages/index.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}