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
<body>

<%
    UserDao userDao = new UserDao();
    Cookie[] cookies = request.getCookies();
    int user_id = 0;
    String userMode = "";
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if (Objects.equals(cookie.getName(), "user_id")) {
                user_id = Integer.parseInt(cookie.getValue());
            }
            if (Objects.equals(cookie.getName(), "userMode")) {
                userMode = cookie.getValue();
                System.out.println(userMode);
            }
        }
    }
    User user = userDao.getById(user_id);
%>

<header style="display: flex; flex-direction: row;">
    <%
        if (userMode.equals("teacher")) {
    %>
    <div class="header column">
        <button>
            <a href="${pageContext.request.contextPath}/">Созданные курсы</a>
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


<% if (userMode.equals("teacher")) {%>
    <div>
        <button>
            <a href="${pageContext.request.contextPath}/author/create_course">Создать курс</a>
        </button>
    </div>
    <%
        ArrayList<Course> createdCourses = (ArrayList<Course>) request.getAttribute("createdCourses");
        if (createdCourses != null){
            for (Course course: createdCourses){ %>
    <div>
        <div>
            <a href="${pageContext.request.contextPath}/course?course_id=<%=course.getId()%>">
                <%=course.getName()%>
            </a>
        </div>
        <div>
            <span>Описание:</span>
            <span><%=course.getDescription()%></span>
        </div>
    </div>
    <%}}%>
<%
} else if (userMode.equals("student")) {
    ArrayList<Course> courses = (ArrayList<Course>) request.getAttribute("subscribedCourses");
    for (Course course : courses) {
        if (!request.getAttribute("user_id").equals(course.getAuthorId())) { %>
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

                <% if (!user.isStudentOf(course)) {%>
                <div>
                    <form action="${pageContext.request.contextPath}/IndexServlet" method="post">
                        <input name="courseToAddId" hidden="hidden" value="<%=course.getId()%>"/>
                        <input type="submit" value="Подписаться"/>
                    </form>
                </div>
                <%} else {%>
                <div>
                    <span>Вы подписаны на курс!</span>
                </div>
                <%}%>
            </div>
        <%}%>
<%}}%>

</body>
</html>