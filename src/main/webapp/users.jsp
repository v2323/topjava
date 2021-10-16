<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<%--<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>--%>
<html>
<head>
    <title>User list</title>
    <style>
        .enabled {
            color: green;
        }

        .disabled {
            color: red;
        }
    </style>
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <hr/>
    <h2>Users</h2>
    <a href="users?action=create">Add User</a>
    <br><br>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Registration Date</th>
            <th>Username</th>
            <th>E-mail</th>
<%--            <th>UserId</th>--%>
<%--            <th></th>--%>
<%--            <th></th>--%>
        </tr>
        </thead>
        <c:forEach items="${users}" var="user">
            <jsp:useBean id="user" type="ru.javawebinar.topjava.model.User"/>
            <tr class="${user.enabled ? 'enabled' : 'disabled'}">
                <td>
                        <%--${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}--%>
                        <%--<%=TimeUtil.toString(meal.getDateTime())%>--%>
                        <%--${fn:replace(meal.dateTime, 'T', ' ')}--%>
<%--                        ${fn:formatDateTime(user.registered)}--%>
                                ${user.registered}
                </td>
                <td>${user.name}</td>
                <td>${user.email}</td>
<%--                <td><a href="meals?action=update&id=${meal.id}">Update</a></td>--%>
<%--                <td><a href="meals?action=delete&id=${meal.id}">Delete</a></td>--%>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>