package com.example.smetaninwebapplication;

import dao.UserDao;
import dao.CourseDao;
import dao.ModuleDao;
import models.Module;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

public class ModuleServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        CourseDao courseDao = new CourseDao();
        UserDao userDao = new UserDao();
        ModuleDao moduleDao = new ModuleDao();

        int course_id = Integer.parseInt(request.getParameter("course_id"));
        int module_id = Integer.parseInt(request.getParameter("module_id"));
        try {
            if (moduleDao.exists(course_id, module_id)){
                Module module = moduleDao.getById(course_id);
                request.setAttribute("module", module);
            } else {
                request.setAttribute("errorMessage", "Модуль с таким ID не существует :(");
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/pages/error.jsp");
                requestDispatcher.forward(request, response);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/pages/module.jsp");
        requestDispatcher.forward(request, response);
    }

    public void destroy() {
    }
}