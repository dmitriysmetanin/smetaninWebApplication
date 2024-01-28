package com.example.smetaninwebapplication;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

public class IndexServlet extends HttpServlet {
    private String message;

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
    }

    public void destroy() {
    }
}