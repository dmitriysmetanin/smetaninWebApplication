<%@ page import="models.Course" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: DmYSmetanin
  Date: 21.02.2024
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Созданные курсы</title>
</head>
<body>
    <div>
        <button>
            <a href="${pageContext.request.contextPath}/author/create_course">Создать курс</a>
        </button>
    </div>

    <%
        ArrayList<Course> courses = (ArrayList<Course>) request.getAttribute("courses");
        if (courses != null){
            for (Course course: courses){ %>
                <div>
                    <div>
                        <a href="${pageContext.request.contextPath}/course&course_id=<%=course.getId()%>">
                            <%=course.getName()%>
                        </a>
                    </div>
                    <div>
                        <span>Описание:</span>
                        <span><%=course.getDescription()%></span>
                    </div>
                </div>
                <%}}%>
</body>
</html>
