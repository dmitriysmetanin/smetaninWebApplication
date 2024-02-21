<%--
  Created by IntelliJ IDEA.
  User: Dmitriy
  Date: 11.02.2024
  Time: 14:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ooops...!</title>
</head>
<body>

    <div>
        <div>
            <h1>
                <%=request.getParameter("errorMessage")%>
            </h1>
        </div>
        <img src=""
        alt="sadImage.png">
    </div>
</body>
</html>
