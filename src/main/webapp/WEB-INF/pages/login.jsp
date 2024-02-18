<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.util.Objects" %><%--
  Created by IntelliJ IDEA.
  User: Dmitriy
  Date: 28.01.2024
  Time: 19:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Войти или зарегистрироваться</title>
</head>
<body>
    <%
        PrintWriter pw = response.getWriter();
        String redirect_after = request.getParameter("redirect_after");
        if (Objects.equals(redirect_after, "incorrect_credentials")){%>
            <div>
                <span>Введен неверный логин или пароль!</span>
            </div>
    <%}%>

    <form method="post" action="<%= request.getContextPath()%>/login">
        <label for="input_email">Email:</label>
        <input id="input_email" type="text" name="email" /><br/>

        <label for="input_pass">Password:</label>
        <input id="input_pass" type="text" name="pass" /><br/>

        <input type="submit">
    </form>
    <label for="register_anchor">Нет аккаунта? Зарегистрируйтесь</label>
    <a id="register_anchor" href="<%= request.getContextPath()%>/register">Зарегистрироваться</a>
</body>
</html>
