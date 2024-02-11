package com.example.smetaninwebapplication;

import java.io.*;
import java.util.ArrayList;

import com.example.smetaninwebapplication.studying.dao.CourseDao;
import com.example.smetaninwebapplication.studying.model.Course;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/")
public class IndexServlet extends HttpServlet {
    private String message;

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        Cookie[] cookies = request.getCookies();
        HttpSession session = request.getSession();
//
//        for (Cookie cookie: cookies){
//                session.setAttribute(cookie.getName(), cookie.getValue());
//        }
        session.setAttribute("a", "1");
        session.setAttribute("b", "2");

        CourseDao courseDao = new CourseDao();
        try {
            ArrayList<Course> courses = courseDao.getAllCourses();
            request.setAttribute("courses", courses);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        Integer user_id = 0;
        for (Cookie cookie: cookies){
            if (cookie.getName().equals("user_id")) {user_id = Integer.valueOf(cookie.getValue());}
        }
       request.setAttribute("user_id", user_id);

        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/index.jsp");
        requestDispatcher.forward(request, response);
    }

    public void destroy() {
    }
}