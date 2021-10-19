<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
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
        </tr>
        </thead>
        <c:forEach items="${users}" var="user">
            <jsp:useBean id="user" type="ru.javawebinar.topjava.model.User"/>
            <tr class="${user.enabled ? 'enabled' : 'disabled'}">
                <td>
                        ${user.registered}
                </td>
                <td>${user.name}</td>
                <td>${user.email}</td>
                <td><a href="users?action=delete&id=${user.id}">Delete</a></td>
                <td><a href="users?action=update&id=${user.id}">Edit</a></td>
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