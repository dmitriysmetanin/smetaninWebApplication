<%@ page import="java.io.PrintWriter" %>
<%@ page import="models.Module" %><%--
  Created by IntelliJ IDEA.
  User: Dmitriy
  Date: 11.02.2024
  Time: 15:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>

<head>
    <title></title>
</head>
<body>
    <div>
        <%
//            ArrayList<Module> modules = (ArrayList<Module>) request.getAttribute("modules");
            Module module = (Module) request.getAttribute("module");%>

        <div style="border: 1px solid black;">
            <div>
                <span><%=module.getName()%></span>
            </div>
            <div>
                <span><%=module.getDesctiption()%></span>
            </div>
            <div>
                <span><%=module.getContent()%></span>
            </div>
        </div>
    </div>
</body>
</html>

