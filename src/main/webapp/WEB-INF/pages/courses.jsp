<%@ page import="models.Course" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="models.User" %>
<%@ page import="dao.UserDao" %>
<%@ page import="java.util.Objects" %>
<%@ page import="dao.CourseDao" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>S-COURSES</title>
</head>

<%
    UserDao userDao = new UserDao();
    Cookie[] cookies = request.getCookies();
    Integer user_id = 0;
    String userMode = "";
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if (Objects.equals(cookie.getName(), "user_id")) {
                user_id = Integer.valueOf(cookie.getValue());
            }
            if (Objects.equals(cookie.getName(), "userMode")) {
                userMode = cookie.getValue();
                System.out.println(userMode);
            }
        }
    }
    ArrayList<Course> courses = (ArrayList<Course>) request.getAttribute("courses");
    User user = userDao.getById(user_id);
%>

<body>
<header style="display: flex; flex-direction: row;">
    <%
        if (userMode.equals("teacher")) {
    %>
    <div class="header column">
        <button>
            <a href="${pageContext.request.contextPath}/created_courses">Созданные курсы</a>
        </button>
    </div>
    <%} else if (userMode.equals("student")) {%>
    <div class="header column">
        <button>
            <a href="${pageContext.request.contextPath}/">Каталог</a>
        </button>
    </div>
    <div class="header column">
        <button>
            <a href="${pageContext.request.contextPath}/courses">Мои курсы</a>
        </button>
    </div>
    <%}%>
    <div class="header column">
        <button>
            <a href="${pageContext.request.contextPath}/profile">Профиль</a>
        </button>
    </div>
</header>

<%
    for (Course course: courses) { %>
        <div style="border: 1px solid black;">
            <div>
                <a href="course?course_id=<%=course.getId()%>">
                    <%= course.getName() %>
                </a>
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
    <%}
%>

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

</body>
</html>