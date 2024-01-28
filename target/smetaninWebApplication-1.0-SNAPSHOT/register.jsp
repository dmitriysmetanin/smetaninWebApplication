<%--
  Created by IntelliJ IDEA.
  User: Dmitriy
  Date: 28.01.2024
  Time: 19:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация</title>
</head>
<body>
    <form method="post" action="<%= request.getContextPath()%>/register">
        <label for="input_name">Name:</label>
        <input id="input_name" type="text" name="name" /><br/>

        <label for="input_email">Email:</label>
        <input id="input_email" type="text" name="email" /><br/>

        <label for="input_pass">Password:</label>
        <input id="input_pass" type="text" name="pass" /><br/>

        <input type="submit">
    </form>
</body>
</html>
