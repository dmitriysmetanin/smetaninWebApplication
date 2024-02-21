<%--
  Created by IntelliJ IDEA.
  User: DmYSmetanin
  Date: 21.02.2024
  Time: 15:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Создание курса</title>
</head>
<body>
    <form method="post" action="<%= request.getContextPath()%>/author/create_course">
        <label for="input_name">Название</label>
        <input id="input_name" type="text" name="name" /><br/>

        <label for="input_description">Описание</label>
        <input id="input_description" type="text" name="description" /><br/>

        <input type="submit">
    </form>
</body>
</html>
