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
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer courseToAddId = Integer.valueOf(request.getParameter("courseToAddId"));
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
            Course course = courseDao.getById(courseToAddId);
            User user = userDao.getById(user_id);
            if (course != null){
                userDao.subscribe(course, user);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    response.sendRedirect("/");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        Cookie[] cookies = request.getCookies();
        HttpSession session = request.getSession();
        CourseDao courseDao = new CourseDao();
        try {
            ArrayList<Course> courses = courseDao.getAllCourses();
            request.setAttribute("courses", courses);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        Integer user_id = 0;
        for (Cookie cookie: cookies){
            if (cookie != null){
                if (cookie.getName().equals("user_id")) {
                    user_id = Integer.valueOf(cookie.getValue());
                    request.setAttribute("user_id", user_id);
                }
            }
        }
        if (request.getAttribute("user_id") ==  null) {
            response.sendRedirect("/login");
        } else {
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/pages/index.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}