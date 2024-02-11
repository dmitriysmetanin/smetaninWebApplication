<%@ page import="models.Module" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Dmitriy
  Date: 11.02.2024
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><%=request.getAttribute("courseName")%></title>
</head>
<body>
    <div>
        <h1><%=request.getAttribute("courseName")%></h1>
        <h3><%=request.getAttribute("courseDescription")%></h3>
        <%
            ArrayList<Module> modules = (ArrayList<Module>) request.getAttribute("modules");
            for (Module module: modules){%>
                <div style="border: 1px solid black;">
                    <a href="/course?course_id=<%=module.getCourseid()%>&module_id=<%=module.getId()%>"><%=module.getName()%></a>
                    <span><%=module.getDesctiption()%></span>

                </div>
        <%}%>
    </div>

</body>
</html>
