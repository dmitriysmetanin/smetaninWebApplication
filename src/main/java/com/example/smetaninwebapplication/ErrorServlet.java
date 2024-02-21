package com.example.smetaninwebapplication;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/error")
public class ErrorServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("errorMessage", "No user with such ID exists");
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/pages/error.jsp");
        requestDispatcher.forward(request, response);
    }
}