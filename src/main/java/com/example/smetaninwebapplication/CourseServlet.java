package com.example.smetaninwebapplication;

import dao.UserDao;
import dao.ModuleDao;
import models.Course;
import models.Module;
import dao.CourseDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/course")
public class CourseServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        CourseDao courseDao = new CourseDao();
        UserDao userDao = new UserDao();
        ModuleDao moduleDao = new ModuleDao();

        int course_id = Integer.parseInt(request.getParameter("course_id"));
        try {
            if (courseDao.exists(course_id)){
                Course course = courseDao.getById(course_id);
                ArrayList<Module> modules = course.getModules();
                request.setAttribute("modules", modules);
                request.setAttribute("courseName", course.getName());
                request.setAttribute("courseDescription", course.getDescription());

                String module_id = request.getParameter("module_id");
                //  Если передан ID Модуля - проверяем на корректность
                if (module_id != null){
                    // Если ID Курса и Модуля корректны - переводим на страницу Модуля
                    if (moduleDao.exists(course_id, Integer.parseInt(module_id))){
                        System.out.println("course_id & module_id are correct");
                        Module module = moduleDao.getById(Integer.parseInt(module_id));
                        request.setAttribute("module", module);
                        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/pages/module.jsp");
                        requestDispatcher.forward(request, response);
                        // Если ID Курса и Модуля НЕкорректны - выводим ошибку
                    } else {
                        System.out.println("course_id & module_id are INCORRECT");
                        request.setAttribute("errorMessage", "Модуль с таким ID для курса не существует :(");
                        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("WEB-INF/pages/error.jsp");
                        requestDispatcher.forward(request, response);
                    }
                    // Если не передан ID модуля - выводим список всех модулей для курса
                } else {
                    System.out.println("модуль не передали");
                }


            } else {
                request.setAttribute("errorMessage", "Курс с таким ID не существует :(");
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("WEB-INF/pages/error.jsp");
                requestDispatcher.forward(request, response);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (java.lang.NumberFormatException e){
            request.setAttribute("errorMessage", "Модуль с таким ID для курса не существует :(");
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/pages/error.jsp");
            requestDispatcher.forward(request, response);
        }

        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/pages/course.jsp");
        requestDispatcher.forward(request, response);
    }

    public void destroy() {
    }
}