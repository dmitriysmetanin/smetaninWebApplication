<%@ page import="models.User" %>
<%@ page import="java.util.PrimitiveIterator" %>
<%@ page import="java.io.PrintWriter" %><%--
  Created by IntelliJ IDEA.
  User: DmYSmetanin
  Date: 21.02.2024
  Time: 11:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Профиль</title>
</head>
<body>
    <%
    User user = (User) request.getAttribute("user");

    String userMode = (String) request.getAttribute("userMode");
    String userModeToChange = "";
    if (userMode.equals("student")){
        userModeToChange = "teacher";
    } else if (userMode.equals("teacher")){
        userModeToChange = "student";
    }

    String editProfileInfoMode = (String) request.getAttribute("editProfileInfoMode");
    String editProfileInfoModeToChange = "";
    if (editProfileInfoMode.equals("true")){
        editProfileInfoModeToChange = "false";
    } else if (editProfileInfoMode.equals("false")) {
        editProfileInfoModeToChange = "true";
    }

    %>
    <div>
        <div>
            <span>Имя</span>
            <span><%=user.getName()%></span>
        </div>
        <div>
            <span>Контактные данные</span>
            <span><%=user.getEmail()%></span>
            <span><%=user.getPhone()%></span>
        </div>
        <div>
            <span>Обо мне</span>
            <span><%=user.getAbout()%></span>
        </div>
        <div>
            <form method="post" action="<%= request.getContextPath()%>/profile">
                <button name="editProfileInfoMode" value=<%=editProfileInfoModeToChange%>>Edit</button>
                <button name="userModeToChange" value=<%=userModeToChange%>>Change to <%=userModeToChange%></button>
            </form>
        </div>
    </div>
    <%
    %>
</body>
</html>
