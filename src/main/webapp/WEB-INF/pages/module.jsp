<%@ page import="java.io.PrintWriter" %>
<%@ page import="models.Module" %>
<%@ page import="dao.ModuleDao" %>
<%@ page import="models.Hometask" %>
<%@ page import="java.util.Objects" %>
<%@ page import="dao.HometaskDao" %>
<%@ page import="dao.SubmittedHometaskDao" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="models.SubmittedHometask" %><%--
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

        <div>
            <%
                Cookie[] cookies = request.getCookies();
                PrintWriter pw = response.getWriter();
                Integer user_id = 0;
                if(cookies != null){
                    for (Cookie cookie: cookies){
//                        pw.println(cookie.getValue());
                        if (Objects.equals(cookie.getName(), "user_id")) {
                            user_id = Integer.valueOf(cookie.getValue());
                        };
                    }
                }

                HometaskDao hometaskDao = new HometaskDao();
                SubmittedHometaskDao submittedHometaskDao = new SubmittedHometaskDao();

                Hometask hometask = hometaskDao.getHometaskByModuleId(module.getId()); // получили объект дз
                if (hometask == null){ %>
                    <div>
                        <span>Для данной темы еще не опубликовано домашнее задание :(</span>
                    </div>
                <%} else {
                    SubmittedHometask submittedHometask = submittedHometaskDao.getSubmittedHometask(hometask.getModuleId(), user_id);
                    int submitted_hometask_status = submittedHometaskDao.getStatus(hometask.getModuleId(), user_id);
                    pw.println(submitted_hometask_status);
                    if (submitted_hometask_status == 0) {%>
                        <div>
                            <span>Вы не выполнили домашнее задание!</span>
                            <button>
                                <a href="hometask?module_id=<%=module.getId()%>">Выполнить ДЗ по теме</a>
                            </button>
                        </div>
                    <%} else if (submitted_hometask_status == 1){%>
                        <div>
                            <span>Вы отправили домашнее задание. Скоро его возьмут на проверку.</span>
                        </div>
                    <%} else if (submitted_hometask_status == 2){%>
                        <div>
                            <span>Домашнее задание на проверке.</span>
                        </div>
                    <%} else if (submitted_hometask_status == 3){%>
                        <div>
                            <span>Домашнее задание проверено!</span>
                            <div>
                                <span>Оценка:</span>
                                <span><%=submittedHometask.getPoints()%>/<%=hometask.getMaxPoints()%> (<%=Math.ceil((double) submittedHometask.getPoints() / hometask.getMaxPoints() * 100)%>%)</span>
                            </div>
                            <div>
                                <span>Комментарий эксперта:</span>
                                <span><%=submittedHometask.getTeachersComment()%></span>
                            </div>
                        </div>
                <%}}%>
        </div>
    </div>
</body>
</html>