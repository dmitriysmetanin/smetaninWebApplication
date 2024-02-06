<%@ page import="com.example.smetaninwebapplication.studying.model.Course" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="com.example.smetaninwebapplication.registration.model.User" %>
<%@ page import="com.example.smetaninwebapplication.registration.dao.UserDao" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>S-COURSES</title>
</head>
<body>
<header style="display: flex; flex-direction: row;">
    <div class="header column">
        <button>
            <a href="${pageContext.request.contextPath}/catalog">Каталог</a>
        </button>
    </div>
    <div class="header column">
        <button>
            <a href="${pageContext.request.contextPath}/courses">Мои курсы</a>
        </button>
    </div>
    <div class="header column">
        <button>
            <a href="${pageContext.request.contextPath}/stats">Статистика</a>
        </button>
    </div>
    <div class="header column">
        <button>
            <a href="${pageContext.request.contextPath}/profile">Профиль</a>
        </button>
    </div>
    <div class="header column">
        <button>
            <a href="${pageContext.request.contextPath}/profile">Профиль</a>
        </button>
    </div>
</header>

<p>index page content here</p>

<%
    ArrayList<Course> courses = (ArrayList<Course>) request.getAttribute("courses");
    UserDao userDao = new UserDao();
    for (Course course: courses) {%>
        <div style="border: 1px solid black;">
            <div>
                <span><%= course.getName() %></span>
            </div>
            <div>
                <span>Описание: </span>
                <span><%= course.getDescription() %></span>
            </div>
            <div>
                <span>Автор: </span>
                <span><%= userDao.getNameById(course.getAuthorId()) %></span>
            </div>
        </div>
<% } %>

<footer style="display: flex; flex-direction: row; position: absolute; bottom: 0;">
    <div class="footer column" style="display: flex; flex-direction: column">
        <div class="footer column title">
                <span class="footer column title span">Авторы</span>
        </div>
        <div class="footer column row">
            <span class="footer column row span">Название компании</span>
        </div>
        <div class="footer column row">
            <span class="footer column row span">+1 234 567 89 10</span>
        </div>
        <div class="footer column row">
            <span class="footer column row span">ул. Пушкина, д. 1</span>
        </div>
    </div>
    <div class="footer column" style="display: flex; flex-direction: column">
        <div class="footer column title">
            <span class="footer column title span">Техническая поддержка</span>
        </div>
        <div class="footer column row">
            <span class="footer column row span">support@domain.zone</span>
        </div>
    </div>
</footer>


<%--    <h1><%= "This is main page" %></h1>--%>
<%--    <%@ page import="services.GetDateClass" %>--%>

<%--    <% GetDateClass getDate = new GetDateClass(); %>--%>
<%--    <%=--%>
<%--    getDate.getCurrentDate()--%>
<%--    %>--%>
</body>
</html>