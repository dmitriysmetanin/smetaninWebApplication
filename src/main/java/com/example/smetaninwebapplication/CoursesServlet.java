package com.example.smetaninwebapplication;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/courses")
public class CoursesServlet extends HttpServlet {
    private String message;

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();

        String user_name = (String) session.getAttribute("user_id");
        if (user_name == null){
            response.sendRedirect("/login");
        }
        else {
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/courses.jsp");
            requestDispatcher.forward(request, response);
        }


    }

    public void destroy() {
    }
}