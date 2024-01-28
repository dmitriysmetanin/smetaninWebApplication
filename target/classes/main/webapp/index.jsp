<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>First (index) page</title>
</head>
<body>
<header style="display: flex; flex-direction: row;">
    <div class="header column">
        <button>
            <a href="${pageContext.request.contextPath}/courses">Курсы</a>
        </button>
    </div>
    <div class="header column">
        <button>
            <a href="${pageContext.request.contextPath}/stats">Статистика</a>
        </button>
    </div>
    <div class="header column">
        <button>
            <a href="${pageContext.request.contextPath}/profile">Профиль</a>
        </button>
    </div>
</header>

<footer style="display: flex; flex-direction: row; position: absolute; bottom: 0;">
    <div class="footer column" style="display: flex; flex-direction: column">
        <div class="footer column title">
                <span class="footer column title span">Авторы</span>
        </div>
        <div class="footer column row">
            <span class="footer column row span">Название компании</span>
        </div>
        <div class="footer column row">
            <span class="footer column row span">+1 234 567 89 10</span>
        </div>
        <div class="footer column row">
            <span class="footer column row span">ул. Пушкина, д. 1</span>
        </div>
    </div>
    <div class="footer column" style="display: flex; flex-direction: column">
        <div class="footer column title">
            <span class="footer column title span">Техническая поддержка</span>
        </div>
        <div class="footer column row">
            <span class="footer column row span">support@domain.zone</span>
        </div>
    </div>
</footer>


<%--    <h1><%= "This is main page" %></h1>--%>
<%--    <%@ page import="services.GetDateClass" %>--%>

<%--    <% GetDateClass getDate = new GetDateClass(); %>--%>
<%--    <%=--%>
<%--    getDate.getCurrentDate()--%>
<%--    %>--%>
</body>
</html>