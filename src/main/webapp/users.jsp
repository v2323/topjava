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
<%--    <jsp:useBean id="user" type="ru.javawebinar.topjava.model.User"/>--%>
    <a href="users?action=create">Add User</a>
<%--    <a href="users?action=getAll">Зайти как Admin</a>--%>
    <a href="users?action=enterAsAdmin">Зайти как Admin</a>
    <br><br>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Registration Date</th>
            <th>Username</th>
            <th>E-mail</th>
            ${param.action == 'enterAsAdmin' ? '<th>Delete</th>' : ''}
            ${param.action == 'enterAsAdmin' ? '<th>Edit</th>' : ''}
<%--            <th>Delete</th>--%>
<%--            <th>Edit</th>--%>
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
                    ${param.action == 'enterAsAdmin' ? '<td><a href="users?action=delete&id=${user.id}">Delete</a></td>' : ''}
                    ${param.action == 'enterAsAdmin' ? '<td><a href="users?action=update&id=${user.id}">Edit</a></td>' : ''}
<%--                    ${user.roles eq 'USER' ? '<td><a href="users?action=update&id=${user.id}">Delete</a></td>': ''}--%>
<%--                <td><a href="users?action=update&id=${user.id}">Update</a></td>--%>
<%--                    <td></td>--%>
<%--&lt;%&ndash;                    ${param.action == 'getAll' ? '<th>Edit</th>' : ''}&ndash;%&gt;--%>
<%--                <td>type="<c:out value="${user.roles eq 'ADMIN' ? 'users?action=update&id=${user.id}': ''}"/>"</td>--%>
<%--                <td><a href="users?action=update&id=${user.id}">Delete</a></td>--%>
<%--                <td><a href=<c:out value="${user.roles eq 'ADMIN' ? 'users?action=update&id=${user.id}': ''}"/>>Update</a></td>--%>
<%--&lt;%&ndash;                <td><a href="meals?action=delete&id=${meal.id}">Delete</a></td>&ndash;%&gt;--%>
            </tr>
        </c:forEach>
    </table>
    <table border="1" cellpadding="8" cellspacing="8">
        <form>
            <thead>
            <tr>
                <th>Email</th>
            </tr>
            </thead>
            <tr>
                <input type="hidden" name="action" value="getByEmail">
                <td><input type="text" name="email"/></td>
            </tr>
            <button type="submit">Get by Email</button>
        </form>
    </table>
</section>
</body>
</html>