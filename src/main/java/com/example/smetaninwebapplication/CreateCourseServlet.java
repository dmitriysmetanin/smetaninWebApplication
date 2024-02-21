package com.example.smetaninwebapplication;

import dao.CourseDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Course;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

@WebServlet("/author/create_course")
public class CreateCourseServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Cookie[] cookies = request.getCookies();
        CourseDao courseDao = new CourseDao();
        Integer user_id = 0;
        for (Cookie cookie: cookies) {
            if (cookie.getName().equals("user_id")) {
                user_id = Integer.valueOf(cookie.getValue());
                request.setAttribute("user_id", user_id);
            }
        }

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        if (!Objects.equals(name, "") && !Objects.equals(description, "")) {
            Course course = new Course();
            course.setName(name);
            course.setDescription(description);
            course.setAuthorId(user_id);

            try {
                courseDao.create(course);
                response.sendRedirect("/");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/pages/create_course.jsp");
        requestDispatcher.forward(request, response);
    }
}